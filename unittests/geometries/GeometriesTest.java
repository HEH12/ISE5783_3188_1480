package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {


    /**
     * Test method for {@link Geometries#findIntersections(Ray)}
     */

    @Test
    void TestFindIntersections() {

        Geometries geometriesListOne = new Geometries();

        // =============== Boundary Values Tests ==================

        // TC01: Empty geometry list (0 points)
        assertNull( geometriesListOne.findIntersections(new Ray(new Point(1, 1, 1), new Vector(2, 1, 1)))
                , "Empty geometries - should return null");

        geometriesListOne.add(new Sphere(new Point(1, 0, 0), 1.0));
        geometriesListOne.add(new Plane(new Point(1, 1, 0), new Vector(0, 0, 1)));
        geometriesListOne.add(new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)));

        //TC02: No intersections with any geometry (0 points)
        assertNull(geometriesListOne.findIntersections(new Ray
                        (new Point(0.0, 0.0, 2.0),
                                new Vector(0.0, -1.0, 0.0))),
                "No intersections - should return null");

        //TC03: One intersection with one geometry (1 point)
        assertEquals(1, geometriesListOne.findIntersections(new Ray
                        (new Point(0, 5, -1),
                                new Vector(0, 0, 1))).size(),
                "Wrong number of intersections - expected 1");

        //TC04: Intersections with all geometries (4 points)
        Geometries geometriesListTwo = new Geometries();
        geometriesListTwo.add(new Sphere(new Point(0,0,3), 2.0));
        geometriesListTwo.add(new Plane(new Point(0, 0, 7), new Vector(0,0,1)));
        geometriesListTwo.add(new Triangle(new Point(-1, -1, 8), new Point(2,0,8), new Point(-1,1,8)));

        assertEquals( 3, geometriesListTwo.findIntersections(new Ray
                        ( new Point(0,0,0.5),
                                new Vector(0, 0, 1))).size(),
                "Wrong number of intersections - expected 3");



        // ============ Equivalence Partitions Tests ==============

        // TC05: Few Geometries are intersected, but not all of them (2 points)
        assertEquals(2, geometriesListOne.findIntersections(new Ray
                        (new Point(1.0, 0.0, -1.0),
                                new Vector(0.0, 0.0, 1.0))).size(),
                "Wrong number of intersections - expected 2");

    }
}