package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {

    private final Vector vTo;
    private final Vector vUp;
    private final Point p0;
    private final Vector vRight;
    private int distance;
    private int width;
    private int height;

    public Camera(Point p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        this.vTo = vTo;
        this.vUp = vUp;

        vRight = vTo.crossProduct(vUp).normalize();
    }

    public Camera setViewPlaneDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public Camera setViewPlaneSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }


    /**
     * Constructing a ray through a pixel.
     *
     * @param nx pixels of the width in the view plane
     * @param ny pixels of the height in the view plane
     * @return ray from the camera to pixel[i, j]
     */
    public Ray constructRay(int nx, int ny, int j, int i)
    {
        //image center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width and height)

        double Ry = height / ny;
        double Rx = width / nx;

        //pixel [i, j] center
        Point Pij = Pc;

        //delta values for going to pixel[i,j] from pc.

        double yI = -(i - (ny - 1) / 2d) * Ry;
        double xJ = (j - (nx - 1) / 2d) * Rx;

        if (!isZero(xJ)) {
            Pij = Pij.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }

        return new Ray(p0, Pij.subtract(p0));
    }
}
