package com.group14.application.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideOperationTest {
    private final double EPSILON = 10e-6;

    @Test
    public void testDivideByNonZero() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(1, divideOperation.calculate(1, 1), EPSILON);
        assertEquals(2, divideOperation.calculate(4, 2), EPSILON);
        assertEquals(0.5, divideOperation.calculate(1, 2), EPSILON);
        assertEquals(2.5, divideOperation.calculate(5, 2), EPSILON);
    }

    @Test
    public void testDivideByZero() {
        DivideOperation divideOperation = new DivideOperation();
        assertThrows(IllegalArgumentException.class, () -> divideOperation.calculate(1, 0));
        assertThrows(IllegalArgumentException.class, () -> divideOperation.calculate(-2, 0));
        assertThrows(IllegalArgumentException.class, () -> divideOperation.calculate(0, 0));
    }

    @Test
    public void testDivideTwoPositiveNumbers() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2, divideOperation.calculate(4, 2), EPSILON);
        assertEquals(1.5, divideOperation.calculate(3, 2), EPSILON);
    }

    @Test
    public void testDivideTwoNegativeNumbers() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2, divideOperation.calculate(-4, -2), EPSILON);
        assertEquals(1.5, divideOperation.calculate(-3, -2), EPSILON);
    }

    @Test
    public void testDividePositiveAndNegativeNumbers() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(-2, divideOperation.calculate(4, -2), EPSILON);
        assertEquals(-1.5, divideOperation.calculate(-3, 2), EPSILON);
    }

    @Test
    public void testDivideTwoPositiveDecimals() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2.5, divideOperation.calculate(5.0, 2.0), EPSILON);
        assertEquals(1.25, divideOperation.calculate(2.5, 2.0), EPSILON);
    }

    @Test
    public void testDivideIntegerAndDecimal() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2.5, divideOperation.calculate(5, 2.0), EPSILON);
        assertEquals(0.5, divideOperation.calculate(1, 2.0), EPSILON);
    }

    @Test
    public void testDividePositiveAndNegativeDecimals() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(-2.5, divideOperation.calculate(5.0, -2.0), EPSILON);
        assertEquals(-1.25, divideOperation.calculate(-2.5, 2.0), EPSILON);
    }

    @Test
    public void testDivideTwoNegativeDecimals() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2.5, divideOperation.calculate(-5.0, -2.0), EPSILON);
        assertEquals(1.25, divideOperation.calculate(-2.5, -2.0), EPSILON);
    }

    @Test
    public void testLargeAbsoluteNumbers() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(2, divideOperation.calculate(2000000000, 1000000000), EPSILON);
        assertEquals(-2, divideOperation.calculate(-2000000000, 1000000000), EPSILON);
        assertEquals(1, divideOperation.calculate(1000000000, 1000000000), EPSILON);
    }

    @Test
    public void testDivideSmallNumbers() {
        DivideOperation divideOperation = new DivideOperation();
        assertEquals(1, divideOperation.calculate(0.000000001, 0.000000001), EPSILON);
        assertEquals(-1, divideOperation.calculate(-0.000000001, 0.000000001), EPSILON);
        assertEquals(0, divideOperation.calculate(0, 0.000000001), EPSILON);
    }
}