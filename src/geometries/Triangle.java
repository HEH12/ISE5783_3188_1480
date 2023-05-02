package geometries;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
/**
 * Triangle extends Polygon
 */

public class Triangle extends Polygon {

    /**
     * constructor
     *
     * @param p1 type point
     * @param p2 type point
     * @param p3 type point
     */

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * print Triangle's vertices and plane
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * get Normal
     *
     * @param point
     * @return normal vector from the point to the Triangle
     */

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    public List<Point> findIntersections(Ray ray) {
        List<Point> l = vertices;
        Vector v1 = l.get(0).subtract(ray.getP0());
        Vector v2 = l.get(1).subtract(ray.getP0());
        Vector v3 = l.get(2).subtract(ray.getP0());
        Vector n1 = (v1.crossProduct(v2)).normalize();//new Vector(0.001,0.001,0.001);
        Vector n2 = (v2.crossProduct(v3)).normalize();//new Vector(0.001,0.001,0.001);
        Vector n3 = (v3.crossProduct(v1)).normalize();//new Vector(0.001,0.001,0.001);
     /*  try {
            n1= (v1.crossProduct(v2)).normalize();
            n2= (v2.crossProduct(v3)).normalize();
            n3= (v3.crossProduct(v1)).normalize();
        }
        catch (IllegalArgumentException e){}*/
        double num1 = n1.dotProduct(ray.getDir());
        double num2 = n2.dotProduct(ray.getDir());
        double num3 = n3.dotProduct(ray.getDir());

        // if there is an intersection point inside the triangle
        if ((num1 > 0 && num2 > 0 && num3 > 0) || (num1 < 0 && num2 < 0 && num3 < 0)) {
            // Plane pl = new Plane(l.get(0), l.get(1), l.get(2));
            List<Point> geoPoints = new LinkedList<>();
            if (plane.findIntersections(ray) != null)
                for (Point pt : plane.findIntersections(ray)) {
                    geoPoints.add(pt);
                }
            return geoPoints;
        }
        return null; // there isn't an intersection point inside the triangle
    }
}

