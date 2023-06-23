package primitives;

/**
 * this class represents a Vector in 3D Cartesian coordinates
 * the class inherits  {@link Point}
 * the vector is represents starting from  all three axis origins: Point(0.0.0)
 *
 * @author Hadas Holtzberg and Zehavi perla
 *
 */
public class Vector extends Point {

    /**
     * primary Constructor for Vector
     *
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * secondary  constructor for Vector class
     *
     * @param xyz {@link Double3 } head values of vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }

    public Vector(Point target) {
        this(target.xyz);
    }

    /**
     * this method provide the lengthSquared of the Vector in double
     *
     * @return the squared length
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1
                + xyz.d2 * xyz.d2
                + xyz.d3 * xyz.d3;
    }

    /**
     * this method provide the length of the Vector
     *
     * @return the length of the Vector in double
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * dot product between two vectors (scalar product)
     *
     * @param other the right vector of U.V
     * @return scalar value of the dot product
     *
     * {@see  https://www.mathsisfun.com/algebra/vectors-dot-product.html}
     */
    public double dotProduct(Vector other) {
        return xyz.d1 * other.xyz.d1
                + xyz.d2 * other.xyz.d2
                + xyz.d3 * other.xyz.d3;
    }

    /**
     * cross product between two vectors (vectorial product)
     *
     * @param other other the right vector of U.V
     * @return the vector resulting from the cross product (Right-hand rule)
     * {@see https://www.mathsisfun.com/algebra/vectors-cross-product.html}
     */
    public Vector crossProduct(Vector other) {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;

        double bx = other.xyz.d1;
        double by = other.xyz.d2;
        double bz = other.xyz.d3;

        double cx = ay * bz - az * by;
        double cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;


        return new Vector(cx, cy, cz);
    }

    /**
     * normalizing a vector so it's length will be 1
     *
     * @return new Vector in the sme direction with length equal to 1
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * subtract between this vector and another one
     * @param other  the second vector
     * @return new vector from this vector to the other vector
     */
    public Vector subtract(Vector other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    @Override
    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * extending the vector to a specific length
     * @param delta the length
     * @return new extended Vector
     */


    public Vector scale(double delta) {
        return new Vector(xyz.scale(delta));
    }
    /**
     * Rotates the vector around the x-axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateX(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX();
        double y = getY() * Math.cos(radianAlpha) - getZ() * Math.sin(radianAlpha);
        double z = getY() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);

        return new Vector(x, y, z);
    }

    /**
     * Rotates the vector around the y axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateY(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) + getZ() * Math.sin(radianAlpha);
        double y = getY();
        double z = -getX() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);

        return new Vector(x, y, z);
    }

    /**
     * Rotates the vector around the z axis
     *
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateZ(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) - getY() * Math.sin(radianAlpha);
        double y = getX() * Math.sin(radianAlpha) + getY() * Math.cos(radianAlpha);
        double z = getZ();

        return new Vector(x, y, z);
    }

}