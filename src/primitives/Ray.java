package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;

/**
 * Class represents a Ray
 */
public class Ray {
    private static final double DELTA = 0.1;
    /**
     * Field represents the head of the ray
     */
    final Point point;
    /**
     * Field represents the direction of the ray
     */
    final Vector direction;

    /**
     * constructor for ray
     *
     * @param point     parameter for point
     * @param direction parameter for direction
     */
    public Ray(Point point, Vector direction) {
        this.point = point;
        this.direction = direction.normalize();
    }

    /**
     * construct a ray and move point slightly
     *
     * @param point     the point
     * @param direction direction vector
     * @param n         normal
     */
    public Ray(Point point, Vector direction, Vector n) {
        double nv = alignZero(direction.dotProduct(n));
        if (nv < 0) {
            this.point = point.add(n.scale(-DELTA));

        } else {
            this.point = point.add(n.scale(DELTA));
        }
        this.direction = direction.normalize();
    }

    /**
     * Finds the closet Point that is intersected
     *
     * @param points the list of points in which to find the closest one
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closet GeoPoint that is intersected
     *
     * @param geoPointList the list of geoPoints in which to find the closest one
     * @return the closest geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null)
            return null;

        GeoPoint result = null;
        double distance = Double.MAX_VALUE;
        double d;
        for (var pt : geoPointList) {
            d = pt.point.distance(point);
            if (d < distance) {
                distance = d;
                result = pt;
            }
        }
        return result;
    }

    public Vector getDirection() {
        return direction;
    }

    public Point getPoint() {
        return point;
    }

    public Point getPoint(double d) {
        return point.add(direction.scale(d));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return point.equals(ray.point) && direction.equals(ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "point=" + point +
                ", direction=" + direction +
                '}';
    }

}
