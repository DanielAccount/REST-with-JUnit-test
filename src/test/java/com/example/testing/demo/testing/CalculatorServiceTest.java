package com.example.testing.demo.testing;

import com.example.testing.demo.service.CalculatorLogic;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceTest {

    private CalculatorLogic calculatorLogic = new CalculatorLogic();

    @Test
    public void testSum(){
        assertEquals(5, calculatorLogic.add(3,5));
    }
}
