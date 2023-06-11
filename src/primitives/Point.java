package primitives;

import java.util.Objects;

/**
 * @author Hadas Holtzberg  & Zehavi perla
 */
public class Point {
    public static final Point ZERO = new Point(Double3.ZERO);
    final Double3 xyz;

    /**
     * secondary constructor for Point
     * @param xyz Double3 value containing x,z,z axis
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * primary constructor for Point
     *
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point(double x, double y , double z) {
        //this(new Double3(x,y,z));
        xyz = new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point " + xyz;
    }

    /**
     * calculating squared distance between two points
     *
     * @param other other is the second Point
     * @return  d = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1))
     */
    public double distanceSquared(Point other){
        double x1 = xyz.d1;
        double y1 = xyz.d2;
        double z1 = xyz.d3;

        double x2 = other.xyz.d1;
        double y2 = other.xyz.d2;
        double z2 = other.xyz.d3;

        return ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1));
    }

    /**
     * calculating the distance between two points
     *
     * @param other other is the second Point
     * @return d = Sqrt (lengthSquare)
     * {@see https://www.engineeringtoolbox.com/distance-relationship-between-two-points-d_1854.html}
     */
    public  double distance (Point other){
        return Math.sqrt(distanceSquared(other));
    }

    /**
     *  add a Vector producing a new point
     *
     * @param vector vector pointing to the new Point
     * @return new Point resulting from adding both coordinates and direction values
     */
    public Point add(Vector vector) {
        return  new Point(xyz.add(vector.xyz));
    }


    /**
     * substract a {@link Point} to create a new {@link  Vector}
     * @param other second point
     * @return new Vector from other point in the direction of this point
     */
    public Vector subtract(Point other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }

    public double getZ() {
        return xyz.d3;
    }
}