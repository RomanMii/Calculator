package com.ti38b.calculator;

import com.ti38b.calculator.calculatorLogic.Operation;
import com.ti38b.calculator.calculatorLogic.PolishAnnotation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PolishAnnotationTest {

    PolishAnnotation polishAnnotation;
    @Before
    public void init(){
        polishAnnotation = new PolishAnnotation("");
    }

    @Test
    public void getOutputString(){
        polishAnnotation.numberStack.push(new BigDecimal(3));
        assertEquals("3", polishAnnotation.getOutputString());
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
        polishAnnotation.arithmeticOperation(Operation.LEFTBRACKET,new BigDecimal(1),new BigDecimal(2));
    }

    @Test
    public void arithmeticOperationAdd(){
        assertEquals(new BigDecimal(3.0),
                polishAnnotation.arithmeticOperation(Operation.ADD,new BigDecimal(1),new BigDecimal(2)));
    }

    @Test
    public void arithmeticOperationSubtract(){
        assertEquals(new BigDecimal(-1.0),
                polishAnnotation.arithmeticOperation(Operation.SUBTRACT,new BigDecimal(1),new BigDecimal(2)));
    }

    @Test
    public void arithmeticOperationMultiply(){
        assertEquals(new BigDecimal(2),
                polishAnnotation.arithmeticOperation(Operation.MULTIPLY,new BigDecimal(1),new BigDecimal(2)));
    }

    @Test
    public void arithmeticOperationDivide(){
        assertEquals(new BigDecimal(0.5),
                polishAnnotation.arithmeticOperation(Operation.DIVIDE,new BigDecimal(1),new BigDecimal(2)));
    }

    @Test
    public void arithmeticOperationPower(){
        assertEquals(new BigDecimal(1),
                polishAnnotation.arithmeticOperation(Operation.POWER,new BigDecimal(1),new BigDecimal(2)));
    }
}