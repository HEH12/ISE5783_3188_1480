package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface for light sources to implement
 */
public interface LightSource {
    /**
     * Returns light intensity at given point
     *
     * @param p the point
     * @return Color object
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction of the light at given point
     *
     * @param p the point
     * @return Vector object
     */
    public Vector getL(Point p);
}
