package com.ti38b.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void getOutput() {
        Calculator calculator = new Calculator("1+2*(3+4/2-(1+2))*2+1");
        assertEquals("10.0",calculator.getOutput());
    }

    @Test
    public void WrongInputData() {
        Calculator calculator = new Calculator("1!2*(3+4/2-(1+2))*2+1");
        assertEquals("wrong input data",calculator.getOutput());
    }

    @Test
    public void NoInputData() {
        Calculator calculator = new Calculator("");
        assertEquals("no arguments",calculator.getOutput());
    }
}