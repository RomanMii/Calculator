package com.ti38b.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolishAnnotationTest {

    PolishAnnotation polishAnnotation;
    @Before
    public void init(){
        polishAnnotation = new PolishAnnotation("");
    }

    @Test
    public void getOutputString(){
        polishAnnotation.numberStack.push(3.0);
        assertEquals("3.0", polishAnnotation.getOutputString());
    }

    @Test
    public void getOutputStringFailed(){
        assertEquals("calculation failed", polishAnnotation.getOutputString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNumberToStackFailed(){
        polishAnnotation.addNumberToStack("hello");
    }

    @Test(expected = ArithmeticException.class)
    public void calculateFailed(){
        polishAnnotation.symbolStack.push(Operation.ADD);
        polishAnnotation.calculate(Operation.ADD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void arithmeticOperationFailed(){
        polishAnnotation.arithmeticOperation(Operation.LEFTBRACKET,1,2);
    }

    @Test
    public void arithmeticOperationAdd(){
        assertEquals(3.0,
                polishAnnotation.arithmeticOperation(Operation.ADD,1,2),
                0);
    }

    @Test
    public void arithmeticOperationSubtract(){
        assertEquals(-1.0,
                polishAnnotation.arithmeticOperation(Operation.SUBTRACT,1,2),
                0);
    }

    @Test
    public void arithmeticOperationMultiply(){
        assertEquals(2,
                polishAnnotation.arithmeticOperation(Operation.MULTIPLY,1,2),
                0);
    }

    @Test
    public void arithmeticOperationDivide(){
        assertEquals(0.5,
                polishAnnotation.arithmeticOperation(Operation.DIVIDE,1,2),
                0);
    }

    @Test
    public void arithmeticOperationPower(){
        assertEquals(4.0,
                polishAnnotation.arithmeticOperation(Operation.POWER,2,2),
                0);
    }
}