package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * An abstract class for tracing the rays path through the scene
 */
public abstract class RayTracerBase {
    protected final Scene scene;

    /**
     * constructor
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * An abstract method
     * @param ray to consist the color
     * @return color
     */
    public abstract Color traceRay(Ray ray);
    
    /**
     * abstract function to determine the color of a pixel
     * @param rays - rays to intersect
     * @return the color of the pixel intersects the given rays
     */
//    public abstract Color traceRays(List<Ray> rays);
}
