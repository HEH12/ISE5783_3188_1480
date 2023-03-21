package geometries;
/**class geometries is the class representing to be an interface to our geometric shapes
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
import primitives.Point;
import primitives.Vector;
/**Params: point
 * Returns: normal from the point to geometries**/
public interface Geometry {

    public Vector getNormal(Point point);
}