package primitives;
import java.util.Objects;
/**class point is the basic class representing a point
@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
public class Point {
    final Double3 xyz;

    /**
     * constructor
     * @param xyz0
     */
    public Point(Double3 xyz0) {
        this.xyz = xyz0;
    }

    /**
     *another constructor
     * @param x coordinate value for x axis
     * @param y coordinate value for y axis
     * @param z coordinate value for z axis
     */

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }
    /**
     *calculate the distance of two point
     *
     *
     * @param p1
     * @return distance=sqrt(lengthSquare
     */

   public Vector subtract(Point p1) {
        return new Vector(xyz.subtract(p1.xyz));
    }
    /**
     * Sum  floating point triads with vector into a new triad where each couple of numbers
     * is summarized
     *
     * @param vector right handle side operand for addition
     * @return result of add
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }
    /**
     *calculate the squared distance of two point
     *
     * @param other
     * @return distance Squared of two point
     */

    public double distanceSquared(Point other){
        double x1 = xyz.d1;
        double y1 = xyz.d1;
        double z1 = xyz.d1;
        double x2 = other.xyz.d1;
        double y2 = other.xyz.d1;
        double z2 = other.xyz.d1;
        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }
    /**
     *calculate the distance of two point
     *
     *
     * @param p1
     * @return distance=sqrt(lengthSquare
     */

    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));}

    @Override
    public String toString() {
        return "Point : " +
                "xyz=" + xyz ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

}
