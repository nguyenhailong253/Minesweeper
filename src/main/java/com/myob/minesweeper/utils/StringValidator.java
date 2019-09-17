package com.myob.minesweeper.utils;

public class StringValidator {

    public static boolean isStringMatchedPattern(String userInput, String validPattern) {
        return userInput.matches(validPattern);
    }
}
