package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * @author Hadas and Zehavi
 */
public class Triangle extends Polygon {

    final Point p0;
    final Point p1;
    final Point p2;
    Vector v0v1;
    Vector v0v2;


    public Triangle(Point pA, Point pB, Point pC) {
        super(pA, pB, pC);
        this.p0 = pA;
        this.p1 = pB;
        this.p2 = pC;

        /*Vector*/ v0v1 = p1.subtract(p0);      // v1 - v0
        /*Vector*/ v0v2 = p2.subtract(p0);      // v2 - v0


        this.normal = v0v1.crossProduct(v0v2).normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return this.normal;
    }





    @Override
    public String toString() {
        return "Triangle {" + p0 + "," + p1 + "," + p2 + "}";
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray, maxDistance);
        if (planeIntersections == null)
            return null;

        Point p0 = ray.getPoint();
        Vector v = ray.getDirection();

        Vector v1 = this.p0.subtract(p0);
        Vector v2 = this.p1.subtract(p0);
        Vector v3 = this.p2.subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1))
            return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2))
            return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3))
            return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            Point point = planeIntersections.get(0).point;
            return List.of(new GeoPoint(this, point));
        }
        return null;
    }
}