package geometries;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry{

    final Point center ;
    private double radius;
    /**
     * constructor
     * @param center type point
     * @param radius type double
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * get Center
     * @return Center
     */

    public Point getCenter() {
        return center;
    }

    /**
     * get Radius
     * @return Radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * print sphere's center and radius
     */
    @Override
    public String toString() {
        return "Sphere : " +
                "center=" + this.center +
                ", radius=" + radius ;
    }


    /**
     * get Normal
     * @param point
     * @return normal vector from the point to sphere
     */

    @Override
    public Vector getNormal(Point point) {

        Vector n = point.subtract(this.center);
        return n.normalize();
    }
    /**
     * findIntersections find intersections between the sphere to ray
     * @param ray The Ray to intersect
     * @return list of point that intersections between the sphere to ray
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();        //get point of start ray
        Vector v = ray.getDir();      //get dir of ray

        if (P0.equals(this.center)) {    //if start of ray equal to the sphere's center
            return List.of(this.center.add(v.scale(radius)));
        }

        Vector U = this.center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
            Point P1 =ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
            Point P2 =ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
    }
}
