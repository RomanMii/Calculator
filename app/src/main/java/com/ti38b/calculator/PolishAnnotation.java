package com.ti38b.calculator;

import java.util.Stack;
import java.util.regex.Pattern;

public class PolishAnnotation {
    private final String REGEXP = "[\\d\\.\\+\\-\\*\\(\\)\\/\\^]+";
    private String inputString;
    private String outputString = "";
    protected Stack<Double> numberStack = new Stack<>();
    protected Stack<Operation> symbolStack = new Stack<>();

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

    protected void addNumberToStack(String numberString){
        double number;
        try {
            number = Double.parseDouble(numberString);
            numberStack.push(number);
        }catch (Exception e){
            throw new IllegalArgumentException("wrong number");
        }
    }

    protected void calculate(Operation operation){
        while (!symbolStack.isEmpty()
                && operation != Operation.LEFTBRACKET
                && symbolStack.peek() != Operation.LEFTBRACKET
                && operation.getPriority() <= symbolStack.peek().getPriority()){
            if(numberStack.size() >=2 ){
                double secondNumber = numberStack.pop();
                double firstNumber = numberStack.pop();
                double result = arithmeticOperation(symbolStack.pop(), firstNumber, secondNumber);
                numberStack.push(result);
            }else{
                throw new ArithmeticException("not enough arguments");
            }
        }
        if(operation != Operation.RIGHTBRACKET)
            symbolStack.push(operation);
    }

    protected double arithmeticOperation(Operation operation, double firstNumber, double secondNumber){
        switch (operation){
            case ADD:
                return firstNumber + secondNumber;
            case SUBTRACT:
                return firstNumber - secondNumber;
            case MULTIPLY:
                return firstNumber * secondNumber;
            case DIVIDE:
                return firstNumber / secondNumber;
            case POWER:
                return Math.pow(firstNumber,secondNumber);
            default:
                throw new IllegalArgumentException("wrong operator");
        }
    }

    protected double numberStackPeek(){
        return numberStack.peek();
    }
}
