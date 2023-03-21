/**class Cylinder is extends from tube
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**@author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
/**
 * Cylinder extends Tube
 */
public class Cylinder extends Tube {

    public double height;

    /**
     * constructor
     *  radius for the Cylinder
     * ray for the Cylinder
     *  height for the Cylinder
     */
    public Cylinder(double radius, Ray ray, double height) {
        super(radius , ray);
        this.height = height;
    }

    /**
     * normul for Cylinder
     *  point
     * return normal to Cylinder
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    /**
     * get height
     * return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * print Cylinder's height, radius and ray
     */
    @Override
    public String toString() {
        return "Cylinder : " +
                "height=" + height +
                ", radius=" + radius +
                ", ray=" + _ray;
    }
}

