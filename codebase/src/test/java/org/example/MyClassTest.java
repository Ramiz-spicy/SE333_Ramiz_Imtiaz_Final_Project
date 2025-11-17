package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Consolidated tests for MyClass.
 */
public class MyClassTest {

    private final MyClass sut = new MyClass();

    @Test
    public void testAdd() {
        assertEquals(5, sut.add(2, 3));
        assertEquals(0, sut.add(-1, 1));
        assertEquals(-5, sut.add(-2, -3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, sut.subtract(3, 2));
        assertEquals(-4, sut.subtract(-1, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(12, sut.multiply(3, 4));
        assertEquals(0, sut.multiply(0, 5));
        assertEquals(-6, sut.multiply(-2, 3));
    }

    @Test
    public void testDivide() {
        assertEquals(2, sut.divide(4, 2));
        assertEquals(0, sut.divide(1, 0)); // branch: b == 0
        assertEquals(-2, sut.divide(-4, 2));
    }

    @Test
    public void testIsPositiveAndEven() {
        assertTrue(sut.isPositive(1));
        assertFalse(sut.isPositive(0));
        assertFalse(sut.isPositive(-1));
        // small sanity check for add()
        assertEquals(2, sut.add(1, 1));

        assertTrue(sut.isEven(2));
        assertTrue(sut.isEven(-2));
        assertFalse(sut.isEven(3));
    }

    @Test
    public void testMax_firstGreater_secondGreater_equal() {
        // cover first>second
        assertEquals(7, sut.max(7, 3));
        // cover second>first
        assertEquals(5, sut.max(3, 5));
        // equal
        assertEquals(5, sut.max(5, 5));
    }

    @Test
    public void testMin_variants() {
        assertEquals(2, sut.min(2, 8));
        assertEquals(2, sut.min(2, 2));
        assertEquals(-5, sut.min(-5, 0));
    }

    @Test
    public void testFactorial() {
        assertEquals(1, sut.factorial(-1)); // n < 2 -> 1
        assertEquals(1, sut.factorial(0));
        assertEquals(1, sut.factorial(1));
        assertEquals(120, sut.factorial(5));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(sut.isEmpty(null));
        assertTrue(sut.isEmpty(""));
        assertFalse(sut.isEmpty("hello"));
    }
}
