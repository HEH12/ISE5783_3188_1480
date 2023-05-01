package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

public interface Intersectable {
    /**
     * Find intersections of a Ray with the Object(s)
     * @param ray The Ray to intersect
     * @return List of intersection points
     */
    List<Point> findIntersections(Ray ray);
}
