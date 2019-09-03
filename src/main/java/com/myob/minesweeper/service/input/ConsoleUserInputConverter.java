package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidInputDimensionFormatException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.Constants;

public class ConsoleUserInputConverter implements IUserInputConverter {

    private String[] splitStringToArrayOf2Elements(String input) {
        String[] splitInput = input.trim().split(Constants.SINGLE_SPACE_PATTERN);

        if (splitInput.length != Constants.TWO_DIMENSION) {
            throw new InvalidInputDimensionFormatException(Constants.INVALID_DIMENSION_FORMAT);
        }
        return splitInput;
    }

    @Override
    public int[] convertToNumericalDimensions(String userInput) {
        String[] splitInput = splitStringToArrayOf2Elements(userInput);
        try {
            int[] dimensionList = new int[splitInput.length];
            for (int i = 0; i < splitInput.length; i++) {
                int dimensionValue = Integer.parseInt(splitInput[i]);
                dimensionList[i] = dimensionValue;
            }
            return dimensionList;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Constants.STRING_TO_NUMBER_EXCEPTION);
        }
    }
}
