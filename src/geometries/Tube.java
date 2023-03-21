package geometries;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{

    public double radius;
    final Ray _ray;

    /**
     * constructor
     *  radius type double
     *  ray type ray
     */
    public Tube(double radius, Ray ray) {
        this.radius = radius;
        _ray = ray;
    }

    /**
     * get Radius
     * return Radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * get Ray
     * return Ray
     */
    public Ray getRay() {
        return _ray;
    }


    /**
     * print tube's radius and ray
     */
    @Override
    public String  toString() {
        return "Tube{" +
                "radius=" + radius +
                ", ray=" + _ray +
                '}';
    }

    /**
     * get Normal
     * param point
     * return normal vector from the point to the tube
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
