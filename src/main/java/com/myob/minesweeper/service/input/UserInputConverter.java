package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidNumberOfInputDimensionException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.Constants;

public class UserInputConverter {

    public static int[] convertStringToIntegerArray(String[] userInputDimension) {
        try {
            int[] dimensionList = new int[userInputDimension.length];
            for (int i = 0; i < userInputDimension.length; i++) {
                int dimensionValue = Integer.parseInt(userInputDimension[i]);
                dimensionList[i] = dimensionValue;
            }
            return dimensionList;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Constants.STRING_TO_NUMBER_EXCEPTION);
        }
    }

    public static String[] splitStringToArray(String input, String delimiter, int arrayLength) {
        String[] splitInput = input.trim().split(delimiter);

        if (splitInput.length != arrayLength) {
            throw new InvalidNumberOfInputDimensionException(Constants.INVALID_INPUT_DIMENSION);
        }
        return splitInput;
    }
}
