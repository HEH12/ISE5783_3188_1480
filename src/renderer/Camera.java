package renderer;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Class represents a camera at a certain POV
 */
public class Camera {
    /**
     * Field represents the point where the camera is
     */
    private Point p0;

    /**
     * Vector from the camera to the front
     */
    private Vector vTo;
    /**
     * Vector from the camera up
     */
    private Vector vUp;
    /**
     * Vector from the camera to the right
     */
    private Vector vRight;

    /**
     * Distance of the view plane
     */
    private double distance = 10.0D;
    /**
     * Width of the view plane
     */
    private double width = 500.0D;
    /**
     * Height of the plane
     */
    private double height = 500.0D;

    /**
     * Field for image writer
     */
    private ImageWriter imageWriter;
    /**
     * Field for ray tracer
     */
    private RayTracerBase rayTracerBase;

    /**
     * Switch for anti-aliasing
     */
    private boolean isAntiAliasing = false;
    /**
     * Number of antialiasing rays to construct (if applicable)
     */
    private int numOfAARays = 10;

    /**
     * Turns multithreading on/off
     */
    private boolean isMultithreading = false;
    /**
     * How many threads to use
     */
    private int numOfThreads = 1;

    /**
     * Turns adaptive super-sampling on/off
     */
    private boolean isAdaptive = false;

    /**
     * Constructor for Camera
     *
     * @param p0  parameter for p0 field
     * @param vTo parameter for vTo field
     * @param vUp parameter for vUp field
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!Util.isZero(vTo.dotProduct(vUp))) {
            throw new ArithmeticException("the 2 vectors should be orthogonal");
        } else {
            this.p0 = p0;
            this.vTo = vTo.normalize();
            this.vUp = vUp.normalize();
            this.vRight = vTo.crossProduct(vUp);
        }
    }

    //getters
    public Point getP0() {
        return this.p0;
    }

    public Vector getvTo() {
        return this.vTo;
    }

    public Vector getvUp() {
        return this.vUp;
    }

    public Vector getvRight() {
        return this.vRight;
    }

    public double getDistance() {
        return this.distance;
    }

    /**
     * Set p0
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public void setP0(double x, double y, double z) {
        this.p0 = new Point(x, y, z);
    }

    /**
     * Setter of builder patterns
     * set view plane distance
     *
     * @param distance param for distance
     * @return camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Setter of builder patterns
     * set View Plane size
     *
     * @param width  physical width of view plane
     * @param height physical height of view plane
     * @return camera object
     */
    public Camera setVPSize(int width, int height) {
        this.width = (double) width;
        this.height = (double) height;
        return this;
    }

    /**
     * Setter of builder patterns
     * Set image writer
     *
     * @param ImageWriter parameter for imgaeWriter
     * @return Camera object
     */
    public Camera setImageWriter(ImageWriter ImageWriter) {
        this.imageWriter = ImageWriter;
        return this;
    }

    /**
     * Setter of builder patterns
     * set ray tracer
     *
     * @param rayTracerBasic parameter for rayTracerBasic
     * @return Camera object
     */
    public Camera setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBase = rayTracerBasic;
        return this;
    }

    /**
     * Turn anti-aliasing on/off
     *
     * @param flag false for off, true for on
     * @return Camera object
     */
    public Camera useAntiAliasing(boolean flag) {
        isAntiAliasing = flag;
        return this;
    }

    /**
     * Setter of builder patterns
     * set number of antialiasing rays
     *
     * @param num the number
     * @return Camera object
     */
    public Camera setNumOfAARays(int num) {
        numOfAARays = num;
        return this;
    }

    /**
     * Turn adaptive on/off
     *
     * @param flag false for off, true for on
     * @return Camera object
     */
    public Camera useAdaptive(boolean flag) {
        isAdaptive = flag;
        return this;
    }

    /**
     * Setter of builder patters
     * sets the multithreading
     * If set to 1, the render won't use the thread pool.
     * If set to 0, the thread pool will pick the number of threads.
     *
     * @param threads number of threads to use
     * @return the current render
     * @throws IllegalArgumentException when threads is less than 0
     */
    public Camera setMultithreading(int threads) {
        if (threads < 0)//threads cannot be less than zero
            throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");

        isMultithreading = true;

        if (threads != 0)//any number that is not zero is acceptable
            numOfThreads = threads;

        else {//if number received was zero:
            int cores = Runtime.getRuntime().availableProcessors() - 2; //leave 2 spare threads
            numOfThreads = cores <= 2 ? 1 : cores;//if cores is smaller than 2 than threads is 1, i=otherwise it is equal to cores
        }
        return this;
    }

    //**************Camera rotation bonus*************//

    /**
     * Adds the given amount to the camera's position
     *
     * @return the current camera
     */
    public Camera move(Vector amount) {
        p0 = p0.add(amount);
        return this;
    }

    /**
     * Adds x, y, z to the camera's position
     *
     * @return the current camera
     */
    public Camera move(double x, double y, double z) {
        return move(new Vector(x, y, z));
    }

    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param amount vector of angles
     * @return the current camera
     */
    public Camera rotate(Vector amount) {
        return rotate(amount.getX(), amount.getY(), amount.getZ());
    }

    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param x angles to rotate around the x axis
     * @param y angles to rotate around the y axis
     * @param z angles to rotate around the z axis
     * @return the current camera
     */
    public Camera rotate(double x, double y, double z) {
        vTo = vTo.rotateX(x).rotateY(y).rotateZ(z);
        vUp = vUp.rotateX(x).rotateY(y).rotateZ(z);
        vRight = vTo.crossProduct(vUp);

        return this;
    }
    //**************************************************//

    /**
     * function that get the color of each point in
     * the view plane and paint it .
     */
    public Camera renderImage() {
        // if one of the fields hasn't been initialized throw an exception
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }
        if (rayTracerBase == null) {
            throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        //if multithreading is set, render image with multithreads
        if (isMultithreading) {
            Pixel.initialize(nX, nY, 5);

            Pixel p = new Pixel();
            //create thread foreach ray calculation
            Thread[] threads = new Thread[numOfThreads];
            for (int i = numOfThreads - 1; i >= 0; --i) {
                threads[i] = new Thread(() -> {
                    while (p.nextPixel()) {
                        renderHelper(nX, nY, p.col, p.row);
                        Pixel.pixelDone();
                    }
                });
            }
            // Start threads
            for (Thread thread : threads) thread.start();
            Pixel.waitToFinish();
            // Wait for all threads to finish
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (Exception e) {
                }
            }
        }

        //if anti aliasing is not set, construct one ray per pixel
        else if (!isAntiAliasing) {
            //go over all the pixels
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    // construct a ray through the current pixel
                    Ray ray = this.constructRayThroughCenter(nX, nY, j, i);
                    // get the  color of the point from trace ray
                    Color color = rayTracerBase.traceRay(ray);
                    // write the pixel color to the image
                    imageWriter.writePixel(j, i, color);
                }
            }
        }

        //otherwise, do antialiasing without adaptive
        else if (!isAdaptive) {
            //go over all the pixels
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    // construct a ray through the current pixel
                    List<Ray> rays = this.constructAntiAliasingRays(nX, nY, j, i);
                    // get the  color of the point from trace ray
                    Color color = rayTracerBase.traceRays(rays);
                    // write the pixel color to the image
                    imageWriter.writePixel(j, i, color);
                }
            }
        }

        //otherwise use adaptive supersampling
        else {
            //go over all the pixels
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    // construct a ray through the current pixel
                    List<Ray> rays = this.constructAntiAliasingRays(nX, nY, j, i);
                    // get the  color of the point from trace ray
                    Color color = rayTracerBase.adaptiveTraceRays(rays);
                    // write the pixel color to the image
                    imageWriter.writePixel(j, i, color);
                }
            }
        }

        return this;
    }

    /**
     * Helper method for rendering image
     * renders a given pixel
     *
     * @param nX number of columns
     * @param nY number of rows
     * @param j  column of the pixel
     * @param i  row of the pixel
     */
    private void renderHelper(int nX, int nY, int j, int i) {
        //if anti aliasing is not set, construct one ray per pixel
        if (!isAntiAliasing) {
            // construct a ray through the current pixel
            Ray ray = this.constructRayThroughCenter(nX, nY, j, i);
            // get the  color of the point from trace ray
            Color color = rayTracerBase.traceRay(ray);
            // write the pixel color to the image
            imageWriter.writePixel(j, i, color);
        } else if (!isAdaptive) {
            // construct a ray through the current pixel
            List<Ray> rays = this.constructAntiAliasingRays(nX, nY, j, i);
            // get the  color of the point from trace ray
            Color color = rayTracerBase.traceRays(rays);
            // write the pixel color to the image
            imageWriter.writePixel(j, i, color);
        }

        //otherwise, do antialiasing
        else {
            // construct a ray through the current pixel
            List<Ray> rays = this.constructAntiAliasingRays(nX, nY, j, i);
            // get the  color of the point from trace ray
            Color color = rayTracerBase.adaptiveTraceRays(rays);
            // write the pixel color to the image
            imageWriter.writePixel(j, i, color);
        }
    }

    /**
     * Constructing a ray through the center of a pixel
     *
     * @param nX amount of columns
     * @param nY amount of rows
     * @param j  index of column pixel
     * @param i  index of row pixel
     * @return Ray from camera's p0 to the view plane (i,j)
     */
    public Ray constructRayThroughCenter(int nX, int nY, int j, int i) {
        Point Pij = getPixelCenter(nX, nY, j, i);

        //return Ray{p0=p0, direction=pij-p0}
        return new Ray(this.p0, Pij.subtract(this.p0));
    }

    /**
     * Helper function to find the center of a pixel
     *
     * @param nX number of columns
     * @param nY number of rows
     * @param j  j index of pixel
     * @param i  i index of pixel
     * @return the center of the pixel
     */
    private Point getPixelCenter(int nX, int nY, int j, int i) {
        //image center
        Point pC = this.p0.add(this.vTo.scale(this.distance));

        // Ratio (pixel width and height)
        double Ry = this.height / (double) nY;
        double Rx = this.width / (double) nX;

        //pixel[i,j] center
        Point Pij = pC;
        double Yi = -((double) i - (double) (nY - 1) / 2.0D) * Ry;
        double Xj = ((double) j - (double) (nX - 1) / 2.0D) * Rx;

        if (!Util.isZero(Yi)) {
            Pij = pC.add(this.vUp.scale(Yi));
        }

        if (!Util.isZero(Xj)) {
            Pij = Pij.add(this.vRight.scale(Xj));
        }

        return Pij;
    }

    /**
     * Construct rays for antialiasing
     *
     * @param nX amount of columns
     * @param nY amount of rows
     * @param j  index of column pixel
     * @param i  index of row pixel
     * @return
     */
    private List<Ray> constructAntiAliasingRays(int nX, int nY, int j, int i) {
        //The distance between the screen and the camera cannot be 0
        if (isZero(this.distance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        List<Ray> sample_rays = new ArrayList<>();

        double Ry = this.height / nY; //The number of pixels on the y axis
        double Rx = this.width / nX;  //The number of pixels on the x axis

        double yi = ((i - nY / 2d) * Ry); //distance of original pixel from (0,0) on Y axis
        double xj = ((j - nX / 2d) * Rx); //distance of original pixel from (0,0) on x axis

        double pixel_Ry = Ry / numOfAARays; //The height of each grid block we divided the parcel into
        double pixel_Rx = Rx / numOfAARays; //The width of each grid block we divided the parcel into

        for (int row = 0; row < numOfAARays; ++row) {//foreach place in the pixel grid
            for (int column = 0; column < numOfAARays; ++column) {
                sample_rays.add(constructAntiAliasingRay(pixel_Ry, pixel_Rx, yi, xj, row, column, distance));//add the ray
            }
        }
        sample_rays.add(constructRayThroughCenter(nX, nY, j, i));//add the center screen ray
        return sample_rays;
    }

    /**
     * Helper function for anti aliasing
     * In this function we treat each pixel like a little screen of its own and divide it to smaller "pixels".
     * Through each one we construct a ray. This function is similar to ConstructRayThroughPixel.
     *
     * @param Ry       height of each grid block we divided the pixel into
     * @param Rx       width of each grid block we divided the pixel into
     * @param yi       distance of original pixel from (0,0) on Y axis
     * @param xj       distance of original pixel from (0,0) on X axis
     * @param j        j coordinate of small "pixel"
     * @param i        i coordinate of small "pixel"
     * @param distance distance of screen from camera
     * @return beam of rays through pixel
     */
    private Ray constructAntiAliasingRay(double Ry, double Rx, double yi, double xj, int j, int i, double distance) {
        Point Pc = p0.add(vTo.scale(distance)); //the center of the screen point

        double y_sample_i = (i * Ry + Ry / 2d); //The pixel starting point on the y axis
        double x_sample_j = (j * Rx + Rx / 2d); //The pixel starting point on the x axis

        Point Pij = Pc; //The point at the pixel through which a beam is fired
        //Moving the point through which a beam is fired on the x axis
        if (!Util.isZero(x_sample_j + xj)) {
            Pij = Pij.add(vRight.scale(x_sample_j + xj));
        }
        //Moving the point through which a beam is fired on the y axis
        if (!Util.isZero(y_sample_i + yi)) {
            Pij = Pij.add(vUp.scale(-y_sample_i - yi));
        }
        Vector Vij = Pij.subtract(p0);
        return new Ray(p0, Vij);//create the ray throw the point we calculate here
    }

    /**
     * function that create the grid
     *
     * @param interval the interval between grids
     * @param color    the color for the grid
     */
    public void printGrid(int interval, Color color) {
        // if imageWriter hasn't been initialized throw an exception
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }

        imageWriter.printGrid(interval, color);
    }

    /**
     * Function renderImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        // if imageWriter hasn't been initialized throw an exception
        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        }

        imageWriter.writeToImage();
    }
}


