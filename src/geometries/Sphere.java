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


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getPoint();
        Vector v = ray.getDirection();

        if (P0.equals(center)) {
            if(alignZero(radius - maxDistance) > 0){
                return null;
            }
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 <=0 && t2 <= 0){
            return null;
        }

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1), new GeoPoint(this,P2));
        }
        if (t1 > 0  && alignZero(t1 - maxDistance) <= 0) {
            Point P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this,P1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P2));
        }
        return null;
    }
}
