package com.group14.application.operation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddOperationTest {
    private final double EPSILON = 10e-6;
    @Test
    public void testAddZero() {
        AddOperation addOperation = new AddOperation();
        assertEquals(0, addOperation.calculate(0, 0));
        assertEquals(1, addOperation.calculate(0, 1));
        assertEquals(1, addOperation.calculate(1, 0));
        assertEquals(1.01, addOperation.calculate(1, 0.01));
        assertEquals(1.01, addOperation.calculate(0.01, 1));
    }

    @Test
    public void testAddTwoPositiveIntegerNumbers() {
        AddOperation addOperation = new AddOperation();
        assertEquals(3, addOperation.calculate(1, 2));
        assertEquals(3, addOperation.calculate(2, 1));
        assertEquals(7, addOperation.calculate(3, 4));
        assertEquals(7, addOperation.calculate(4, 3));
    }

    @Test
    public void testAddTwoNegativeIntegerNumbers() {
        AddOperation addOperation = new AddOperation();
        assertEquals(-3, addOperation.calculate(-1, -2));
        assertEquals(-3, addOperation.calculate(-2, -1));
        assertEquals(-15, addOperation.calculate(-7, -8));
        assertEquals(-15, addOperation.calculate(-8, -7));
    }

    @Test
    public void testAddPositiveAndNegativeIntegerNumbers() {
        AddOperation addOperation = new AddOperation();
        assertEquals(-1, addOperation.calculate(1, -2));
        assertEquals(-1, addOperation.calculate(-2, 1));
        assertEquals(5, addOperation.calculate(-3, 8));
        assertEquals(5, addOperation.calculate(8, -3));
    }

    @Test
    public void testAddTwoPositiveDecimals() {
        AddOperation addOperation = new AddOperation();
        assertEquals(4, addOperation.calculate(1.5, 2.5), EPSILON);
        assertEquals(7.2, addOperation.calculate(2.5, 4.7), EPSILON);
        assertEquals(0.3, addOperation.calculate(0.1, 0.2), EPSILON);
    }

    @Test
    public void testAddIntegerAndDecimal() {
        AddOperation addOperation = new AddOperation();
        assertEquals(3.5, addOperation.calculate(1, 2.5), EPSILON);
        assertEquals(-3.5, addOperation.calculate(-2.5, -1), EPSILON);
        assertEquals(3.1, addOperation.calculate(1, 2.1), EPSILON);
        assertEquals(1.1, addOperation.calculate(2.1, -1), EPSILON);
    }

    @Test
    public void testAddPositiveAndNegativeDecimals() {
        AddOperation addOperation = new AddOperation();
        assertEquals(-1, addOperation.calculate(0.9, -1.9), EPSILON);
        assertEquals(-1, addOperation.calculate(-1.9, 0.9), EPSILON);
        assertEquals(0.2, addOperation.calculate(-0.1, 0.3), EPSILON);
        assertEquals(0.2, addOperation.calculate(0.3, -0.1), EPSILON);
    }

    @Test
    public void testAddTwoNegativeDecimals() {
        AddOperation addOperation = new AddOperation();
        assertEquals(-4, addOperation.calculate(-2.5, -1.5), EPSILON);
        assertEquals(-5.2, addOperation.calculate(-4.3, -0.9), EPSILON);
        assertEquals(-0.3, addOperation.calculate(-0.1, -0.2), EPSILON);
        assertEquals(-0.3, addOperation.calculate(-0.2, -0.1), EPSILON);
    }

    @Test
    public void testLargeAbsoluteNumbers() {
        AddOperation addOperation = new AddOperation();
        assertEquals(3000000000.0, addOperation.calculate(1000000000, 2000000000));
        assertEquals(-3000000000.0, addOperation.calculate(-1000000000, -2000000000));
        assertEquals(1000000000.0, addOperation.calculate(2000000000, -1000000000));

    }

    @Test
    public void testAddSmallNumbers() {
        AddOperation addOperation = new AddOperation();
        assertEquals(0.000000002, addOperation.calculate(0.000000001, 0.000000001), EPSILON);
        assertEquals(-0.000000002, addOperation.calculate(-0.000000001, -0.000000001), EPSILON);
        assertEquals(0, addOperation.calculate(-0.000000001, 0.000000001), EPSILON);
    }
}