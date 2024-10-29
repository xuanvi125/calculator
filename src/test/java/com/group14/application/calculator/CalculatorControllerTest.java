package com.group14.application.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    public void testShowCalculator() throws Exception {
        // Mock the service response
        given(calculatorService.getOperations())
                .willReturn(Set.of("add", "subtract", "multiply", "divide"));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/calculator"));
    }

    @Test
    public void testCalculate() throws Exception {
        String param1 = "10";
        String param2 = "5";
        String operation = "add";
        String expectedResult = "15";

        // Mock the service response
        given(calculatorService.calculate(param1, param2, operation)).willReturn(expectedResult);
        given(calculatorService.getOperations())
                .willReturn(Set.of("add", "subtract", "multiply", "divide"));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculator")
                        .param("param1", param1)
                        .param("param2", param2)
                        .param("operation", operation))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("param1", param1))
                .andExpect(model().attribute("param2", param2))
                .andExpect(model().attribute("result", expectedResult))
                .andExpect(model().attribute("op", operation))
                .andExpect(model().attribute("error", ""))
                .andExpect(model().attribute("operations", Set.of("add", "subtract", "multiply", "divide")));
    }

    @Test
    public void testCalculateWithError() throws Exception {
        String param1 = "10";
        String param2 = "0";
        String operation = "divide";
        String errorMessage = "Division by zero error";

        // Mock the service response
        given(calculatorService.calculate(param1, param2, operation)).willThrow(new ArithmeticException(errorMessage));
        given(calculatorService.getOperations())
                .willReturn(Set.of("add", "subtract", "multiply", "divide"));

        mockMvc.perform(MockMvcRequestBuilders.get("/calculator")
                        .param("param1", param1)
                        .param("param2", param2)
                        .param("operation", operation))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("param1", param1))
                .andExpect(model().attribute("param2", param2))
                .andExpect(model().attribute("result", ""))
                .andExpect(model().attribute("op", operation))
                .andExpect(model().attribute("error", errorMessage))
                .andExpect(model().attribute("operations", Set.of("add", "subtract", "multiply", "divide")));
    }
}
