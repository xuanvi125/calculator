package com.group14.application.calculator;

import com.group14.application.operation.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CalculatorService {
    private Map<String, Operation> operations;

    @PostConstruct
    public void init() {
        operations = new HashMap<>();
        operations.put("add", new AddOperation());
        operations.put("subtract", new SubtractOperation());
        operations.put("multiply", new MultiplyOperation());
        operations.put("divide", new DivideOperation());
    }

    public String calculate(String param1, String param2, String op) {
        if (op.isBlank())
            return "";

        Operation operation = operations.get(op);
        if (operation == null) {
            throw new IllegalArgumentException("Invalid operation: " + op);
        }

        double a, b;
        try {
            a = Double.parseDouble(param1);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + param1);
        }

        try {
            b = Double.parseDouble(param2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + param2);
        }

        double result = operation.calculate(a, b);

        return String.format("%.7f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    public Set<String> getOperations() {
        return operations.keySet();
    }
}
