package renderer;

import static primitives.Util.alignZero;

import geometries.Geometries;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import java.util.List;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * derivative class from RayTracer traces ray path in the scene noting intersections
 * with geometries in the scene
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double DELTA = 0.1;
    private boolean bb;

    /**
     * constructor
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * setBb
     * @return bb
     */
    public RayTracerBase setBb(boolean bb) {
        this.bb = bb;
        return this;
    }

    /**
     * findClosestIntersection
     * @return list of the Closest Intersection point
     */
    private Point findClosestIntersection(Ray ray) {
        List<Point> intersections;
        intersections = scene.getGeometries().findIntersections(ray);
        if (intersections == null || intersections.size() == 0) {
            return null;
        } else {
            return ray.findClosestPoint(intersections);
        }
    }

    /**
     * traces the ray and its intersections with geometries to find the closest point and return its
     * colour
     * @param ray the ray being traced
     * @return the calculated color of the closest point- to colour thus themathcing pixel
     */
    @Override
    public Color traceRay(Ray ray) {
        Geometries geometries = scene.getGeometries();
        List<GeoPoint> intersectionPoints = geometries.findGeoIntersections(ray);
        if (intersectionPoints == null) {
            return scene.getBackground();
        }
        Intersectable.GeoPoint closesPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(closesPoint, ray);
    }

    /**
     * calcColor
     * @return the color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        Color result = scene.getAmbientLight().getIntensity();
        result = result.add(calcLocalEffects(gp, ray)).add(gp.geometry.getEmission());
        return result;
    }

    /**
     * calcLocalEffects
     * @return color effects
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return color;
        }
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                if (unshaded(gp, lightSource, n, nl, nv)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * unshaded
     * @return if has unshaded
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector n, double nl, double nv) {
        Point point = gp.point;
        Vector l = lightSource.getL(point);
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point pointRay = (point.add(delVector));
        Ray lightRay = new Ray(pointRay, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);
        if(intersections == null){
            return  true;
        }
        double maxDistance = lightSource.getDistance(point);
        if (intersections == null) {
            return true;
        }
        for (GeoPoint geoPoint : intersections) {
            double distance = geoPoint.point.distance(point);
            if (distance >= maxDistance) {
                intersections.remove(geoPoint);
            }
        }
        return intersections.isEmpty();
    }

    /**
     * calcDiffusive
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calcSpecular
     * @param material of the geometry
     * @return scale of the geometry
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        return material.kS.scale(
                Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.nShininess));
    }
}
