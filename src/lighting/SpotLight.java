package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * Class represents a light from a point with direction
 * Extends class PointLight
 */
public class SpotLight extends PointLight {
    // Field represents the direction of the light
    private Vector direction;

    /**
     * Constructor
     *
     * @param intensity parameter for field intensity in super
     * @param position  parameter for field position in super
     * @param direction parameter for field direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double proj = direction.dotProduct(getL(p)); //direction*(psition-p) , projection of light on point
        //if the light source doesn't hit the point return color black
        if (Util.isZero(proj))
            return Color.BLACK;

        double factor = Math.max(0, proj);
        Color i0 = super.getIntensity(p);

        // i0*(max(0,direction*(position-p))/(kC+d*kL+ds*kQ)
        return i0.scale(factor);
    }

    @Override
    public Vector getL(Point p) {
        if (p.equals(position)) {
            return null;
        }
        return p.subtract(position).normalize();
    }
    public PointLight setNarrowBeam(int val) {
        return this;
    }
}

