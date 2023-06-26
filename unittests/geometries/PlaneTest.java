package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}
     */
    void testConstructor() {

        // ============ Equivalence Partitions Tests ==============

        try {
            Plane plane = new Plane(new Point(0, 1, 1),
                    new Point(-2, 1, 0),
                    new Point(5, 0, 2));
        } catch (IllegalArgumentException e) {
            fail("failed constructing plane");
        }
        // TC01: first point and second point are same
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(1, 2, 1)),
                "Constructed a plane with tow equal points");
        // TC02: three point are on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(2, 1, 0), new Point(5, 0, 3),
                        new Point(3.5, 0.5, 1.5)),
                "Constructed a plane with tow equal points");
    }



    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check if getNormal calculated correct
        Plane plane = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), plane.getNormal(new Point(0, 0, 1)),
                "Error: Plane getNormal not returning correct value");
        // TC02: the test check if the length of normal different from 1
        assertEquals(1, plane.getNormal().length(), "ERROR: the length different from 1");
    }

    /**
     * Test method for {@link Plane#getNormal(primitives.Point)}.
     */
    @org.junit.jupiter.api.Test
    void testfindIntsersections() {
        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point(1, 0, 0)),
                pl.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC3: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC4: Ray in plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, .5), new Vector(0, 1, -1))),
                "Must not be plane intersection");


        // TC5: Orthogonal ray into plane
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC6: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC7: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC8: Orthogonal ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC9: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC10: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 0))),
                "Must not be plane intersection");
    }



}