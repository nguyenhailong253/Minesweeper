package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.model.MineFieldValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class MineFieldValidatorTest {

    public static class TestValidateDimensionValuesInRange {

        private static final int MAX = Constants.MAX_SIZE;
        private static final int MIN = Constants.MIN_SIZE;

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAreEqualTo100() {
            boolean maxDimension =
                    MineFieldValidator.validateDimensionValuesInRange(100, 100, MIN, MAX);
            Assert.assertTrue(maxDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAre1() {
            boolean minDimension =
                    MineFieldValidator.validateDimensionValuesInRange(1, 1, MIN, MAX);
            Assert.assertTrue(minDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIs1AndColumnIsMoreThan1() {
            boolean minRowDimension =
                    MineFieldValidator.validateDimensionValuesInRange(1, 2, MIN, MAX);
            Assert.assertTrue(minRowDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIsMoreThan1AndColumnIs1() {
            boolean minColumnDimension =
                    MineFieldValidator.validateDimensionValuesInRange(2, 1, MIN, MAX);
            Assert.assertTrue(minColumnDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowIs0AndColumnIsInRange() {
            boolean zeroRowDimension =
                    MineFieldValidator.validateDimensionValuesInRange(0, 1, MIN, MAX);
            Assert.assertFalse(zeroRowDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowIsInRangeAndColumnIs0() {
            boolean zeroColumnDimension =
                    MineFieldValidator.validateDimensionValuesInRange(1, 0, MIN, MAX);
            Assert.assertFalse(zeroColumnDimension);
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
            boolean twoNegativeDimensions =
                    MineFieldValidator.validateDimensionValuesInRange(-100, -50, MIN, MAX);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfRowsMoreThan100() {
            boolean numOfRowsMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(500, 50, MIN, MAX);
            Assert.assertFalse(numOfRowsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfColumnsMoreThan100() {
            boolean numOfColumnsMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(60, 150, MIN, MAX);
            Assert.assertFalse(numOfColumnsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenBothRowsAndColumnsAreMoreThan100() {
            boolean dimensionMoreThan100 =
                    MineFieldValidator.validateDimensionValuesInRange(200, 109, MIN, MAX);
            Assert.assertFalse(dimensionMoreThan100);
        }
    }

    public static class TestValidateNumberInRange {
        private static final int MIN = 0;
        private static final int MAX = 5;

        @Test
        public void shouldReturnTrue_WhenNumberIsEqualToMinValue() {
            int number = MIN;
            boolean numberIsInRange = MineFieldValidator.validateNumberInRange(number, MIN, MAX);
            Assert.assertTrue(numberIsInRange);
        }

        @Test
        public void shouldReturnTrue_WhenNumberIsBiggerThanMinValueButSmallerThanMaxValue() {
            int number = 3;
            boolean numberIsInRange = MineFieldValidator.validateNumberInRange(number, MIN, MAX);
            Assert.assertTrue(numberIsInRange);
        }

        @Test
        public void shouldReturnTrue_WhenNumberIsEqualToMaxValue() {
            int number = MAX;
            boolean numberIsInRange = MineFieldValidator.validateNumberInRange(number, MIN, MAX);
            Assert.assertTrue(numberIsInRange);
        }

        @Test
        public void shouldReturnFalse_WhenNumberIsSmallerThanMin() {
            int number = -1;
            boolean numberIsInRange = MineFieldValidator.validateNumberInRange(number, MIN, MAX);
            Assert.assertFalse(numberIsInRange);
        }

        @Test
        public void shouldReturnFalse_WhenNumberIsBiggerThanMax() {
            int number = 10000;
            boolean numberIsInRange = MineFieldValidator.validateNumberInRange(number, MIN, MAX);
            Assert.assertFalse(numberIsInRange);
        }
    }

    public static class TestValidateLengthOfRowInput {

        private static final int GIVEN_DIMENSION = 4;

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            boolean rowLengthMatchesDimension =
                    MineFieldValidator.validateLengthOfRowInput(rowWithFourCharacters, GIVEN_DIMENSION);
            Assert.assertTrue(rowLengthMatchesDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsDimension() {
            String rowWithSixCharacters = "*.*.*.";
            boolean rowLengthTooLong =
                    MineFieldValidator.validateLengthOfRowInput(rowWithSixCharacters, GIVEN_DIMENSION);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsDimension() {
            String rowWithTwoCharacters = "..";
            boolean rowLengthTooShort =
                    MineFieldValidator.validateLengthOfRowInput(rowWithTwoCharacters, GIVEN_DIMENSION);
            Assert.assertFalse(rowLengthTooShort);
        }
    }
}
