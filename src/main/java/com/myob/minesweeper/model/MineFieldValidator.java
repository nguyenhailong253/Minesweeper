package com.myob.minesweeper.model;

public class MineFieldValidator {
    public static boolean validateDimensionValuesInRange(int numRows, int numColumns, int minDimension, int maxDimension) {
        return validateNumberInRange(numRows, minDimension, maxDimension)
                && validateNumberInRange(numColumns, minDimension, maxDimension);
    }

    private static boolean validateNumberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static boolean validateContentOf2DStringArray(String[][] stringMatrix, String validPattern) {
        for (String[] stringArray: stringMatrix) {
            for (String squareValue: stringArray) {
                if (!validateSquareValue(squareValue, validPattern)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validateSquareValue(String squareValue, String validValue) {
        return squareValue.matches(validValue);
    }
}
