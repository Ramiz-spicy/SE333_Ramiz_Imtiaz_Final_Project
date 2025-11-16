package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyClassTest {

    private final MyClass sut = new MyClass();

    @Test
    public void testAdd() {
        assertEquals(5, sut.add(2, 3));
        assertEquals(0, sut.add(-1, 1));
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
    }

    @Test
    public void testIsPositive() {
        assertTrue(sut.isPositive(1));
        assertFalse(sut.isPositive(0));
        assertFalse(sut.isPositive(-1));
    }

    @Test
    public void testIsEven() {
        assertTrue(sut.isEven(2));
        assertTrue(sut.isEven(-2));
        assertFalse(sut.isEven(3));
    }

    @Test
    public void testMaxMin() {
        assertEquals(5, sut.max(3, 5));
        assertEquals(5, sut.max(5, 5));
        assertEquals(2, sut.min(2, 8));
        assertEquals(2, sut.min(2, 2));
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
package org.example;

import org.junit.Test;
import org.example.MyClass;
import static org.junit.Assert.*;

public class MyClassTest {

    private MyClass myClass = new MyClass();

    // Tests for add()
    @Test
    public void test_add_positive_numbers() {
        assertEquals(5, myClass.add(2, 3));
    }

    @Test
    public void test_add_negative_numbers() {
        assertEquals(-5, myClass.add(-2, -3));
    }

    @Test
    public void test_add_mixed_numbers() {
        assertEquals(1, myClass.add(5, -4));
        assertEquals(-1, myClass.add(-5, 4));
    }

    @Test
    public void test_add_with_zero() {
        assertEquals(5, myClass.add(5, 0));
        assertEquals(3, myClass.add(0, 3));
        assertEquals(0, myClass.add(0, 0));
    }

    // Tests for subtract()
    @Test
    public void test_subtract_positive_numbers() {
        assertEquals(-1, myClass.subtract(2, 3));
        assertEquals(1, myClass.subtract(5, 4));
    }

    @Test
    public void test_subtract_negative_numbers() {
        assertEquals(1, myClass.subtract(-2, -3));
    }

    @Test
    public void test_subtract_mixed_numbers() {
        assertEquals(9, myClass.subtract(5, -4));
        assertEquals(-9, myClass.subtract(-5, 4));
    }

    @Test
    public void test_subtract_with_zero() {
        assertEquals(5, myClass.subtract(5, 0));
        assertEquals(-3, myClass.subtract(0, 3));
        assertEquals(0, myClass.subtract(0, 0));
    }

    // Tests for multiply()
    @Test
    public void test_multiply_positive_numbers() {
        assertEquals(6, myClass.multiply(2, 3));
        assertEquals(15, myClass.multiply(5, 3));
    }

    @Test
    public void test_multiply_negative_numbers() {
        assertEquals(6, myClass.multiply(-2, -3));
    }

    @Test
    public void test_multiply_mixed_numbers() {
        assertEquals(-6, myClass.multiply(2, -3));
        assertEquals(-15, myClass.multiply(-5, 3));
    }

    @Test
    public void test_multiply_with_zero() {
        assertEquals(0, myClass.multiply(5, 0));
        assertEquals(0, myClass.multiply(0, 3));
        assertEquals(0, myClass.multiply(0, 0));
    }

    // Tests for divide()
    @Test
    public void test_divide_positive_numbers() {
        assertEquals(2, myClass.divide(6, 3));
        assertEquals(5, myClass.divide(15, 3));
    }

    @Test
    public void test_divide_negative_numbers() {
        assertEquals(2, myClass.divide(-6, -3));
    }

    @Test
    public void test_divide_mixed_numbers() {
        assertEquals(-2, myClass.divide(6, -3));
        assertEquals(-5, myClass.divide(-15, 3));
    }

    @Test
    public void test_divide_by_zero() {
        assertEquals(0, myClass.divide(10, 0));
        assertEquals(0, myClass.divide(-10, 0));
        assertEquals(0, myClass.divide(0, 0));
    }

    @Test
    public void test_divide_zero_dividend() {
        assertEquals(0, myClass.divide(0, 5));
    }

    // Tests for isPositive()
    @Test
    public void test_isPositive_positive_number() {
        assertTrue(myClass.isPositive(1));
        assertTrue(myClass.isPositive(100));
        assertTrue(myClass.isPositive(Integer.MAX_VALUE));
    }

    @Test
    public void test_isPositive_negative_number() {
        assertFalse(myClass.isPositive(-1));
        assertFalse(myClass.isPositive(-100));
        assertFalse(myClass.isPositive(Integer.MIN_VALUE));
    }

    @Test
    public void test_isPositive_zero() {
        assertFalse(myClass.isPositive(0));
    }

    // Tests for isEven()
    @Test
    public void test_isEven_even_positive_numbers() {
        assertTrue(myClass.isEven(2));
        assertTrue(myClass.isEven(4));
        assertTrue(myClass.isEven(100));
        assertTrue(myClass.isEven(0));
    }

    @Test
    public void test_isEven_odd_positive_numbers() {
        assertFalse(myClass.isEven(1));
        assertFalse(myClass.isEven(3));
        assertFalse(myClass.isEven(99));
    }

    @Test
    public void test_isEven_even_negative_numbers() {
        assertTrue(myClass.isEven(-2));
        assertTrue(myClass.isEven(-4));
        assertTrue(myClass.isEven(-100));
    }

    @Test
    public void test_isEven_odd_negative_numbers() {
        assertFalse(myClass.isEven(-1));
        assertFalse(myClass.isEven(-3));
        assertFalse(myClass.isEven(-99));
    }

    // Tests for max()
    @Test
    public void test_max_first_is_greater() {
        assertEquals(5, myClass.max(5, 3));
        assertEquals(100, myClass.max(100, 50));
    }

    @Test
    public void test_max_second_is_greater() {
        assertEquals(5, myClass.max(3, 5));
        assertEquals(100, myClass.max(50, 100));
    }

    @Test
    public void test_max_equal_values() {
        assertEquals(5, myClass.max(5, 5));
        assertEquals(0, myClass.max(0, 0));
    }

    @Test
    public void test_max_negative_numbers() {
        assertEquals(-2, myClass.max(-5, -2));
        assertEquals(-2, myClass.max(-2, -5));
    }

    @Test
    public void test_max_mixed_signs() {
        assertEquals(5, myClass.max(5, -3));
        assertEquals(5, myClass.max(-3, 5));
        assertEquals(0, myClass.max(0, -5));
        assertEquals(0, myClass.max(-5, 0));
    }

    // Tests for min()
    @Test
    public void test_min_first_is_lesser() {
        assertEquals(3, myClass.min(3, 5));
        assertEquals(50, myClass.min(50, 100));
    }

    @Test
    public void test_min_second_is_lesser() {
        assertEquals(3, myClass.min(5, 3));
        assertEquals(50, myClass.min(100, 50));
    }

    @Test
    public void test_min_equal_values() {
        assertEquals(5, myClass.min(5, 5));
        assertEquals(0, myClass.min(0, 0));
    }

    @Test
    public void test_min_negative_numbers() {
        assertEquals(-5, myClass.min(-5, -2));
        assertEquals(-5, myClass.min(-2, -5));
    }

    @Test
    public void test_min_mixed_signs() {
        assertEquals(-3, myClass.min(5, -3));
        assertEquals(-3, myClass.min(-3, 5));
        assertEquals(-5, myClass.min(0, -5));
        assertEquals(-5, myClass.min(-5, 0));
    }

    // Tests for factorial()
    @Test
    public void test_factorial_zero() {
        assertEquals(1, myClass.factorial(0));
    }

    @Test
    public void test_factorial_one() {
        assertEquals(1, myClass.factorial(1));
    }

    @Test
    public void test_factorial_negative_number() {
        // Negative numbers should return 1 (as per the method: if n < 2, return 1)
        assertEquals(1, myClass.factorial(-5));
        assertEquals(1, myClass.factorial(-1));
    }

    @Test
    public void test_factorial_positive_numbers() {
        assertEquals(2, myClass.factorial(2));
        assertEquals(6, myClass.factorial(3));
        assertEquals(24, myClass.factorial(4));
        assertEquals(120, myClass.factorial(5));
        assertEquals(720, myClass.factorial(6));
    }

    // Tests for isEmpty()
    @Test
    public void test_isEmpty_null_string() {
        assertTrue(myClass.isEmpty(null));
    }

    @Test
    public void test_isEmpty_empty_string() {
        assertTrue(myClass.isEmpty(""));
    }

    @Test
    public void test_isEmpty_non_empty_string() {
        assertFalse(myClass.isEmpty("hello"));
        assertFalse(myClass.isEmpty("a"));
        assertFalse(myClass.isEmpty("  "));
        assertFalse(myClass.isEmpty("123"));
    }

}
