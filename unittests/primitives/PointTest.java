package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Hadas and Zehavi
 */

class PointTest {

    Point p1 = new Point(1, 2, 3);
    /**
     * Test method for {@link Point#Subtract}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check subtract between 2 points
        assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1),
                "ERROR: Point - Vector does not work correctly");
    }

    /**
     * Test method for {@link Point#Add}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check adding between point and vector to point
        assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly");

    }

    /**
     * Test method for {@link Point#DistanceSquared}
     */
    @Test
    void testDistanceSquared() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: check distance squared between 2 points
        Point p2 = new Point(3, 4, 5);
        assertEquals(12, p1.distanceSquared(p2), "ERROR: wrong distance squared.");
    }

    /**
     * Test method for {@link Point#Distance}
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check distance between 2 points by using function sqrt
        Point p2 = new Point(3, 4, 5);
        assertEquals(Math.sqrt(12), p1.distance(p2), "ERROR: wrong distance between points.");
    }
}