package com.myob.minesweeper.service.input;

import com.myob.minesweeper.utils.Constants;

import java.util.Arrays;

public class ConsoleUserInputValidator implements IUserInputValidator {

    @Override
    public boolean validateInputDimensionFormat(String inputDimension) {
        return inputDimension.matches(Constants.VALID_FIELD_DIMENSION_PATTERN);
    }

    @Override
    public boolean validateDimensionsInRange(int numRows, int numColumns) {
        if (numRows < Constants.MIN_FIELD_SIZE
                || numRows > Constants.MAX_FIELD_SIZE
                || numColumns < Constants.MIN_FIELD_SIZE
                || numColumns > Constants.MAX_FIELD_SIZE) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateRowContent(String rowContent) {
        String invalidSubString = rowContent.replaceAll(Constants.VALID_ROW_PATTERN, Constants.EMPTY_STRING);
        if (!invalidSubString.isEmpty() || rowContent.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateLengthOfRowInput(String rowInput, int givenDimension) {
        return rowInput.length() == givenDimension;
    }

    @Override
    public boolean validateEndOfInputPattern(int[] convertedUserInputDimension) {
        return Arrays.equals(convertedUserInputDimension, Constants.END_OF_INPUT_VALUE_SET);
    }
}
