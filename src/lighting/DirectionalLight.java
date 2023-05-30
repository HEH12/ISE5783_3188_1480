package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private final Vector direction;


    /**
     * constructor
     * @param intensity of the light
     * @param direction of the ray
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * get Intensity
     * @param p point
     * @return getIntensity in super
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * get light
     * @param p
     * @return direction
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
