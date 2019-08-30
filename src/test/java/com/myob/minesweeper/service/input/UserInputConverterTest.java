package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidNumberOfDimensionsException;
import com.myob.minesweeper.exception.StringToNumberConversionException;
import org.junit.Assert;
import org.junit.Test;

public class UserInputConverterTest {

    static UserInputConverter converter = new UserInputConverter();

    public static class TestSplitStringToArrayOf2Elements {

        @Test
        public void shouldReturnStringArrayOfSize2_WhenGivenStringOf2Numbers() {
            String[] expectedArraySize2 = new String[]{"100", "99"};

            String stringWith2Numbers = new String("100 99");
            String[] actualArray = converter.splitStringToArrayOf2Elements(stringWith2Numbers);

            Assert.assertArrayEquals(actualArray, expectedArraySize2);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGiven1NumberFollowedBy1Space() {
            String stringWith1NumberAnd1Space = new String("1 ");
            converter.splitStringToArrayOf2Elements(stringWith1NumberAnd1Space);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGiven1SpaceFollowedBy1Number() {
            String stringWith1SpaceAnd1Number = new String(" 10");
            converter.splitStringToArrayOf2Elements(stringWith1SpaceAnd1Number);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGiven1Number() {
            String oneNumber = new String("25");
            converter.splitStringToArrayOf2Elements(oneNumber);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGivenMoreThan2NumbersAnd1Space() {
            String stringWith3NumbersAnd2Spaces = new String("100 99 98");
            converter.splitStringToArrayOf2Elements(stringWith3NumbersAnd2Spaces);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGivenEmptyString() {
            String emptyString = new String();
            converter.splitStringToArrayOf2Elements(emptyString);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGivenBlankString() {
            String blankString = new String(" ");
            converter.splitStringToArrayOf2Elements(blankString);
        }
    }

    public static class TestConvertToNumericalDimensions {

        @Test
        public void shouldReturnArrayOf2Integers_WhenGivenStringArrayOf2Integers() {
            int[] expectedOutputWith2Numbers = new int[]{100, 99};

            String[] stringArrayWith2Numbers = new String[]{"100", "99"};
            int[] actualOutput = converter.convertToNumericalDimensions(stringArrayWith2Numbers);

            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test
        public void shouldReturnEmptyArray_WhenGivenEmptyStringArray() {
            int[] expectedEmptyOuput = new int[]{};

            String[] emptyString = new String[]{};
            int[] actualOutput = converter.convertToNumericalDimensions(emptyString);

            Assert.assertArrayEquals(actualOutput, expectedEmptyOuput);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGiven1IntegerAnd1NonIntegerElement() {
            String[] inputWith1NonInteger = new String[]{"hello,./()", "123"};
            converter.convertToNumericalDimensions(inputWith1NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGiven2NonIntegerElements() {
            String[] inputWith2NonInteger = new String[] {"hello", "world"};
            converter.convertToNumericalDimensions(inputWith2NonInteger);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGiven1FloatingPointAnd1Integer() {
            String[] inputWith1FloatingPoint = new String[] {"1.25", "10"};
            converter.convertToNumericalDimensions(inputWith1FloatingPoint);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGiven2FloatingPoints() {
            String[] inputWith2FloatingPoints = new String[] {"0.5", "100.98"};
            converter.convertToNumericalDimensions(inputWith2FloatingPoints);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGiven1FloatingPointAnd1RandomCharacter() {
            String[] invalidInput = new String[] {"0.75", "hello$%^&"};
            converter.convertToNumericalDimensions(invalidInput);
        }
    }
}
