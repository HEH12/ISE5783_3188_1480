package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry extends Intersectable {
    /**
     * @return normal from the point to geometries
     */
    Vector getNormal(Point point);
}
