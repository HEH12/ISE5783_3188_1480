package primitives;

import org.junit.jupiter.api.Test;
import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    /**
     * Test method for {@link Vector#Add}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: adding vector to vector
        assertEquals(new Vector(1, 5, 1), v1.add(v3),
                "Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC1: test adding v + (-v)
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(new Vector(-1, -2, -3)),
                "Add v plus -v must throw exception");
    }

    /**
     * Test method for {@link Vector#Scale}
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: scale simple vector, positive scalar
        assertEquals(new Vector(2, 4, 6),
                v1.scale(2),
                "Wrong vector scale");
        // ============ Equivalence Partitions Tests ==============
        // TC02: scale simple vector, negative scalar
        assertEquals(new Vector(-2, -4, -6),
                v1.scale(-2),
                "Wrong vector scale");
        // =============== Boundary Values Tests ==================
        // TC1: test scaling to 0
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0d),
                "Scale by 0 must throw exception");

    }

    /**
     * Test method for {@link Vector#DotProduct}
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple dotProduct test
        assertTrue(isZero(v1.dotProduct(v2) + 28),
                "ERROR: dotProduct() wrong value");
        // =============== Boundary Values Tests ==================
        // TC1: dotProduct for orthogonal vectors
        assertEquals(0d, v1.dotProduct(v3), 0.00001,
                "dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link Vector#CrossProduct}
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001,
                "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),
                "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)),
                "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC1: test zero vector from cross-product of co-lined vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#LengthSquared}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:  check length square of vector
        assertEquals(14, v1.lengthSquared(), 0.00001,
                "ERROR: lengthSquared() wrong value");

    }

    /**
     * Test method for {@link Vector#Length}
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:  check length of vector
        assertEquals(5d, new Vector(0, 3, 4).length(),
                "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#Normalize}
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertFalse(v == n, "normalized() changes the vector itself");
        // TC02: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        // TC03: Simple test
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n),
                "normalized vector is not in the same direction");
        // TC04: Simple test
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }

    /**
     * Test method for {@link Vector#Subtract}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: subtract vector to vector
        assertEquals(new Vector(1, -1, 5), v1.subtract(v3),
                "Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC1: test subtract v - v
        assertThrows(IllegalArgumentException.class,
                () -> v1.subtract(v1),
                "Add v minus v must throw exception");
    }
}