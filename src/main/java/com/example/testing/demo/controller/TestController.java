package com.example.testing.demo.controller;

import com.example.testing.demo.service.CalculatorLogic;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final CalculatorLogic logic;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @PostMapping("/add")
    public int add(@RequestParam int a, @RequestParam int b){
        return logic.add(5,5);
    }

}
