package com.group14.application.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
        calculatorService.init();
    }

    @Nested
    class TestCalculateMethod {
        @Test
        public void testCalculateWithInvalidParam1() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculatorService.calculate("invalid", "2", "add");
            });

            assertEquals("Invalid number: invalid", exception.getMessage());
        }

        @Test
        public void testCalculateWithInvalidParam2() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculatorService.calculate("1", "invalid", "add");
            });

            assertEquals("Invalid number: invalid", exception.getMessage());
        }

        @Test
        public void testCalculateWithInvalidOperation() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculatorService.calculate("1", "2", "invalid");
            });

            assertEquals("Invalid operation: invalid", exception.getMessage());
        }

        @Test
        public void testCalculateAdd() {
            String result = calculatorService.calculate("1", "2", "add");
            assertEquals("3", result);
        }

        @Test
        public void testCalculateSubtract() {
            String result = calculatorService.calculate("1", "2", "subtract");
            assertEquals("-1", result);
        }

        @Test
        public void testCalculateMultiply() {
            String result = calculatorService.calculate("2", "3", "multiply");
            assertEquals("6", result);
        }

        @Test
        public void testCalculateDivide() {
            String result = calculatorService.calculate("6", "3", "divide");
            assertEquals("2", result);
        }

        @Test
        public void testCalculateOperationBlank() {
            String result = calculatorService.calculate("1", "2", "");

            assertEquals(result, "");
        }
    }

    @Nested
    class TestGetOperationsMethod {
        @Test
        public void testGetOperations() {
            assertEquals(4, calculatorService.getOperations().size());
        }
    }

    @Nested
    class TestInitMethod {
        @Test
        public void testInit() {
            calculatorService.init();
            assertEquals(4, calculatorService.getOperations().size());
        }
    }
}