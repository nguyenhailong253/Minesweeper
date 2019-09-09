package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.exception.InvalidNumberOfInputDimensionException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UserInputConverterTest {

    public static class TestSplitStringToArray {
        private String delimiter = Constants.WHITESPACE_DELIMITER;
        private int requiredLength = Constants.REQUIRED_LENGTH;

        @Test
        public void shouldReturnArrayOf2Integers_WhenReceiveStringArrayOf2Integers() {
            String[] expectedOutputWith2Numbers = new String[]{"100", "99"};
            String stringWith2Numbers = new String("100 99");

            String[] actualOutput = UserInputConverter.splitStringToArray(stringWith2Numbers, delimiter, requiredLength);

            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceive1NumberFollowedBy1Space() {
            String stringWith1NumberAnd1Space = new String("1 ");
            UserInputConverter.splitStringToArray(stringWith1NumberAnd1Space, delimiter, requiredLength);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceive1SpaceFollowedBy1Number() {
            String stringWith1SpaceAnd1Number = new String(" 10");
            UserInputConverter.splitStringToArray(stringWith1SpaceAnd1Number, delimiter, requiredLength);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceive1Number() {
            String oneNumber = new String("25");
            UserInputConverter.splitStringToArray(oneNumber, delimiter, requiredLength);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceiveMoreThan2NumbersAnd1Space() {
            String stringWith3NumbersAnd2Spaces = new String("100 99 98");
            UserInputConverter.splitStringToArray(stringWith3NumbersAnd2Spaces, delimiter, requiredLength);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceiveBlankString() {
            String blankString = new String(" ");
            UserInputConverter.splitStringToArray(blankString, delimiter, requiredLength);
        }

        @Test(expected = InvalidNumberOfInputDimensionException.class)
        public void shouldThrowException_WhenReceiveEmptyStringArray() {
            String emptyString = new String();
            UserInputConverter.splitStringToArray(emptyString, delimiter, requiredLength);
        }
    }

    public static class TestConvertToNumericalDimensions {

        @Test
        public void shouldReturnArrayOf2Integers_WhenReceiveStringArrayOf2Numbers() {
            int[] expectedOutputWith2Numbers = new int[]{100, 99};
            String[] stringWith2Numbers = new String[]{"100", "99"};

            int[] actualOutput = UserInputConverter.convertStringToIntegerArray(stringWith2Numbers);

            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1NumberAnd1NonNumericalElement() {
            String[] inputWith1NonInteger = new String[]{"hello,./()", "123"};
            UserInputConverter.convertStringToIntegerArray(inputWith1NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2NonNumericalElements() {
            String[] inputWith2NonInteger = new String[]{"hello", "world"};
            UserInputConverter.convertStringToIntegerArray(inputWith2NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1Integer() {
            String[] inputWith1FloatingPoint = new String[]{"1.25", "10"};
            UserInputConverter.convertStringToIntegerArray(inputWith1FloatingPoint);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive2FloatingPoints() {
            String[] inputWith2FloatingPoints = new String[]{"0.5", "100.98"};
            UserInputConverter.convertStringToIntegerArray(inputWith2FloatingPoints);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowConversionException_WhenReceive1FloatingPointAnd1RandomCharacter() {
            String[] invalidInput = new String[]{"0.75", "hello$%^&"};
            UserInputConverter.convertStringToIntegerArray(invalidInput);
        }
    }
}
