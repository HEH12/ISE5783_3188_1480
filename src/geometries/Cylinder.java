/**@author Hadas Holtzberg 326133188 hadashol123@gmail.com and Zehavi Perla 326381480 zehaviperla@gmail.com**/
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Cylinder extends Tube
 */

public class Cylinder extends Tube {

    public double height;

    /**
     * constructor
     * @param radius for the Cylinder
     * @param ray for the Cylinder
     * @param height for the Cylinder
     */

    public Cylinder(double radius, Ray ray, double height) {
        super(radius , ray);
        this.height = height;
    }

    /**
     * normul for Cylinder
     * @param point
     * @return normal to Cylinder
     */

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    /**
     * get height
     * @return height
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
                ", ray=" + ray;
    }
}

