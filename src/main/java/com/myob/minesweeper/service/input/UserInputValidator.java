package com.myob.minesweeper.service.input;

public class UserInputValidator {

    public static boolean validateStringInputWithRequiredPattern(String userInput, String validPattern) {
        return userInput.matches(validPattern);
    }
}
