package com.myob.minesweeper.service.input;

import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

public class UserInputValidatorTest {

    public static class TestValidateStringInputWithRequiredPattern_GivenFieldDimensionPattern {

        private String validFieldDimensionPattern = Constants.FIELD_DIMENSION_PATTERN;

        @Test
        public void shouldReturnTrue_WhenInputContains2NumbersSeparatedBy1WhiteSpace() {
            String spacesBetweenNumbers = "100 90";
            boolean inputContainsSpacesBetweenNumbers =
                    UserInputValidator.validateStringInputWithRequiredPattern(spacesBetweenNumbers, validFieldDimensionPattern);
            Assert.assertTrue(inputContainsSpacesBetweenNumbers);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsANumberFollowedBy1WhiteSpace() {
            String numberFollowedBySpace = "100 ";
            boolean inputContains1NumberAnd1Space =
                    UserInputValidator.validateStringInputWithRequiredPattern(numberFollowedBySpace, validFieldDimensionPattern);
            Assert.assertFalse(inputContains1NumberAnd1Space);
        }

        @Test
        public void shouldReturnFalse_WhenInputContains1WhiteSpaceFollowedByANumber() {
            String spaceFollowedByNumber = " 1";
            boolean inputContains1SpaceAnd1Number =
                    UserInputValidator.validateStringInputWithRequiredPattern(spaceFollowedByNumber, validFieldDimensionPattern);
            Assert.assertFalse(inputContains1SpaceAnd1Number);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsMoreThan1WhiteSpaceBetween2Numbers() {
            String numericalInput = "12       34";
            boolean inputWithOnlyNumbers =
                    UserInputValidator.validateStringInputWithRequiredPattern(numericalInput, validFieldDimensionPattern);
            Assert.assertFalse(inputWithOnlyNumbers);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsNonNumericalCharacters() {
            String inputWithNonNumericalCharacters = "hello12()-+ world34,./";
            boolean inputWithWordCharacters =
                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithNonNumericalCharacters, validFieldDimensionPattern);
            Assert.assertFalse(inputWithWordCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String numbersAndTrailingSpaces = "34  90  ";
            boolean inputWithTrailingSpaces =
                    UserInputValidator.validateStringInputWithRequiredPattern(numbersAndTrailingSpaces, validFieldDimensionPattern);
            Assert.assertFalse(inputWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsLeadingSpaces() {
            String leadingSpacesAndNumbers = "     1 2";
            boolean inputWithLeadingSpaces =
                    UserInputValidator.validateStringInputWithRequiredPattern(leadingSpacesAndNumbers, validFieldDimensionPattern);
            Assert.assertFalse(inputWithLeadingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputIsEmpty() {
            String emptyString = new String();
            boolean emptyInput =
                    UserInputValidator.validateStringInputWithRequiredPattern(emptyString, validFieldDimensionPattern);
            Assert.assertFalse(emptyInput);
        }

        @Test
        public void shouldReturnFalse_WhenInputIsBlank() {
            String blankString = "      ";
            boolean blankInput =
                    UserInputValidator.validateStringInputWithRequiredPattern(blankString, validFieldDimensionPattern);
            Assert.assertFalse(blankInput);
        }
    }

    public static class TestValidateStringInputWithRequiredPattern_GivenRowPattern {

        private String validRowPattern = Constants.ROW_PATTERN;

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsAsterisks() {
            String allAsterisks = "*******";
            boolean rowWithAsterisks = UserInputValidator.validateStringInputWithRequiredPattern(allAsterisks, validRowPattern);
            Assert.assertTrue(rowWithAsterisks);
        }

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsDots() {
            String allDots = "......";
            boolean rowWithDots = UserInputValidator.validateStringInputWithRequiredPattern(allDots, validRowPattern);
            Assert.assertTrue(rowWithDots);
        }

        @Test
        public void shouldReturnTrue_WhenInputContainsOnlyAsterisksAndDots() {
            String inputWithBothAsteriskAndDot = "*...*";
            boolean rowWithAsteriskAndDot =
                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithBothAsteriskAndDot, validRowPattern);
            Assert.assertTrue(rowWithAsteriskAndDot);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsAsteriskAndOtherCharacters() {
            String asteriskAndOtherKindsOfCharacters = "(****)123hello";
            boolean inputWithDifferentKindsOfCharacters =
                    UserInputValidator.validateStringInputWithRequiredPattern(asteriskAndOtherKindsOfCharacters, validRowPattern);
            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsDotsAndOtherCharacters() {
            String dotsAndOtherKindsOfCharacters = "....$%^&world123";
            boolean inputWithDifferentKindsOfCharacters =
                    UserInputValidator.validateStringInputWithRequiredPattern(dotsAndOtherKindsOfCharacters, validRowPattern);
            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsCharactersAsteriskAndDotAndOtherCharacters() {
            String inputWithMultipleCharacters = "hello***world....123()!@#$%^";
            boolean rowWithMultipleKindsOfCharacters =
                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithMultipleCharacters, validRowPattern);
            Assert.assertFalse(rowWithMultipleKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsNoAsterisksAndNoDots() {
            String inputWithNoAsteriskOrDot = "hello world 123";
            boolean rowWithoutAsteriskOrDot =
                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithNoAsteriskOrDot, validRowPattern);
            Assert.assertFalse(rowWithoutAsteriskOrDot);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasTrailingSpaces() {
            String trailingWhiteSpaceString = "*....   ";
            boolean rowWithTrailingSpaces =
                    UserInputValidator.validateStringInputWithRequiredPattern(trailingWhiteSpaceString, validRowPattern);
            Assert.assertFalse(rowWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasLeadingSpaces() {
            String leadingWhiteSpaceString = "*....   ";
            boolean rowWithLeadingSpaces =
                    UserInputValidator.validateStringInputWithRequiredPattern(leadingWhiteSpaceString, validRowPattern);
            Assert.assertFalse(rowWithLeadingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsBlank() {
            String blankString = "       ";
            boolean blankRow = UserInputValidator.validateStringInputWithRequiredPattern(blankString, validRowPattern);
            Assert.assertFalse(blankRow);
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsEmpty() {
            String emptyString = new String();
            boolean emptyRow = UserInputValidator.validateStringInputWithRequiredPattern(emptyString, validRowPattern);
            Assert.assertFalse(emptyRow);
        }
    }

    public static class TestValidateDimensionValuesInRange {

        private int MAX = Constants.MAX_SIZE;
        private int MIN = Constants.MIN_SIZE;

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAreEqualTo100() {
            int[] rowAndColumn = new int[]{100, 100};
            boolean dimensionLessThanOrEqualTo100 =
                    UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(dimensionLessThanOrEqualTo100);
        }

        @Test
        public void shouldReturnTrue_WhenRowsAndColumnsAre0() {
            int[] rowAndColumn = new int[]{0, 0};
            boolean zeroDimension = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIs0AndColumnIsMoreThan0() {
            int[] rowAndColumn = new int[]{0, 1};
            boolean zeroRowDimension = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroRowDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIsMoreThan0AndColumnIs0() {
            int[] rowAndColumn = new int[]{1, 0};
            boolean zeroColumnDimension = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertTrue(zeroColumnDimension);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfRows() {
            int[] rowAndColumn = new int[]{-1, 2};
            boolean negativeNumOfRows = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(negativeNumOfRows);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveNegativeNumOfColumns() {
            int[] rowAndColumn = new int[]{10, -100};
            boolean negativeNumOfColumn = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(negativeNumOfColumn);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveBothNumOfRowsAndColumnsAreNegative() {
            int[] rowAndColumn = new int[]{-100, -50};
            boolean twoNegativeDimensions = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfRowsMoreThan100() {
            int[] rowAndColumn = new int[]{500, 50};
            boolean numOfRowsMoreThan100 = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(numOfRowsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenNumOfColumnsMoreThan100() {
            int[] rowAndColumn = new int[]{60, 150};
            boolean numOfColumnsMoreThan100 = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(numOfColumnsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenBothRowsAndColumnsAreMoreThan100() {
            int[] rowAndColumn = new int[]{200, 109};
            boolean dimensionMoreThan100 = UserInputValidator.validateDimensionValuesInRange(rowAndColumn, MIN, MAX);
            Assert.assertFalse(dimensionMoreThan100);
        }
    }

    public static class TestValidateLengthOfRowInput {

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            int givenDimension = 4;
            boolean rowLengthMatchesDimension =
                    UserInputValidator.validateLengthOfRowInput(rowWithFourCharacters, givenDimension);
            Assert.assertTrue(rowLengthMatchesDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsDimension() {
            String rowWithSixCharacters = "*.*.*.";
            int givenDimension = 4;
            boolean rowLengthTooLong = UserInputValidator.validateLengthOfRowInput(rowWithSixCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsDimension() {
            String rowWithTwoCharacters = "..";
            int givenDimension = 4;
            boolean rowLengthTooShort = UserInputValidator.validateLengthOfRowInput(rowWithTwoCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooShort);
        }
    }

    public static class TestValidateEndOfInputValues {

        private int[] requiredEndOfInputValues = Constants.END_OF_INPUT_VALUE_SET;

        @Test
        public void shouldReturnTrue_WhenReceiveArrayWith2Zeros() {
            int[] twoZeros = new int[]{0,0};
            boolean isEndOfInput= UserInputValidator.validateEndOfInputValues(twoZeros, requiredEndOfInputValues);
            Assert.assertTrue(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith2PositiveIntegers() {
            int[] twoPositiveIntegers = new int[]{1, 100};
            boolean isEndOfInput =
                    UserInputValidator.validateEndOfInputValues(twoPositiveIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1PositiveIntegerAnd1Zero() {
            int[] positiveIntegerAndZero = new int[]{1, 0};
            boolean isEndOfInput =
                    UserInputValidator.validateEndOfInputValues(positiveIntegerAndZero, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith2NegativeIntegers() {
            int[] twoNegativeIntegers = new int[]{-1, -100};
            boolean isEndOfInput =
                    UserInputValidator.validateEndOfInputValues(twoNegativeIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1NegativeIntegerAnd1Zero() {
            int[] negativeIntegerAndZero = new int[]{-1, 0};
            boolean isEndOfInput =
                    UserInputValidator.validateEndOfInputValues(negativeIntegerAndZero, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayWith1PositiveAnd1NegativeIntegers() {
            int[] negativeAndPositiveInteger = new int[]{-1, 1};
            boolean isEndOfInput =
                    UserInputValidator.validateEndOfInputValues(negativeAndPositiveInteger, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayOfLength3() {
            int[] threeIntegers = new int[]{0, 0, 10};
            boolean isEndOfInput = UserInputValidator.validateEndOfInputValues(threeIntegers, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveArrayOfLength1() {
            int[] oneInteger = new int[]{0};
            boolean isEndOfInput = UserInputValidator.validateEndOfInputValues(oneInteger, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }

        @Test
        public void shouldReturnFalse_WhenReceiveEmptyArray() {
            int[] emptyArray = new int[]{};
            boolean isEndOfInput = UserInputValidator.validateEndOfInputValues(emptyArray, requiredEndOfInputValues);
            Assert.assertFalse(isEndOfInput);
        }
    }
}
