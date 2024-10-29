package com.group14.application.operation;

public class DivideOperation implements Operation {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }
}
