package com.practice.app.controller;

import com.practice.app.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/sum")
    public Integer calculateSum(@RequestParam Integer a, @RequestParam Integer b){
        return calculatorService.calculateSum(a,b);
    }
}
