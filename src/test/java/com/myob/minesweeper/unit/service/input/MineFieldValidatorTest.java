package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.service.input.MineFieldValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class MineFieldValidatorTest {

    public static class TestValidateDimensionValuesInRange {

        private int MAX = Constants.MAX_SIZE;
        private int MIN = Constants.MIN_SIZE;

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAreEqualTo100() {
            int[] rowAndColumn = new int[]{100, 100};
            boolean dimensionLessThanOrEqualTo100 =
                    MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(dimensionLessThanOrEqualTo100);
        }

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAre0() {
            int[] rowAndColumn = new int[]{0, 0};
            boolean zeroDimension = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIs0AndColumnIsMoreThan0() {
            int[] rowAndColumn = new int[]{0, 1};
            boolean zeroRowDimension = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroRowDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIsMoreThan0AndColumnIs0() {
            int[] rowAndColumn = new int[]{1, 0};
            boolean zeroColumnDimension = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroColumnDimension);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfRows() {
            int[] rowAndColumn = new int[]{-1, 2};
            boolean negativeNumOfRows = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(negativeNumOfRows);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfColumns() {
            int[] rowAndColumn = new int[]{10, -100};
            boolean negativeNumOfColumn = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(negativeNumOfColumn);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveBothNumOfRowsAndColumnsAreNegative() {
            int[] rowAndColumn = new int[]{-100, -50};
            boolean twoNegativeDimensions = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfRowsMoreThan100() {
            int[] rowAndColumn = new int[]{500, 50};
            boolean numOfRowsMoreThan100 = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(numOfRowsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfColumnsMoreThan100() {
            int[] rowAndColumn = new int[]{60, 150};
            boolean numOfColumnsMoreThan100 = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(numOfColumnsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenBothRowsAndColumnsAreMoreThan100() {
            int[] rowAndColumn = new int[]{200, 109};
            boolean dimensionMoreThan100 = MineFieldValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(dimensionMoreThan100);
        }
    }

    public static class TestValidateLengthOfRowInput {

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            int givenDimension = 4;
            boolean rowLengthMatchesDimension =
                    MineFieldValidator.validateLengthOfRowInput(rowWithFourCharacters, givenDimension);
            Assert.assertTrue(rowLengthMatchesDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsDimension() {
            String rowWithSixCharacters = "*.*.*.";
            int givenDimension = 4;
            boolean rowLengthTooLong = MineFieldValidator.validateLengthOfRowInput(rowWithSixCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsDimension() {
            String rowWithTwoCharacters = "..";
            int givenDimension = 4;
            boolean rowLengthTooShort = MineFieldValidator.validateLengthOfRowInput(rowWithTwoCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooShort);
        }
    }

    public static class TestValidateEndOfInputValues {

        private int[] requiredEndOfInputValues = Constants.END_OF_INPUT_VALUE_SET;

        @Test
        public void shouldReturnTrue_WhenReceiveArrayWith2Zeros() {
            int[] twoZeros = new int[]{0,0};
            boolean isEndOfInput= MineFieldValidator.validateEndOfInputValues(twoZeros, requiredEndOfInputValues);
            Assert.assertTrue(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith2PositiveIntegers() {
            int[] twoPositiveIntegers = new int[]{1, 100};
            boolean isEndOfInput =
                    MineFieldValidator.validateEndOfInputValues(twoPositiveIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1PositiveIntegerAnd1Zero() {
            int[] positiveIntegerAndZero = new int[]{1, 0};
            boolean isEndOfInput =
                    MineFieldValidator.validateEndOfInputValues(positiveIntegerAndZero, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith2NegativeIntegers() {
            int[] twoNegativeIntegers = new int[]{-1, -100};
            boolean isEndOfInput =
                    MineFieldValidator.validateEndOfInputValues(twoNegativeIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1NegativeIntegerAnd1Zero() {
            int[] negativeIntegerAndZero = new int[]{-1, 0};
            boolean isEndOfInput =
                    MineFieldValidator.validateEndOfInputValues(negativeIntegerAndZero, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1PositiveAnd1NegativeIntegers() {
            int[] negativeAndPositiveInteger = new int[]{-1, 1};
            boolean isEndOfInput =
                    MineFieldValidator.validateEndOfInputValues(negativeAndPositiveInteger, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayOfLength3() {
            int[] threeIntegers = new int[]{0, 0, 10};
            boolean isEndOfInput = MineFieldValidator.validateEndOfInputValues(threeIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayOfLength1() {
            int[] oneInteger = new int[]{0};
            boolean isEndOfInput = MineFieldValidator.validateEndOfInputValues(oneInteger, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveEmptyArray() {
            int[] emptyArray = new int[]{};
            boolean isEndOfInput = MineFieldValidator.validateEndOfInputValues(emptyArray, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }
    }
}
