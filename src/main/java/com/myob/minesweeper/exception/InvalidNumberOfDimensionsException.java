package com.myob.minesweeper.exception;

public class InvalidNumberOfDimensionsException extends RuntimeException {
    private String customMessage;

    public InvalidNumberOfDimensionsException(String msg) {
        super(msg);
        customMessage = msg;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }
}
