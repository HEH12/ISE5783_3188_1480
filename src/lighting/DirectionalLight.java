package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

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

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
    @Override
    public List<Vector> getLCircle(Point p, double r, int amount) {
        return List.of(getL(p));
    }
}
