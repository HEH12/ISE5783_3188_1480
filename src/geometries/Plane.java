/**class plane
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
package geometries;

import primitives.Point;
import primitives.Vector;



public class Plane implements Geometry{
    final  Point _q0;
    final Vector _normal;
    /**constructor
     Params:
     q0 – type point normal – type vector**/

    public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal.normalize();
    }
/**another constructor
 Params:
 q0 q1 q2**/
    public Plane(Point q0, Point q1, Point q2) {
        _q0 = q0;
        _normal = null;
    }
/**get q0
 Returns:
 q0**/

    public Point getQ0() {
        return _q0;
    }

/**get normal.
 Returns:
 normal.**/
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane : " +
                "point=" + _q0 +
                ", normal=" + _normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}

