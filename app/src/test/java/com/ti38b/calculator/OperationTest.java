package com.ti38b.calculator;

import com.ti38b.calculator.calculatorLogic.Operation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationTest {

    @Test
    public void getPriority(){
        assertEquals(1, Operation.ADD.getPriority());
    }

    @Test
    public void contains(){
        assertEquals(true,Operation.contains('+'));
    }

    @Test
    public void containsFailed(){
        assertEquals(false,Operation.contains('?'));
    }

    @Test
    public void findOperation(){
        assertEquals(Operation.RIGHTBRACKET, Operation.findOperation(')'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOperationFailed(){
        Operation.findOperation('?');
    }

}