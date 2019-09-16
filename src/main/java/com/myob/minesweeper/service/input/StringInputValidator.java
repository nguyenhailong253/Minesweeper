package com.myob.minesweeper.service.input;

public class StringInputValidator {

    public static boolean isStringMatchedPattern(String userInput, String validPattern) {
        return userInput.matches(validPattern);
    }
}
