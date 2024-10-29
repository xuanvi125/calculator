package com.group14.application.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CalculatorController {
    private final CalculatorService calculatorService;

    @GetMapping("/")
    public String showCalculator(Model model) {
        model.addAttribute("operations", calculatorService.getOperations());
        return "redirect:/calculator";
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam(defaultValue = "") String param1,
                            @RequestParam(defaultValue = "") String param2,
                            @RequestParam(defaultValue = "") String operation,
                            Model model) {
        String result = "", error = "";

        try {
            result = calculatorService.calculate(param1, param2, operation);
        } catch (Exception e) {
            result = "";
            error = e.getMessage();
        }

        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        model.addAttribute("result", result);
        model.addAttribute("op", operation);
        model.addAttribute("error", error);
        model.addAttribute("operations", calculatorService.getOperations());
        return "calculator";
    }
}