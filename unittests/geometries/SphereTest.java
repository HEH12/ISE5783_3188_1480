package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     * Test method for {@link Sphere#GetNormal}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sphere = new Sphere(new Point(0, 0, 1), 1.0);
        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 2)),
                "Error: Sphere getNormal not returning correct value");
    }

    /**
     * Test method for {@link Sphere#findIntersections(Ray ray)}.
     */
    @org.junit.jupiter.api.Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0), 1.0);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(
                new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(List.of(p2),
                sphere.findIntersections(new Ray(
                        new Point(0.5, 0.5, 0),
                        new Vector(3, 1, 0))),
                "Ray from inside of the sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(
                sphere.findIntersections(new Ray(new Point(2, 1, 0)
                        , new Vector(3, 1, 0))),
                "Sphere behind the Ray");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC1: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(1, 1, 0))),
                "Ray inside the Sphere");
        // TC2: Ray starts at sphere and goes outside (0 points)
        assertNull(
                sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 0))),
                "Ray outside from sphere");

        // **** Group: Ray's line goes at the zero point
        // TC3: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals(2, result.size(), "Wrong amount of points");
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(new Point(1, -1, 0), new Point(1, 1, 0)), result,
                "line at the zero point, ray crosses sphere");
        // TC4: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, 1, 0))),
                "line at the zero point, ray from and crosses sphere");
        // TC5: Ray starts inside (1 points)
        assertEquals(List.of(new Point(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, 1, 0))),
                "line at the zero point, ray from inside sphere");
        // TC6: Ray starts at the center (1 points)
        assertEquals(List.of(new Point(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, 0))),
                "line at the zero point, ray from O");
        // TC7: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "line at the zero point, ray from sphere outside");
        // TC8: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 1, 0))),
                "line at the zero point, ray outside sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC9: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray before sphere");
        // TC10: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray at sphere");
        // TC11: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "Tangent line, ray after sphere");

        // **** Group: Special cases
        // TC12: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, 1))),
                "Ray orthogonal to ray head therefor O line");

    }


}