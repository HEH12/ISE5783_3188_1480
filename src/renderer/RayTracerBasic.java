package renderer;

import geometries.Geometries;
import java.util.List;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * derivative class from RayTracer traces ray path in the scene noting intersections
 * with geometries in the scene
 */
public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * traces the ray and its intersections with geometries to find the closest point and return its
     * colour
     * @param ray the ray being traced
     * @return the calculated color of the closest point- to colour thus themathcing pixel
     */
    @Override
    public Color traceRay(Ray ray) {
        Color color = scene.background;
        Geometries geometries = scene.geometries;
        List<Point> intersectionPoints = geometries.findIntersections(ray);
        if (intersectionPoints != null) {
            Point closesPoint = ray.findClosestPoint(intersectionPoints);
            color = calcColor(closesPoint);
        }
        return color;
    }

    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
