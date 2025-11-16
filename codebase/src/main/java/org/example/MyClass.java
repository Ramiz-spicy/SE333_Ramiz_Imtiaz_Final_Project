package org.example;

/**
 * A sample class with various methods for testing coverage.
 */
public class MyClass {

    /**
     * Adds two integers.
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts b from a.
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides a by b. Returns 0 if b is zero.
     */
    public int divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    /**
     * Checks if a number is positive.
     */
    public boolean isPositive(int num) {
        return num > 0;
    }

    /**
     * Checks if a number is even.
     */
    public boolean isEven(int num) {
        return num % 2 == 0;
    }

    /**
     * Returns the maximum of two integers.
     */
    public int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    /**
     * Returns the minimum of two integers.
     */
    public int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    /**
     * Calculates factorial of n. Returns 1 if n < 2.
     */
    public int factorial(int n) {
        if (n < 2) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Checks if a string is null or empty.
     */
    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
