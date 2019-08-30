package com.myob.minesweeper.service.input;

import com.myob.minesweeper.utils.Constants;

public class UserInputValidator {

    public boolean validateInputHavingNumbersWithSpaceAsDelimiter(String inputDimension) {
        return inputDimension.matches(Constants.VALID_BOARD_DIMENSION_PATTERN);
    }

    public boolean validateDimensionsInRange(int numRows, int numColumns) {
        if (numRows < Constants.MIN_FIELD_SIZE
                || numRows > Constants.MAX_FIELD_SIZE
                || numColumns < Constants.MIN_FIELD_SIZE
                || numColumns > Constants.MAX_FIELD_SIZE) {
            return false;
        }
        return true;
    }

    public boolean validateRowContent(String rowContent) {
        String invalidSubString = rowContent.replaceAll(Constants.VALID_ROW_PATTERN, "");
        if (!invalidSubString.isEmpty() || rowContent.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateLengthOfRowInput(String rowInput, int givenDimension) {
        return rowInput.length() == givenDimension;
    }
}
