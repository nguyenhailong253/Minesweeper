package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.model.MineFieldValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class MineFieldValidatorTest {

    public static class TestValidateDimensionValuesInRange {

        private int MAX = Constants.MAX_SIZE;
        private int MIN = Constants.MIN_SIZE;

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAreEqualTo100() {
            boolean dimensionLessThanOrEqualTo100 =
                    MineFieldValidator.validateDimensionValuesInRange(100, 100, MIN, MAX);
            Assert.assertTrue(dimensionLessThanOrEqualTo100);
        }

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAre0() {
            boolean zeroDimension =
                    MineFieldValidator.validateDimensionValuesInRange(0, 0, MIN, MAX);
            Assert.assertTrue(zeroDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIs0AndColumnIsMoreThan0() {
            boolean zeroRowDimension =
                    MineFieldValidator.validateDimensionValuesInRange(0, 1, MIN, MAX);
            Assert.assertTrue(zeroRowDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIsMoreThan0AndColumnIs0() {
            int[] rowAndColumn = new int[]{1, 0};
            boolean zeroColumnDimension =
                    MineFieldValidator.validateDimensionValuesInRange(1, 0, MIN, MAX);
            Assert.assertTrue(zeroColumnDimension);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfRows() {
            boolean negativeNumOfRows =
                    MineFieldValidator.validateDimensionValuesInRange(-1, 2, MIN, MAX);
            Assert.assertFalse(negativeNumOfRows);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfColumns() {
            boolean negativeNumOfColumn =
                    MineFieldValidator.validateDimensionValuesInRange(10, -100, MIN, MAX);
            Assert.assertFalse(negativeNumOfColumn);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveBothNumOfRowsAndColumnsAreNegative() {
            int[] rowAndColumn = new int[]{-100, -50};
            boolean twoNegativeDimensions =
                    MineFieldValidator.validateDimensionValuesInRange(-100, -50, MIN, MAX);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfRowsMoreThan100() {
            int[] rowAndColumn = new int[]{500, 50};
            boolean numOfRowsMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(500, 50, MIN, MAX);
            Assert.assertFalse(numOfRowsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfColumnsMoreThan100() {
            int[] rowAndColumn = new int[]{60, 150};
            boolean numOfColumnsMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(60, 150, MIN, MAX);
            Assert.assertFalse(numOfColumnsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenBothRowsAndColumnsAreMoreThan100() {
            int[] rowAndColumn = new int[]{200, 109};
            boolean dimensionMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(200, 109, MIN, MAX);
            Assert.assertFalse(dimensionMoreThan100);
        }
    }

    public static class TestValidateLengthOfRowInput {

        private int givenDimension = 4;

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            boolean rowLengthMatchesDimension =
                    MineFieldValidator.validateLengthOfRowInput(rowWithFourCharacters, givenDimension);
            Assert.assertTrue(rowLengthMatchesDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsDimension() {
            String rowWithSixCharacters = "*.*.*.";
            boolean rowLengthTooLong =
                    MineFieldValidator.validateLengthOfRowInput(rowWithSixCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsDimension() {
            String rowWithTwoCharacters = "..";
            boolean rowLengthTooShort =
                    MineFieldValidator.validateLengthOfRowInput(rowWithTwoCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooShort);
        }
    }
}
