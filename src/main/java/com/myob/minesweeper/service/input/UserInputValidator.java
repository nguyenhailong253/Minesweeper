package com.myob.minesweeper.service.input;

import java.util.Arrays;

public class UserInputValidator {

    public static boolean validateStringInputWithRequiredPattern(String userInput, String validPattern) {
        return userInput.matches(validPattern);
    }

    public static boolean validateEndOfInputValues(int[] userInput, int[] endOfInputValues) {
        return Arrays.equals(userInput, endOfInputValues);
    }

    public static boolean validateLengthOfRowInput(String rowInput, int givenLength) {
        return rowInput.length() == givenLength;
    }
}
