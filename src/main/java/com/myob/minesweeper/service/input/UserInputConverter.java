package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidNumberOfDimensionsException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.Constants;

public class UserInputConverter {
    public String[] splitStringToArrayOf2Elements(String input) {
        String[] splitInput = input.trim().split(Constants.SINGLE_SPACE_PATTERN);

        if (splitInput.length != Constants.TWO_DIMENSION) {
            throw new InvalidNumberOfDimensionsException(Constants.WRONG_NUMBER_OF_DIMENSIONS);
        }
        return splitInput;
    }

    public int[] convertToNumericalDimensions(String[] userInput) {
        try {
            int[] dimensionList = new int[userInput.length];
            for (int i = 0; i < userInput.length; i++) {
                int dimensionValue = Integer.parseInt(userInput[i]);
                dimensionList[i] = dimensionValue;
            }
            return dimensionList;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Constants.STRING_TO_NUMBER_EXCEPTION, e);
        }
    }
}
