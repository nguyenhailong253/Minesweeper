package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.service.input.UserInputConverter;
import org.junit.Assert;
import org.junit.Test;

public class UserInputConverterTest {

    public static class TestConvertStringArrayToIntegerArray {

        @Test
        public void shouldReturnArrayOf2Integers_WhenReceiveStringArrayOf2Numbers() {
            int[] expectedOutputWith2Numbers = new int[]{100, 99};
            String[] stringWith2Numbers = new String[]{"100", "99"};

            int[] actualOutput = UserInputConverter.convertStringArrayToIntegerArray(stringWith2Numbers);

            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1NumberAnd1NonNumericalElement() {
            String[] inputWith1NonInteger = new String[]{"hello,./()", "123"};
            UserInputConverter.convertStringArrayToIntegerArray(inputWith1NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2NonNumericalElements() {
            String[] inputWith2NonInteger = new String[]{"hello", "world"};
            UserInputConverter.convertStringArrayToIntegerArray(inputWith2NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1Integer() {
            String[] inputWith1FloatingPoint = new String[]{"1.25", "10"};
            UserInputConverter.convertStringArrayToIntegerArray(inputWith1FloatingPoint);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2FloatingPoints() {
            String[] inputWith2FloatingPoints = new String[]{"0.5", "100.98"};
            UserInputConverter.convertStringArrayToIntegerArray(inputWith2FloatingPoints);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1RandomCharacter() {
            String[] invalidInput = new String[]{"0.75", "hello$%^&"};
            UserInputConverter.convertStringArrayToIntegerArray(invalidInput);
        }
    }
}
