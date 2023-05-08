package primitives;
import java.util.Objects;

import static primitives.Util.isZero;

/**class ray is the basic class representing a ray-vector that started from point p0
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
public class Ray {
    private final Point p0;
    private final Vector dir;
    /**
     * constructor
     * @param p0
     * @param dir
     */

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
    /**
     * getP0
     * @return point
     */

    public Point getP0() {
        return p0;
    }
    /**
     * getDir
     * @return direction of vector
     */

    public Vector getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(getP0(), getDir());
    }

    /**
     * get Point
     * @param delta
     * @return p0 + delta
     */
    public Point getPoint(double delta ){
        if (isZero(delta)){
            return  p0;
        }
        return p0.add(dir.normalize().scale(delta));
    }
}
