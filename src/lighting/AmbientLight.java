package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight for all 3D objects
 */
public class   AmbientLight {

    public static final AmbientLight NONE = null ;
    private final Color intensity;

    /**
     * Constructor
     * @param Ia illumination color of light
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        intensity = Ia.scale(Ka);
    }

    /**
     * Default constructor
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * get Intensity -> reset intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
