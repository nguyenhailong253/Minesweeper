package com.myob.minesweeper.utils;

import com.myob.minesweeper.exception.StringToNumberConversionException;

public class UserInputConverter {

    public static int[] convertStringToIntegerArray(String userInput, String delimiter) {
        try {
            String[] userInputArray = userInput.trim().split(delimiter);
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
}
