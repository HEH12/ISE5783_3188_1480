package renderer;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.MissingResourceException;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * camera protracting rays thought a view plane
 */
public class Camera {

    private final Point p0;//camera eye
    private final Vector vTo; //vector pointing towards the scene
    private final Vector vUp; //vector pointing upwards
    private final Vector vRight; //vector pointing towards the right
    private double distance;
    private double width; // width for view plane
    private double height;// height for view plane

    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    /**
     * constructor
     * @param p0 center of the camera
     * @param vTo dir camera
     * @param vUp Camera location
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vTo and vUp should be orthogonal.");
        }
        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();

        vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * set view plane Distance
     * @param distance
     * @return the distance
     */
    public Camera setViewPlaneDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * set view plane Size
     * @param width for view plane
     * @param height for view plane
     * @return width and height
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Constructing a ray through a pixel.
     * @param Nx pixels of the width in the view plane
     * @param Ny pixels of the height in the view plane
     * @return ray from the camera to pixel[i, j]
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //image center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width and height)
        double Ry = alignZero(height / Ny);
        double Rx = alignZero(width / Nx);

        //pixel [i, j] center
        Point Pij = Pc;

        //delta values for going to pixel[i,j] from pc.

        double yI = alignZero(-(i - (Ny - 1) / 2d) * Ry);
        double xJ = alignZero((j - (Nx - 1) / 2d) * Rx);

        if (!isZero(xJ)) {
            Pij = Pij.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }

        return new Ray(p0, Pij.subtract(p0));
    }

    /**
     * set ImageWriter
     * @param imageWriter
     * @return imageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set RayTracer
     * @param rayTracer
     * @return RayTracer
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracerBase = rayTracer;
        return this;
    }

    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("missing imagewriter", "Camera", "in print Grid");
        }
        imageWriter.writeToImage();
    }

    /**
     * paints the image as a grid according to the wanted interval and color of grid lines
     * @param interval length of wanted interval
     * @param color wanted color for grid lines
     */
    public void printGrid(int interval, Color color) {

        if (imageWriter == null) {
            throw new MissingResourceException("missing imagewriter", "Camera", "in print Grid");
        }

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
            imageWriter.writeToImage();
        }
    }


    /**
     * The actual rendering function , according to data received from the ray tracer - colours each
     * pixel appropriately thus
     * rendering the image
     */
    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(),
                        "");
            }
            if (rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(),
                        "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            Ray ray;
            Color pixelColor;
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    ray = constructRay(nX, nY, i, j);
                    pixelColor = rayTracerBase.traceRay(ray);
                    imageWriter.writePixel(i, j, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }
}


