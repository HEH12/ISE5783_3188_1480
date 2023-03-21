package geometries;
/**class geometries is the class representing to be an interface to our geometric shapes
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Vector;

public interface Geometry {

    /**
     * @param point
     * @return normal from the point to geometrries
     */
    public Vector getNormal(Point point);
}