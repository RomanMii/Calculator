package com.ti38b.calculator;

public enum Operation {
    ADD('+',1),
    SUBTRACT('-',1),
    MULTIPLY('*',2),
    DIVIDE('/',2),
    POWER('^',3),
    LEFTBRACKET('(',0),
    RIGHTBRACKET(')',0);

    private final char symbol;
    private final int priority;

    Operation(char symbol, int priority){
        this.symbol = symbol;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static boolean contains(char symbol){
        for(Operation operation: Operation.values()){
            if(operation.symbol == symbol){
                return true;
            }
        }
        return false;
    }

    public static Operation findOperation(char symbol){
        for(Operation operation : Operation.values()){
            if(operation.symbol == symbol){
                return operation;
            }
        }
        throw new IllegalArgumentException("there is no such operation as \"" + symbol + "\"");
    }
}
