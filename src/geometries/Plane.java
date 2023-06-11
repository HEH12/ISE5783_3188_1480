/**class plane
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Plane extends Geometry{
    final  Point q0;
    final Vector normal;

    /**
     * constructor
     * @param q0 type point
     * @param normal type vector
     */

    public Plane(Point q0, Vector normal) {
        this.q0=q0;
        this.normal = normal.normalize();
    }
    /**
     * another constructor
     * @param q0
     * @param q1
     * @param q2
     */
    public Plane(Point q0, Point q1, Point q2) {
        this.q0 = q0;
        Vector u = q1.subtract(q0);
        Vector v = q2.subtract(q0);
        Vector n = u.crossProduct(v);
        this.normal = n.normalize();
    }
    /**
     * get q0
     * @return q0
     */

    public Point getQ0() {
        return q0;
    }

    /**
     * get normal.
     * @return normal.
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public String toString() {
        return "Plane : " +
                "point=" + q0 +
                ", normal=" + this.normal;
    }

    @Override
    public Vector getNormal(Point point) { return getNormal();}


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = normal;
        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        //ray cannot start from the plane
        if (q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = q0.subtract(P0);

        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

        // ray parallel to the plane
        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t < 0 ||  alignZero(t - maxDistance) > 0) {
            return null;
        }

        Point point = ray.getPoint(t);

        return List.of(new GeoPoint(this, point));
    }
}

