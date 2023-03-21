package geometries;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Vector;

/**
 * Triangle extends Polygon
 */
public class Triangle extends Polygon{

    /**
     * constructor
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
     * @param point
     * @return normal vector from the point to the Triangle
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}

