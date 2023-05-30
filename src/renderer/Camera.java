//package renderer;
//
//import static primitives.Util.alignZero;
//import static primitives.Util.isZero;
//
//import java.util.MissingResourceException;
//import primitives.Color;
//import primitives.Point;
//import primitives.Ray;
//import primitives.Vector;
//
///**
// * camera protracting rays thought a view plane
// */
//public class Camera {
//
//    private final Point p0;//camera eye
//    private final Vector vTo; //vector pointing towards the scene
//    private final Vector vUp; //vector pointing upwards
//    private final Vector vRight; //vector pointing towards the right
//    private double distance;
//    private double width; // width for view plane
//    private double height;// height for view plane
//
//    private ImageWriter imageWriter;
//    private RayTracerBase rayTracer;
//
//    /**
//     * constructor
//     * @param p0 center of the camera
//     * @param vTo dir camera
//     * @param vUp Camera location
//     */
//    public Camera(Point p0, Vector vTo, Vector vUp) {
//        if (!isZero(vUp.dotProduct(vTo))) {
//            throw new IllegalArgumentException("vTo and vUp should be orthogonal.");
//        }
//        this.p0 = p0;
//        this.vTo = vTo.normalize();
//        this.vUp = vUp.normalize();
//
//        vRight = this.vTo.crossProduct(this.vUp);
//    }
//
//    /**
//     * set view plane Distance
//     * @param distance
//     * @return the distance
//     */
//    public Camera setViewPlaneDistance(double distance) {
//        this.distance = distance;
//        return this;
//    }
//
//    /**
//     * set view plane Size
//     * @param width for view plane
//     * @param height for view plane
//     * @return width and height
//     */
//    public Camera setViewPlaneSize(double width, double height) {
//        this.width = width;
//        this.height = height;
//        return this;
//    }
//
//    /**
//     * Constructing a ray through a pixel.
//     * @param Nx pixels of the width in the view plane
//     * @param Ny pixels of the height in the view plane
//     * @return ray from the camera to pixel[i, j]
//     */
//    public Ray constructRay(int Nx, int Ny, int j, int i) {
//        //image center
//        Point Pc = p0.add(vTo.scale(distance));
//
//        //Ratio (pixel width and height)
//        double Ry = alignZero(height / Ny);
//        double Rx = alignZero(width / Nx);
//
//        //pixel [i, j] center
//        Point Pij = Pc;
//
//        //delta values for going to pixel[i,j] from pc.
//
//        double yI = alignZero(-(i - (Ny - 1) / 2d) * Ry);
//        double xJ = alignZero((j - (Nx - 1) / 2d) * Rx);
//
//        if (!isZero(xJ)) {
//            Pij = Pij.add(vRight.scale(xJ));
//        }
//        if (!isZero(yI)) {
//            Pij = Pij.add(vUp.scale(yI));
//        }
//
//        return new Ray(p0, Pij.subtract(p0));
//    }
//
//    /**
//     * set ImageWriter
//     * @param imageWriter
//     * @return imageWriter
//     */
//    public Camera setImageWriter(ImageWriter imageWriter) {
//        this.imageWriter = imageWriter;
//        return this;
//    }
//
//    /**
//     * set RayTracer
//     * @param rayTracer
//     * @return RayTracer
//     */
//    public Camera setRayTracer(RayTracerBase rayTracer) {
//        this.rayTracer = rayTracer;
//        return this;
//    }
//
//    /**
//     * Function writeToImage produces unoptimized png file of the image according to
//     * pixel color matrix in the directory of the project
//     */
//    public void writeToImage() {
//        if (imageWriter == null) {
//            throw new MissingResourceException("missing imagewriter", "Camera", "in print Grid");
//        }
//        imageWriter.writeToImage();
//    }
//
//    /**
//     * paints the image as a grid according to the wanted interval and color of grid lines
//     * @param interval length of wanted interval
//     * @param color wanted color for grid lines
//     */
//    public void printGrid(int interval, Color color) {
//
//        if (imageWriter == null) {
//            throw new MissingResourceException("missing imagewriter", "Camera", "in print Grid");
//        }
//
//        for (int i = 0; i < imageWriter.getNx(); i++) {
//            for (int j = 0; j < imageWriter.getNy(); j++) {
//                if (i % interval == 0 || j % interval == 0) {
//                    imageWriter.writePixel(i, j, color);
//                }
//            }
//            imageWriter.writeToImage();
//        }
//    }
//
//
//    /**
//     * The actual rendering function , according to data received from the ray tracer - colours each
//     * pixel appropriately thus
//     * rendering the image
//     */
//    public void renderImage() {
//
////        try {
////            // if one of the fields hasn't been initialized throw an exception
////            if (imageWriter == null) {
////                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
////            }
////            if (rayTracer == null) {
////                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
////            }
////
////            int nX = imageWriter.getNx();
////            int nY = imageWriter.getNy();
////
////            //go over all the pixels
////            for (int i = 0; i < nX; i++) {
////                for (int j = 0; j < nY; j++) {
////                    // construct a ray through the current pixel
////                    Ray ray = this.constructRay(nX, nY, j, i);
////                    // get the  color of the point from trace ray
////                    Color color = rayTracer.traceRay(ray);
////                    // write the pixel color to the image
////                    imageWriter.writePixel(j, i, color);
////                }
////            }
////        } catch (MissingResourceException e) {
////            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
////        }
////    }
////        try {
////            if (imageWriter == null) {
////                throw new MissingResourceException("missing resource", ImageWriter.class.getName(),
////                        "");
////            }
////            if (rayTracer == null) {
////                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(),
////                        "");
////            }
////
////            //rendering the image
////            int nX = imageWriter.getNx();
////            int nY = imageWriter.getNy();
////            Ray ray;
////            Color pixelColor;
////            for (int i = 0; i < nX; i++) {
////                for (int j = 0; j < nY; j++) {
////                    ray = constructRay(nX, nY, i, j);
////                    pixelColor = rayTracer.traceRay(ray);
////                    imageWriter.writePixel(i, j, pixelColor);
////                }
////
////            }
////
////        } catch (MissingResourceException e) {
////            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
////        }
////    }
//}
//
//

        package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.MissingResourceException;

public class Camera {
    /*
    The center point of the camera.
    */
    private Point p0;
    /*
    The up vector of the camera.
    */
    private Vector vUp;
    /*
    The "to" vector of the camera.
    */
    private Vector vTo;
    /*
    The right vector of the camera.
    */
    private Vector vRight;
    /*
    The width of the viewport.
    */
    private double width;
    /*
    The height of the viewport.
    */
    private double height;
    /*
    The distance between the camera and the view plane.
    */
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;



    /**
     Constructs a new camera with the given parameters.
     @param p The center point of the camera.
     @param to The "to" vector of the camera.
     @param up The up vector of the camera.
     @throws IllegalArgumentException If the given up and to vectors are not orthogonal.
     */
    public Camera(Point p,Vector to,Vector up)
    {
        //need to check if the given vectors are orthogonal-if their dotProduct is zero
        if(isZero(up.dotProduct(to))==false)
            throw new IllegalArgumentException("vectors are not orthogonal!");
        //else-orthogonal
        vRight=to.crossProduct(up).normalize();
        vUp=up.normalize();
        vTo=to.normalize();
        p0=p;
    }
    private final String RESOURCE = "Renderer resource not set";
    private final String CAMERA_CLASS = "Camera";
    private final String IMAGE_WRITER = "Image writer";
    private final String CAMERA = "Camera";
    private final String RAY_TRACER = "Ray tracer";
    public Camera renderImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);
        if (p0 == null  || vTo == null  ||vUp == null || vRight == null  ||width == 0  ||height == 0  ||distance == 0)
        throw new MissingResourceException(RESOURCE, CAMERA_CLASS, CAMERA);
        if (rayTracer == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, RAY_TRACER);
        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        for(int i=0;i<nX;i++)
        {
            for(int j=0;j<nY;j++)
            {//for each pixel in the view plane, shoot a ray from the camera to the pixel and color the pixel
                this.imageWriter.writePixel(j, i, castRay(nX, nY, j, i));
            }
        }
        return this;//for builder

    }
    public void printGrid(int interval, Color color)
    {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA_CLASS, IMAGE_WRITER);

        final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }

    }
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }
    public void writeToImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException(RESOURCE, CAMERA, IMAGE_WRITER);
        imageWriter.writeToImage();//delegation!
    }

    private Color castRay(int nX, int nY, int j, int i)
    {
        return this.rayTracer.traceRay(this.constructRay(nX, nY, j, i));
    }
    /**
     Sets the size of the viewport.
     @param w The width of the viewport.
     @param h The height of the viewport.
     @return The camera instance with the updated viewport size.
     */
    public Camera setVPSize(double w,double h)
    {
        this.width=w;
        this.height=h;
        return this;
    }
    /**
     Sets the distance between the camera and the view plane.
     @param d The distance between the camera and the view plane.
     @return The camera instance with the updated distance.
     */
    public Camera setVPDistance(double d)
    {
        this.distance=d;
        return this;
    }


    /**
     * building ray from each pixel's center
     * @param nX size of width of pixel graph
     * @param nY size of  of pixel graph
     * @param j given value for index of wanted pixel
     * @param i given value for index of wanted pixel
     * @return a ray from the center of the pixel to the picture
     */

    public Ray constructRay(int nX,int nY,int j,int i)
    {
        Point pCenterPoint=p0.add(vTo.scale(distance));//p0+d*Vto
        double rY=(height/nY);
        double rX=width/nX;//Ry=h/Ny     Rx=w/Nx
        double yI=-(i-(nY-1d)/2)*rY;
        double xJ=(j-(nX-1d)/2)*rX;
        Point pIJ=pCenterPoint;
        if(xJ!=0)
            pIJ=pIJ.add(vRight.scale(xJ));
        if(yI!=0)
            pIJ=pIJ.add(vUp.scale(yI));
        //Didn't throw an exception
        Vector vIJ=pIJ.subtract(p0);//a vector for the ray
        //we have a point and a vector for the ray
        return new Ray(p0, vIJ);//building a ray and return it


    }
    @Override
    public String toString() {
        return "Camera [p0=" + p0 + ", vUp=" + vUp + ", vTo=" + vTo + ", vRight=" + vRight + ", width=" + width
                + ", height=" + height + ", distance=" + distance + ", imageWriter=" + imageWriter + ", RESOURCE="
                + RESOURCE + ", CAMERA_CLASS=" + CAMERA_CLASS + ", IMAGE_WRITER=" + IMAGE_WRITER + ", CAMERA="
                + CAMERA + ", RAY_TRACER=" + RAY_TRACER + "]";
    }

}