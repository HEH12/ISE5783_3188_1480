package renderer;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IntegrationTest {

    /**
     * Integration tests of Camera Ray with Ray-Sphere intersections.
     * all the test were taken from the presentation of the pre-curse.
     */


    //create camera
    Camera camera1 = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(3, 3)
            .setVPDistance(1);
    Camera camera2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(3, 3)
            .setVPDistance(1);

    /**
     * Sphere integration
     */

    @Test
    void testIntegrationCameraSphere() {
        //create sphere
        Sphere sphere1 = new Sphere(new Point(0, 0, -3), 1);
        // TC01: The sphere is small and far from the VP => 2 points
        assertEquals(2, countIntersection(camera1, sphere1, 3, 3),
                "testIntegrationCameraSphere=> should return 2 point");
        // TC02:The sphere is big and close => 18 points
        Sphere sphere2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, countIntersection(camera2, sphere2, 3, 3),
                "testIntegrationCameraSphere=> should return 18 point");
        // TC03: The sphere is medium => 10 points
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, countIntersection(camera2, sphere3, 3, 3),
                "testIntegrationCameraSphere=> should return 10 point");
        // TC04: The camera is inside the Sphere => 9 points
        Sphere sphere4 = new Sphere(new Point(0, 0, -1), 4);
        assertEquals(9, countIntersection(camera2, sphere4, 3, 3),
                "testIntegrationCameraSphere=> should return 9 point");
        // TC05: The sphere is behind the camera => 0 points
        Sphere sphere5 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, countIntersection(camera1, sphere5, 3, 3),
                "testIntegrationCameraSphere=> should return 0 point");
    }

    /**
     * Triangle integrations
     */

    @Test
    void testIntegrationCameraTriangle() {
        // TC01: The triangle is small => 1 point
        Triangle triangle1 = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertEquals(1, countIntersection(camera1, triangle1, 3, 3),
                "testIntegrationCameraTriangle => should return 1 point");
        // TC02:  The triangle is medium triangle => 2 points
        Triangle triangle2 = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertEquals(2, countIntersection(camera1, triangle2, 3, 3),
                "testIntegrationCameraTriangle => should return 2 point");
    }


    @Test
    void testIntegrationCameraPlane() {
        // TC01: The plane is against camera => 9 points
        Plane plane1 = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1));
        assertEquals(9, countIntersection(camera1, plane1, 3, 3),
                "testIntegrationCameraPlane => should return 9 point");

        // TC02: The Plane is with small angle => 9 points
        Plane plane2 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 2));
        assertEquals(9, countIntersection(camera1, plane2, 3, 3),
                "testIntegrationCameraPlane => should return 9 point");

        // TC03: The plane is parallel to lower rays => 6 points
        Plane plane3 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        assertEquals(6, countIntersection(camera1, plane3, 3, 3),
                "testIntegrationCameraPlane => should return 6 point");


        // TC04: The plane is behind the camera => 0 points
        Plane plane4 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        assertEquals(6, countIntersection(camera1, plane4, 3, 3),
                "testIntegrationCameraPlane => should return 6 point");
    }

    /**
     * @param camera that construct the rays
     * @param nX row of the view plane
     * @param nY cow of the view plane
     * @return count the intersection from the camera to the geometry
     */
    private int countIntersection(Camera camera, Geometry geometry, int nX, int nY) {
        int countIntersectionOfGeometries = 0;//count the intersection with the geometry
        for (int i = 0; i < nX; i++) {        //row
            for (int j = 0; j < nY; j++) {    //cow
                Ray ray = camera.constructRay(nX, nY, j, i); //create ray to pixel
                var intersection = geometry.findIntersections(
                        ray); // find Intersections to the geometry with the ray
                if (intersection != null) { //if there is intersection
                    countIntersectionOfGeometries += intersection.size(); //count it
                }
            }
        }
        return countIntersectionOfGeometries;
    }
}
