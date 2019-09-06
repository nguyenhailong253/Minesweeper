package com.myob.minesweeper.service.input;

import java.util.Arrays;

public class UserInputValidator {

    public static boolean validateStringInputWithRequiredPattern(String userInput, String validPattern) {
        return userInput.matches(validPattern);
    }

    public static boolean validateDimensionValuesInRange(int[] dimensions, int min, int max) {
        for (int num: dimensions) {
            if (!validateNumberInRange(num, min, max)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateNumberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static boolean validateLengthOfRowInput(String rowInput, int givenDimension) {
        return rowInput.length() == givenDimension;
    }

    public static boolean validateEndOfInputValues(int[] userInput, int[] requiredEndOfInputValues) {
        return Arrays.equals(userInput, requiredEndOfInputValues);
    }
}
