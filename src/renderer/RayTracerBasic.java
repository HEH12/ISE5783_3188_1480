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
    private boolean bb;

    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * setBb
     * @param bb
     * @return bb
     */
    public RayTracerBase setBb(boolean bb) {
        this.bb = bb;
        return this;
    }

    /**
     * findClosestIntersection
     * @param ray
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
     * @param gp
     * @param ray
     * @return the color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        Color result = scene.getAmbientLight().getIntensity();
        result = result.add(calcLocalEffects(gp, ray)).add(gp.geometry.getEmission());
        return result;
    }

    /**
     * calcLocalEffects
     * @param gp
     * @param ray
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
                //if (unshaded(gp, l)) {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
                //}
            }
        }
        return color;
    }

    /**
     * unshaded
     * @param gp
     * @param l
     * @return if has unshaded
     */
    private boolean unshaded(GeoPoint gp, Vector l) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        return intersections == null;
    }

    /**
     * calcDiffusive
     * @param material
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calcSpecular
     * @param material of the geometry
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return scale of the geometry
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        return material.kS.scale(
                Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.nShininess));
    }
}
