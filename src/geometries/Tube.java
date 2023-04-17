package geometries;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry{

    public double radius;
    final Ray _ray;

    /**
     * constructor
     * @param radius type double
     * @param ray type ray
     */

    public Tube(double radius, Ray ray) {
        this.radius = radius;
        _ray = ray;
    }

    /**
     * get Radius
     * @return Radius
     */

    public double getRadius() {
        return radius;
    }


    /**
     * get Ray
     * @return Ray
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
     * @param point
     * @return normal vector from the point to the tube
     */

    @Override
    public Vector getNormal(Point point) {
        Point p0 = _ray.getP0();
        Vector v = _ray.getDir();
        Vector p0_p = point.subtract(p0);

        double w = v.dotProduct(p0_p);

        if (isZero(w)) {
            return p0_p;
        }

        Point startP = p0.add(v.scale(w));
        Vector n = point.subtract(startP);
        return n.normalize();
    }
}
