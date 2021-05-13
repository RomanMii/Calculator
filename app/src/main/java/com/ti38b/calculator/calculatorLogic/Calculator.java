package com.ti38b.calculator.calculatorLogic;

import java.util.regex.Pattern;

public class Calculator {
    String output;
    PolishAnnotation polishAnnotation = null;
    private final String REGEXP = "(\\-?\\d+\\.?\\d*[\\-\\+\\*\\/\\^]?([\\(\\)]+[\\-\\+\\*\\/\\^]?)?)*";

    public Calculator(String input){
        if (input != null && input.length() > 0) {
            if(Pattern.matches(REGEXP,input)){
                polishAnnotation = new PolishAnnotation(input);
                polishAnnotation.stringSplitter();
                output = polishAnnotation.getOutputString();
            }else{
                output = "wrong input data";
            }
        }else{
            output = "no arguments";
        }
    }

    public String getOutput() {
        return output;
    }
}
