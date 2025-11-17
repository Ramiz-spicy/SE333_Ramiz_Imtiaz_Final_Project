package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Decision Table Tests for MyClass
 * Tests various combinations of conditions in control flow statements
 */
public class decisionTests {
    
    private MyClass myClass;
    
    @Before
    public void setUp() {
        myClass = new MyClass();
    }
    
    // ==================== divide() - Decision Table ====================
    // Condition: b == 0
    // Decision outcomes: Return 0 if TRUE, else perform division
    
    @Test
    public void test_divide_divisor_zero() {
        // Condition: b == 0 is TRUE
        int result = myClass.divide(10, 0);
        assertEquals(0, result);
    }
    
    @Test
    public void test_divide_divisor_nonzero_positive() {
        // Condition: b == 0 is FALSE (positive divisor)
        int result = myClass.divide(10, 2);
        assertEquals(5, result);
    }
    
    @Test
    public void test_divide_divisor_nonzero_negative() {
        // Condition: b == 0 is FALSE (negative divisor)
        int result = myClass.divide(10, -2);
        assertEquals(-5, result);
    }
    
    // ==================== isPositive() - Decision Table ====================
    // Condition: num > 0
    // Decision outcomes: TRUE if positive, FALSE otherwise
    
    @Test
    public void test_isPositive_positive_number() {
        // Condition: num > 0 is TRUE
        assertTrue(myClass.isPositive(5));
    }
    
    @Test
    public void test_isPositive_zero() {
        // Condition: num > 0 is FALSE (boundary case: zero)
        assertFalse(myClass.isPositive(0));
    }
    
    @Test
    public void test_isPositive_negative_number() {
        // Condition: num > 0 is FALSE (negative)
        assertFalse(myClass.isPositive(-5));
    }
    
    // ==================== isEven() - Decision Table ====================
    // Condition: num % 2 == 0
    // Decision outcomes: TRUE if even, FALSE if odd
    
    @Test
    public void test_isEven_even_positive() {
        // Condition: num % 2 == 0 is TRUE (positive even)
        assertTrue(myClass.isEven(4));
    }
    
    @Test
    public void test_isEven_even_zero() {
        // Condition: num % 2 == 0 is TRUE (zero is even)
        assertTrue(myClass.isEven(0));
    }
    
    @Test
    public void test_isEven_even_negative() {
        // Condition: num % 2 == 0 is TRUE (negative even)
        assertTrue(myClass.isEven(-4));
    }
    
    @Test
    public void test_isEven_odd_positive() {
        // Condition: num % 2 == 0 is FALSE (positive odd)
        assertFalse(myClass.isEven(3));
    }
    
    @Test
    public void test_isEven_odd_negative() {
        // Condition: num % 2 == 0 is FALSE (negative odd)
        assertFalse(myClass.isEven(-3));
    }
    
    // ==================== max() - Decision Table ====================
    // Condition: a > b
    // Decision outcomes: Return a if TRUE, else return b
    
    @Test
    public void test_max_a_greater_than_b() {
        // Condition: a > b is TRUE
        int result = myClass.max(10, 5);
        assertEquals(10, result);
    }
    
    @Test
    public void test_max_a_less_than_b() {
        // Condition: a > b is FALSE
        int result = myClass.max(5, 10);
        assertEquals(10, result);
    }
    
    @Test
    public void test_max_a_equals_b() {
        // Condition: a > b is FALSE (boundary case: equal)
        int result = myClass.max(10, 10);
        assertEquals(10, result);
    }
    
    // ==================== min() - Decision Table ====================
    // Condition: a < b
    // Decision outcomes: Return a if TRUE, else return b
    
    @Test
    public void test_min_a_less_than_b() {
        // Condition: a < b is TRUE
        int result = myClass.min(5, 10);
        assertEquals(5, result);
    }
    
    @Test
    public void test_min_a_greater_than_b() {
        // Condition: a < b is FALSE
        int result = myClass.min(10, 5);
        assertEquals(5, result);
    }
    
    @Test
    public void test_min_a_equals_b() {
        // Condition: a < b is FALSE (boundary case: equal)
        int result = myClass.min(10, 10);
        assertEquals(10, result);
    }
    
    // ==================== factorial() - Decision Table ====================
    // Condition: n < 2
    // Decision outcomes: Return 1 if TRUE, else calculate factorial
    
    @Test
    public void test_factorial_zero() {
        // Condition: n < 2 is TRUE (boundary case: 0)
        int result = myClass.factorial(0);
        assertEquals(1, result);
    }
    
    @Test
    public void test_factorial_one() {
        // Condition: n < 2 is TRUE (boundary case: 1)
        int result = myClass.factorial(1);
        assertEquals(1, result);
    }
    
    @Test
    public void test_factorial_negative() {
        // Condition: n < 2 is TRUE (negative number)
        int result = myClass.factorial(-5);
        assertEquals(1, result);
    }
    
    @Test
    public void test_factorial_two() {
        // Condition: n < 2 is FALSE (minimum factorial calculation)
        int result = myClass.factorial(2);
        assertEquals(2, result);
    }
    
    @Test
    public void test_factorial_five() {
        // Condition: n < 2 is FALSE (normal case: 5! = 120)
        int result = myClass.factorial(5);
        assertEquals(120, result);
    }
    
    @Test
    public void test_factorial_large() {
        // Condition: n < 2 is FALSE (larger number)
        int result = myClass.factorial(10);
        assertEquals(3628800, result);
    }
    
    // ==================== isEmpty() - Decision Table ====================
    // Condition: str == null || str.isEmpty()
    // Decision outcomes: TRUE if null OR empty, FALSE otherwise
    // This involves a composite condition with OR operator
    
    @Test
    public void test_isEmpty_null_string() {
        // Condition: str == null is TRUE (short-circuit evaluation)
        assertTrue(myClass.isEmpty(null));
    }
    
    @Test
    public void test_isEmpty_empty_string() {
        // Condition: str == null is FALSE, str.isEmpty() is TRUE
        assertTrue(myClass.isEmpty(""));
    }
    
    @Test
    public void test_isEmpty_non_empty_string() {
        // Condition: str == null is FALSE, str.isEmpty() is FALSE
        assertFalse(myClass.isEmpty("hello"));
    }
    
    @Test
    public void test_isEmpty_single_char() {
        // Condition: str == null is FALSE, str.isEmpty() is FALSE (boundary)
        assertFalse(myClass.isEmpty("a"));
    }
    
    @Test
    public void test_isEmpty_whitespace_string() {
        // Condition: str == null is FALSE, str.isEmpty() is FALSE (whitespace)
        assertFalse(myClass.isEmpty(" "));
    }
}
