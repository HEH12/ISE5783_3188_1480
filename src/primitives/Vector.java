package primitives;
import static primitives.Util.isZero;
/**class vector is the basic class representing a vector that started from 0,0,0
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
public class Vector extends Point {
    /**
     *constructor
     * @param x coordinate value for x axis
     * @param y coordinate value for y axis
     * @param z coordinate value for z axis
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector(0,0,0) is not allowed");

    }
    /**
     * throw exception if the vector is zero vector
     *
     * another constructor
     * @param xyz
     */

    public Vector(Double3 xyz) {
        super(xyz);
            if(xyz.equals(Double3.ZERO)){
                throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }
    /**
     * Sum two vector into a new vector where each couple of numbers
     * is summarized
     *
     * @param v right handle side operand for addition
     * @return result of add
     */

    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }
    /**
     *Scale (multiply) vector by a number into a new vector
     *
     * @param scalar right handle side operand for scaling
     * @return result of scale
     */
    public Vector scale(double scalar) {
        if (isZero(scalar)) {
            throw new IllegalArgumentException("scale by zero not allowed");
        }
        return new Vector(xyz.scale(scalar));
    }
    /**
     * dot product between two vectors (scalar product)
     *
     * @param other the right vector of U.V
     * @return scalar value of the dot product
     * @link https://www.mathsisfun.com/algebra/vectors-dot-product.html
     */

    public double dotProduct(Vector other) {
        return xyz.d1 * other.xyz.d1 +
                xyz.d2 * other.xyz.d2 +
                xyz.d3 * other.xyz.d3;
    }
    /**
     * cross product between two vectors (vectorial product)
     * @param other other the right vector of U.V
     * @return the vector resulting from the cross product (Right-hand rule)
     * link cuemath.com/geometry/cross-product/
     */

    public Vector crossProduct(Vector other) {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;
        double bx = other.xyz.d1;
        double by = other.xyz.d2;
        double bz = other.xyz.d3;
        if (bx == 0 && by == 0 && bz == 0) {
            throw new IllegalArgumentException("parallel vectors- CROS PRODUCT");
        }
        return new Vector(ay*bz-by*az, -ax*bz+az*bx, ax*by-ay*bx);
    }
    /**
     *
     * @return length Squared of vector
     */

    public double lengthSquared()
    {
        return xyz.d1 * xyz.d1 +
                xyz.d2 * xyz.d2 +
                xyz.d3 * xyz.d3;
    }
    /**
     *
     * @return length of vector
     */

   public double length() {
       return Math.sqrt(lengthSquared());
    }

    /**
     * Divides the vector by its length
     * @return normalize vector
     */
    public Vector normalize() {
        double length = length();
        return new Vector(xyz.reduce(length));
    }

    /**
     * subtract vector from vector
     * @return vector less vector
     */
    public Vector subtract(Vector vector)
    {
        return new Vector(xyz.subtract(vector.xyz));
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}




