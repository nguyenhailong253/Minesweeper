package com.myob.minesweeper.exception;

public class TooManyCharactersInARowException extends RuntimeException {

    private String customMessage;

    public TooManyCharactersInARowException(String msg) {
        super(msg);
        customMessage = msg;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }
}