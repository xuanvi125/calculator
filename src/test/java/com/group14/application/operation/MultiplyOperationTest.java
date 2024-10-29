package com.group14.application.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyOperationTest {
    private final double EPSILON = 10e-6;

    @Test
    public void testMultiplyZero() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(0, multiplyOperation.calculate(0, 0));
        assertEquals(0, multiplyOperation.calculate(0, 1));
        assertEquals(0, multiplyOperation.calculate(1, 0));
        assertEquals(0, multiplyOperation.calculate(0, 0.01));
        assertEquals(0, multiplyOperation.calculate(0.01, 0));
    }

    @Test
    public void testMultiplyTwoPositiveIntegerNumbers() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(2, multiplyOperation.calculate(1, 2));
        assertEquals(2, multiplyOperation.calculate(2, 1));
        assertEquals(12, multiplyOperation.calculate(3, 4));
        assertEquals(12, multiplyOperation.calculate(4, 3));
    }

    @Test
    public void testMultiplyTwoNegativeIntegerNumbers() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(2, multiplyOperation.calculate(-1, -2));
        assertEquals(2, multiplyOperation.calculate(-2, -1));
        assertEquals(56, multiplyOperation.calculate(-7, -8));
        assertEquals(56, multiplyOperation.calculate(-8, -7));
    }

    @Test
    public void testMultiplyPositiveAndNegativeIntegerNumbers() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(-2, multiplyOperation.calculate(1, -2));
        assertEquals(-2, multiplyOperation.calculate(-2, 1));
        assertEquals(-24, multiplyOperation.calculate(-3, 8));
        assertEquals(-24, multiplyOperation.calculate(8, -3));
    }

    @Test
    public void testMultiplyTwoPositiveDecimals() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(3.75, multiplyOperation.calculate(1.5, 2.5), EPSILON);
        assertEquals(11.75, multiplyOperation.calculate(2.5, 4.7), EPSILON);
        assertEquals(0.02, multiplyOperation.calculate(0.1, 0.2), EPSILON);
    }

    @Test
    public void testMultiplyIntegerAndDecimal() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(2.5, multiplyOperation.calculate(1, 2.5), EPSILON);
        assertEquals(2.5, multiplyOperation.calculate(-2.5, -1), EPSILON);
        assertEquals(2.1, multiplyOperation.calculate(1, 2.1), EPSILON);
        assertEquals(-2.1, multiplyOperation.calculate(2.1, -1), EPSILON);
    }

    @Test
    public void testMultiplyPositiveAndNegativeDecimals() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(-2.5, multiplyOperation.calculate(1, -2.5), EPSILON);
        assertEquals(-2.5, multiplyOperation.calculate(-2.5, 1), EPSILON);
        assertEquals(-24.0, multiplyOperation.calculate(-3, 8), EPSILON);
        assertEquals(-24.0, multiplyOperation.calculate(8, -3), EPSILON);
    }

    @Test
    public void testMultiplyTwoNegativeDecimals() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(2.5, multiplyOperation.calculate(-1.5, -1.6666666666666667), EPSILON);
        assertEquals(2.5, multiplyOperation.calculate(-1.6666666666666667, -1.5), EPSILON);
        assertEquals(0.02, multiplyOperation.calculate(-0.1, -0.2), EPSILON);
    }

    @Test
    public void testMultiplyLargeAbsoluteNumbers() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(1.0E10, multiplyOperation.calculate(1.0E5, 1.0E5), EPSILON);
        assertEquals(1.0E10, multiplyOperation.calculate(-1.0E5, -1.0E5), EPSILON);
        assertEquals(-1.0E10, multiplyOperation.calculate(-1.0E5, 1.0E5), EPSILON);
        assertEquals(-1.0E10, multiplyOperation.calculate(1.0E5, -1.0E5), EPSILON);
    }

    @Test
    public void testMultiplySmallDecimals() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        assertEquals(1.0E-10, multiplyOperation.calculate(1.0E-5, 1.0E-5), EPSILON);
        assertEquals(1.0E-10, multiplyOperation.calculate(-1.0E-5, -1.0E-5), EPSILON);
        assertEquals(1.0E-10, multiplyOperation.calculate(-1.0E-5, 1.0E-5), EPSILON);
        assertEquals(1.0E-10, multiplyOperation.calculate(1.0E-5, -1.0E-5), EPSILON);
    }



}