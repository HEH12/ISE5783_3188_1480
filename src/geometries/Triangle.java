package geometries;

import java.util.List;
import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon {


    /**
     * Constructor
     * @param p1 type point
     * @param p2 type point
     * @param p3 type point
     */

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }


    /**
     * Print Triangle's vertices and plane
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * Get Normal
     * @return normal vector from the point to the Triangle
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    /**
     * findIntersections find intersections between the triangle to ray
     * @param ray The Ray to intersect
     * @return list of point that intersections between the triangle to ray
     */
    @Override
    public java.util.List<primitives.Point> findIntersections(primitives.Ray ray) {

        List<Point> result = plane.findIntersections(ray);
        if (result == null) {
            return null;
        }
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        Vector v1 = p1.subtract(p0);// p0->p1
        Vector v2 = p2.subtract(p0);// p0->p2
        Vector v3 = p3.subtract(p0);//p0->p3

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        double s1 = n1.dotProduct(v);
        double s2 = n2.dotProduct(v);
        double s3 = n3.dotProduct(v);

        if (s1 > 0 && s2 > 0 && s3 > 0 || s1 < 0 && s2 < 0 && s3 < 0) {
            return result;
        }

        return super.findIntersections(ray);
    }
}
