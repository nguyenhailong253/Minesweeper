package com.myob.minesweeper.unit.utils;

import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.UserInputConverter;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UserInputConverterTest {

    private static final String DELIMITER = Constants.WHITESPACE_DELIMITER;

    @Test
    public void shouldReturnArrayOf2Integers_WhenReceive2Numbers() {
        int[] expectedOutputWith2Numbers = new int[]{100, 99};

        int[] actualOutput = UserInputConverter.convertStringToIntegerArray("100 99", DELIMITER);

        Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
    }

    @Test(expected = StringToNumberConversionException.class)
    public void shouldThrowConversionException_WhenReceive1NumberAnd1NonNumericalElement() {
        UserInputConverter.convertStringToIntegerArray("hello,./() 123", DELIMITER);
    }

    @Test(expected = StringToNumberConversionException.class)
    public void shouldThrowConversionException_WhenReceive2NonNumericalElements() {
        UserInputConverter.convertStringToIntegerArray("hello world", DELIMITER);
    }

    @Test(expected = StringToNumberConversionException.class)
    public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1Integer() {
        UserInputConverter.convertStringToIntegerArray("1.25 10", DELIMITER);
    }

    @Test(expected = StringToNumberConversionException.class)
    public void shouldThrowConversionException_WhenReceive2FloatingPoints() {
        UserInputConverter.convertStringToIntegerArray("0.5 100.98", DELIMITER);
    }

    @Test(expected = StringToNumberConversionException.class)
    public void shouldThrowConversionException_WhenReceive1FloatingPointAndRandomCharacters() {
        UserInputConverter.convertStringToIntegerArray("0.75 hello$%^&", DELIMITER);
    }
}
