package com.practice.app.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Integer calculateSum(Integer a, Integer b){
        Integer sum = a + b;
        return sum;
    }
}
