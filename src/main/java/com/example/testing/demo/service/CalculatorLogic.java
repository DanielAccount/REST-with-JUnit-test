package com.example.testing.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculatorLogic {

    public int add(int a, int b){
        return a + b;
    }

}
