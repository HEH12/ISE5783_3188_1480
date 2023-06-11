package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import geometries.Intersectable.*;

/**
 *@author Hadas Holtzberg & Zehavi Perla
 */
public class Ray {

    /**
     * DELTA value to move the point away from original point
     */
    private static final double DELTA = 0.1;

    private final Point p0;
    private final Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructor for ray deflected by DELTA
     *
     * @param p origin
     * @param n   normal vector
     * @param dir direction
     */
    public Ray(Point p, Vector n, Vector dir) {
        this.dir = dir.normalize();
        double nv = n.dotProduct(this.dir);
        Vector delta  =n.scale(DELTA);
        if (nv < 0)
            delta = delta.scale(-1);
        this.p0 = p.add(delta);
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point getPoint(double delta) {
        if (isZero(delta)) {
            return p0;
        }
        return p0.add(dir.scale(delta));
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        GeoPoint closestpoint = null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;

        for (GeoPoint geoPoint : intersections) {
            ptDistance = geoPoint.point.distanceSquared(p0);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                closestpoint = geoPoint;
            }
        }
        return closestpoint;
    }

    public Point findClosestPoint(List<Point> intersections) {
        return intersections == null || intersections.isEmpty()
                ? null
                : findClosestGeoPoint(intersections.stream()
                .map(p -> new GeoPoint(null, p))
                .toList()
        ).point;
    }
}