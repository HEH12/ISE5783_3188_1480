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
     * Declaration of abstract function
     *
     * @param rays rays to check the color
     * @return Average color of all rays at the pixel
     */
    public abstract Color traceRays(List<Ray> rays);
    /**
     * Declaration of abstract function
     * @param rays rays to check the color
     * @return Average color of some rays at the pixel, using adaptive super-sampling
     */
    public abstract Color adaptiveTraceRays(List<Ray> rays);

}
