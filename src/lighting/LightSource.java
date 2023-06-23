package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

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
    double getDistance(Point point);
    /**
     * Creates a list of vectors from the given point to random points around the light within radius r
     *
     * @param p      the given point
     * @param r      the radius
     * @param amount the amount of vectors to create
     * @return list of vectors
     */
    public List<Vector> getLCircle(Point p, double r, int amount);

}
