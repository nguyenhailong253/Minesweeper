package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.Constants;

public class UserInputConverter {

    public static int[] convertStringArrayToIntegerArray(String[] userInputArray) {
        try {
            int[] result = new int[userInputArray.length];
            for (int i = 0; i < userInputArray.length; i++) {
                int convertedInteger = Integer.parseInt(userInputArray[i]);
                result[i] = convertedInteger;
            }
            return result;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Constants.STRING_TO_NUMBER_EXCEPTION);
        }
    }

    public static String[] splitStringToArray(String input, String delimiter) {
        String[] splitInput = input.trim().split(delimiter);
        return splitInput;
    }
}
