package com.practice.app.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Cacheable(value = "sum")
    public Integer calculateSum(Integer a, Integer b){
        try {
            System.out.println("loading...");
            Thread.sleep(3000);
            System.out.println("loading...");
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        Integer sum = a + b;
        return sum;
    }
}
