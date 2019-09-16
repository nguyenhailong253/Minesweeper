package com.myob.minesweeper.model;

public class MineFieldValidator {
    public static boolean validateDimensionValuesInRange(int numRows, int numColumns, int minDimension, int maxDimension) {
        return validateNumberInRange(numRows, minDimension, maxDimension)
                && validateNumberInRange(numColumns, minDimension, maxDimension);
    }

    // TODO: 16/9/19 (check test file) is it duplicated test?
    public static boolean validateNumberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }

    public static boolean validateLengthOfRowInput(String rowInput, int givenDimension) {
        return rowInput.length() == givenDimension;
    }
}
