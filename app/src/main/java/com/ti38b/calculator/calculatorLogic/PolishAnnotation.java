package com.ti38b.calculator.calculatorLogic;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.ti38b.calculator.calculatorLogic.Operation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

public class PolishAnnotation {
    private final String REGEXP = "[\\d\\.\\+\\-\\*\\(\\)\\/\\^]+";
    private String inputString;
    private String outputString = "";
    public Stack<BigDecimal> numberStack = new Stack<>();
    public Stack<Operation> symbolStack = new Stack<>();

    public PolishAnnotation(String inputString){
        this.inputString = inputString;
    }

    public String getOutputString() {
        if(numberStack.size() == 1){
            outputString = numberStack.pop().toString();
        }else {
            return "calculation failed";
        }
        return outputString;
    }

    public void stringSplitter(){
        String numberString = "";
        boolean prewSymbolIsOperation = true;
        Operation operation = null;

        for(int i = 0; i < inputString.length(); i++){
            if(Operation.contains(inputString.charAt(i))){
                try {
                    operation = Operation.findOperation(inputString.charAt(i));
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }

                if(operation != null){
                    if(operation == Operation.SUBTRACT && prewSymbolIsOperation){
                        numberString = "-";
                    } else {
                        if(numberString.length() > 0){
                            addNumberToStack(numberString);
                            numberString = "";
                        }

                        if(operation == Operation.RIGHTBRACKET) {
                            while (symbolStack.peek() != Operation.LEFTBRACKET) {
                                calculate(operation);
                            }

                            if (symbolStack.peek() == Operation.LEFTBRACKET)
                                symbolStack.pop();
                        }else {
                            calculate(operation);
                        }
                    }
                }
                prewSymbolIsOperation = true;
            }else{
                prewSymbolIsOperation = false;
                numberString += inputString.charAt(i);
                if(i == inputString.length()-1){
                    addNumberToStack(numberString);
                }
            }
        }

        while (numberStack.size() != 1){
            calculate(Operation.RIGHTBRACKET);
        }
    }

    public void addNumberToStack(String numberString){
        BigDecimal number;
        try {
            number = new BigDecimal(numberString);
            numberStack.push(number);
        }catch (Exception e){
            throw new IllegalArgumentException("wrong number");
        }
    }

    public void calculate(Operation operation){
        while (!symbolStack.isEmpty()
                && operation != Operation.LEFTBRACKET
                && symbolStack.peek() != Operation.LEFTBRACKET
                && operation.getPriority() <= symbolStack.peek().getPriority()){
            if(numberStack.size() >=2 ){
                BigDecimal secondNumber = numberStack.pop();
                BigDecimal firstNumber = numberStack.pop();
                BigDecimal result = arithmeticOperation(symbolStack.pop(), firstNumber, secondNumber);
                numberStack.push(result);
            }else{
                throw new ArithmeticException("not enough arguments");
            }
        }
        if(operation != Operation.RIGHTBRACKET)
            symbolStack.push(operation);
    }

    public BigDecimal arithmeticOperation(Operation operation, BigDecimal firstNumber, BigDecimal secondNumber){
        switch (operation){
            case ADD:
                return firstNumber.add(secondNumber);
            case SUBTRACT:
                return firstNumber.subtract(secondNumber);
            case MULTIPLY:
                return firstNumber.multiply(secondNumber);
            case DIVIDE:
                return firstNumber.divide(secondNumber,new MathContext(10, RoundingMode.CEILING));
            case POWER:
                return BigDecimalMath.pow(firstNumber,secondNumber,new MathContext(10, RoundingMode.CEILING));
            default:
                throw new IllegalArgumentException("wrong operator");
        }
    }

}
