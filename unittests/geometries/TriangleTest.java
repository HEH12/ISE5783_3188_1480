package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link Triangle#GetNormal}
     */
    @Test
    void testGetNormal() {
    }
    @org.junit.jupiter.api.Test
    void testFindIntersections() {


        Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0),
                new Point(-1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line intersects inside Triangle (1 points)
        assertEquals(List.of(new Point(0, 0, 0.5)),
                triangle.findIntersections(
                        new Ray(new Point(0, 2, 0.5), new Vector(0, -1, 0))),
                "Ray intersects triangle - should return one point");


        // TC02: Ray's line intersects inside Triangle against edge (0 points)
        assertNull(triangle.findIntersections(
                        (new Ray(new Point(0.5, -2, -1), new Vector(0, 1, 0)))),
                "Ray intersects triangle against edge - should return null");

        // TC03: Ray's line intersects inside Triangle against vertex (0 points)
        assertNull(triangle.findIntersections(
                        (new Ray(new Point(1.5, -2, -0.2), new Vector(0, 1, 0)))),
                "Ray intersects triangle against vertex - should return null");

        // =============== Boundary Values Tests ==================

        // TC04: Ray's line intersects Triangle's edge  (0 points)
        assertNull(
                triangle.findIntersections((new Ray(new Point(0.5, -2, 0), new Vector(0, 1, 0)))),
                "Ray's line intersects Triangle's edge - should return null");

        // TC05: Ray's line intersects Triangle's vertex  (0 points)
        assertNull(triangle.findIntersections((new Ray(new Point(1, -1, 0), new Vector(0, 1, 0))))
                , "Ray's line intersects Triangle's vertex - should return null");

        // TC06: Ray's line intersects Triangle's edge outside Triangle  (0 points)
        assertNull(triangle.findIntersections((new Ray(new Point(2, -2, 0), new Vector(0, 1, 0))))
                , "Ray's line intersects Triangle's outside Triangle - should return null");
    }
}