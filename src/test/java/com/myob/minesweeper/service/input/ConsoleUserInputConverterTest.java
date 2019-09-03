package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidInputDimensionFormatException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import org.junit.Assert;
import org.junit.Test;

public class ConsoleUserInputConverterTest {

    private static IUserInputConverter converter = new ConsoleUserInputConverter();

    public static class TestConvertToNumericalDimensions {

        @Test
        public void shouldReturnArrayOf2Integers_WhenReceiveStringArrayOf2Integers() {
            int[] expectedOutputWith2Numbers = new int[]{100, 99};
            String stringWith2Numbers = new String("100 99");

            int[] actualOutput = converter.convertToNumericalDimensions(stringWith2Numbers);

            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceive1NumberFollowedBy1Space() {
            String stringWith1NumberAnd1Space = new String("1 ");
            converter.convertToNumericalDimensions(stringWith1NumberAnd1Space);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceive1SpaceFollowedBy1Number() {
            String stringWith1SpaceAnd1Number = new String(" 10");
            converter.convertToNumericalDimensions(stringWith1SpaceAnd1Number);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceive1Number() {
            String oneNumber = new String("25");
            converter.convertToNumericalDimensions(oneNumber);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceiveMoreThan2NumbersAnd1Space() {
            String stringWith3NumbersAnd2Spaces = new String("100 99 98");
            converter.convertToNumericalDimensions(stringWith3NumbersAnd2Spaces);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceiveBlankString() {
            String blankString = new String(" ");
            converter.convertToNumericalDimensions(blankString);
        }

        @Test(expected = InvalidInputDimensionFormatException.class)
        public void shouldThrowFormatException_WhenReceiveEmptyStringArray() {
            String emptyString = new String();
            converter.convertToNumericalDimensions(emptyString);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1IntegerAnd1NonIntegerElement() {
            String inputWith1NonInteger = new String("hello,./() 123");
            converter.convertToNumericalDimensions(inputWith1NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2NonIntegerElements() {
            String inputWith2NonInteger = new String("hello world");
            converter.convertToNumericalDimensions(inputWith2NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1Integer() {
            String inputWith1FloatingPoint = new String("1.25 10");
            converter.convertToNumericalDimensions(inputWith1FloatingPoint);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2FloatingPoints() {
            String inputWith2FloatingPoints = new String("0.5 100.98");
            converter.convertToNumericalDimensions(inputWith2FloatingPoints);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1RandomCharacter() {
            String invalidInput = new String("0.75 hello$%^&");
            converter.convertToNumericalDimensions(invalidInput);
        }
    }
}
