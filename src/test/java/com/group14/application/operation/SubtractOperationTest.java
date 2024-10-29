package com.group14.application.operation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class SubtractOperationTest {
    @Value("${application.operation.epsilon}")
    private final double EPSILON = 10e-6;

    @Test
    public void testSubtractZero() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(0, subtractOperation.calculate(0, 0));
        assertEquals(-1, subtractOperation.calculate(0, 1));
        assertEquals(1, subtractOperation.calculate(1, 0));
        assertEquals(0.99, subtractOperation.calculate(1, 0.01), EPSILON);
        assertEquals(-0.99, subtractOperation.calculate(0.01, 1), EPSILON);
    }

    @Test
    public void testSubtractTwoPositiveIntegerNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(-1, subtractOperation.calculate(1, 2));
        assertEquals(1, subtractOperation.calculate(2, 1));
        assertEquals(-1, subtractOperation.calculate(3, 4));
        assertEquals(1, subtractOperation.calculate(4, 3));
    }

    @Test
    public void testSubtractTwoNegativeIntegerNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(1, subtractOperation.calculate(-1, -2));
        assertEquals(-1, subtractOperation.calculate(-2, -1));
        assertEquals(1, subtractOperation.calculate(-7, -8));
        assertEquals(-1, subtractOperation.calculate(-8, -7));
    }

    @Test
    public void testSubtractPositiveAndNegativeIntegerNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(3, subtractOperation.calculate(1, -2));
        assertEquals(-3, subtractOperation.calculate(-2, 1));
        assertEquals(-11, subtractOperation.calculate(-3, 8));
        assertEquals(11, subtractOperation.calculate(8, -3));
    }

    @Test
    public void testSubtractTwoPositiveDecimals() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(-1, subtractOperation.calculate(1.5, 2.5), EPSILON);
        assertEquals(-2.2, subtractOperation.calculate(2.5, 4.7), EPSILON);
        assertEquals(-0.1, subtractOperation.calculate(0.1, 0.2), EPSILON);
        assertEquals(0.1, subtractOperation.calculate(0.3, 0.2), EPSILON);
    }

    @Test
    public void testSubtractIntegerAndDecimal() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(-1.5, subtractOperation.calculate(1, 2.5), EPSILON);
        assertEquals(-1.5, subtractOperation.calculate(-2.5, -1), EPSILON);
        assertEquals(-1.1, subtractOperation.calculate(1, 2.1), EPSILON);
        assertEquals(3.1, subtractOperation.calculate(2.1, -1), EPSILON);
    }

    @Test
    public void testSubtractPositiveAndNegativeDecimals() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(2.8, subtractOperation.calculate(0.9, -1.9), EPSILON);
        assertEquals(-2.8, subtractOperation.calculate(-1.9, 0.9), EPSILON);
        assertEquals(-0.4, subtractOperation.calculate(-0.1, 0.3), EPSILON);
        assertEquals(0.4, subtractOperation.calculate(0.3, -0.1), EPSILON);
    }

    @Test
    public void testSubtractTwoNegativeDecimals() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(-1, subtractOperation.calculate(-2.5, -1.5), EPSILON);
        assertEquals(-3.4, subtractOperation.calculate(-4.3, -0.9), EPSILON);
        assertEquals(0.1, subtractOperation.calculate(-0.1, -0.2), EPSILON);
        assertEquals(-0.1, subtractOperation.calculate(-0.2, -0.1), EPSILON);
    }

    @Test
    public void testLargeAbsoluteNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(-1000000000.0, subtractOperation.calculate(1000000000, 2000000000));
        assertEquals(1000000000.0, subtractOperation.calculate(-1000000000, -2000000000));
        assertEquals(3000000000.0, subtractOperation.calculate(2000000000, -1000000000));
    }

    @Test
    public void testSubtractSmallNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        assertEquals(0, subtractOperation.calculate(0.000000001, 0.000000001), EPSILON);
        assertEquals(0, subtractOperation.calculate(-0.000000001, -0.000000001), EPSILON);
        assertEquals(-0.000000002, subtractOperation.calculate(-0.000000001, 0.000000001), EPSILON);
    }
}