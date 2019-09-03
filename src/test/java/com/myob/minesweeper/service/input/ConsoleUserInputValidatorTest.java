package com.myob.minesweeper.service.input;

import org.junit.Assert;
import org.junit.Test;

public class ConsoleUserInputValidatorTest {

    private static IUserInputValidator validator = new ConsoleUserInputValidator();

    public static class TestValidateInputDimensionFormat {

        @Test
        public void shouldReturnTrue_WhenInputContains2NumbersSeparatedBy1WhiteSpace() {
            String spacesBetweenNumbers = "100 90";
            boolean inputContainsSpacesBetweenNumbers = validator.validateInputDimensionFormat(spacesBetweenNumbers);
            Assert.assertTrue(inputContainsSpacesBetweenNumbers);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsANumberFollowedBy1WhiteSpace() {
            String numberFollowedBySpace = "100 ";
            boolean inputContains1NumberAnd1Space = validator.validateInputDimensionFormat(numberFollowedBySpace);
            Assert.assertFalse(inputContains1NumberAnd1Space);
        }

        @Test
        public void shouldReturnFalse_WhenInputContains1WhiteSpaceFollowedByANumber() {
            String spaceFollowedByNumber = " 1";
            boolean inputContains1SpaceAnd1Number = validator.validateInputDimensionFormat(spaceFollowedByNumber);
            Assert.assertFalse(inputContains1SpaceAnd1Number);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsMoreThan1WhiteSpaceBetween2Numbers() {
            String numericalInput = "12       34";
            boolean inputWithOnlyNumbers = validator.validateInputDimensionFormat(numericalInput);
            Assert.assertFalse(inputWithOnlyNumbers);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsNonNumericalCharacters() {
            String inputWithNonNumericalCharacters = "hello12()-+ world34,./";
            boolean inputWithWordCharacters = validator.validateInputDimensionFormat(inputWithNonNumericalCharacters);
            Assert.assertFalse(inputWithWordCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String numbersAndTrailingSpaces = "34  90  ";
            boolean inputWithTrailingSpaces = validator.validateInputDimensionFormat(numbersAndTrailingSpaces);
            Assert.assertFalse(inputWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsLeadingSpaces() {
            String leadingSpacesAndNumbers = "     1 2";
            boolean inputWithLeadingSpaces = validator.validateInputDimensionFormat(leadingSpacesAndNumbers);
            Assert.assertFalse(inputWithLeadingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputIsEmpty() {
            String emptyString = new String();
            boolean emptyInput = validator.validateInputDimensionFormat(emptyString);
            Assert.assertFalse(emptyInput);
        }

        @Test
        public void shouldReturnFalse_WhenInputIsBlank() {
            String blankString = "      ";
            boolean blankInput = validator.validateInputDimensionFormat(blankString);
            Assert.assertFalse(blankInput);
        }
    }

    public static class TestValidateDimensionsInRange {

        @Test
        public void shouldReturnTrue_WhenGivenRowsAndColumnsAreLessThanOrEqualTo100() {
            int rows = 100;
            int columns = 100;
            boolean dimensionLessThanOrEqualTo100 = validator.validateDimensionsInRange(rows, columns);
            Assert.assertTrue(dimensionLessThanOrEqualTo100);
        }

        @Test
        public void shouldReturnTrue_WhenGivenRowsAndColumnsAre0() {
            int rows = 0;
            int columns = 0;
            boolean zeroDimension = validator.validateDimensionsInRange(rows, columns);
            Assert.assertTrue(zeroDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIs0AndColumnIsMoreThan0() {
            int rows = 0;
            int columns = 1;
            boolean zeroRowDimension = validator.validateDimensionsInRange(rows, columns);
            Assert.assertTrue(zeroRowDimension);
        }

        @Test
        public void shouldReturnTrue_WhenRowIsMoreThan0AndColumnIs0() {
            int rows = 1;
            int columns = 0;
            boolean zeroColumnDimension = validator.validateDimensionsInRange(rows, columns);
            Assert.assertTrue(zeroColumnDimension);
        }

        @Test
        public void shouldReturnFalse_WhenGivenNegativeNumOfRows() {
            int rows = -1;
            int columns = 2;
            boolean negativeNumOfRows = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(negativeNumOfRows);
        }

        @Test
        public void shouldReturnFalse_WhenGivenNegativeNumOfColumns() {
            int rows = 10;
            int columns = -100;
            boolean negativeNumOfColumn = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(negativeNumOfColumn);
        }

        @Test
        public void shouldReturnFalse_WhenGivenBothNumOfRowsAndColumnsAreNegative() {
            int rows = -100;
            int columns = -50;
            boolean twoNegativeDimensions = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnFalse_WhenGivenNumOfRowsMoreThan100() {
            int rows = 500;
            int columns = 50;
            boolean numOfRowsMoreThan100 = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(numOfRowsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenGivenNumOfColumnsMoreThan100() {
            int rows = 60;
            int columns = 150;
            boolean numOfColumnsMoreThan100 = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(numOfColumnsMoreThan100);
        }

        @Test
        public void shouldReturnFalse_WhenGivenBothRowsAndColumnsAreMoreThan100() {
            int rows = 200;
            int columns = 109;
            boolean dimensionMoreThan100 = validator.validateDimensionsInRange(rows, columns);
            Assert.assertFalse(dimensionMoreThan100);
        }
    }

    public static class TestValidateRowContent {

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsAsterisks() {
            String allAsterisks = "*******";
            boolean rowWithAsterisks = validator.validateRowContent(allAsterisks);
            Assert.assertTrue(rowWithAsterisks);
        }

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsDots() {
            String allDots = "......";
            boolean rowWithDots = validator.validateRowContent(allDots);
            Assert.assertTrue(rowWithDots);
        }

        @Test
        public void shouldReturnTrue_WhenInputContainsOnlyAsterisksAndDots() {
            String inputWithBothAsteriskAndDot = "*...*";
            boolean rowWithAsteriskAndDot = validator.validateRowContent(inputWithBothAsteriskAndDot);
            Assert.assertTrue(rowWithAsteriskAndDot);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsAsteriskAndOtherCharacters() {
            String asteriskAndOtherKindsOfCharacters = "(****)123hello";
            boolean inputWithDifferentKindsOfCharacters = validator.validateRowContent(asteriskAndOtherKindsOfCharacters);
            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsDotsAndOtherCharacters() {
            String dotsAndOtherKindsOfCharacters = "....$%^&world123";
            boolean inputWithDifferentKindsOfCharacters = validator.validateRowContent(dotsAndOtherKindsOfCharacters);
            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsCharactersAsteriskAndDotAndOtherCharacters() {
            String inputWithMultipleCharacters = "hello***world....123()!@#$%^";
            boolean rowWithMultipleKindsOfCharacters = validator.validateRowContent(inputWithMultipleCharacters);
            Assert.assertFalse(rowWithMultipleKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsNoAsterisksAndNoDots() {
            String inputWithNoAsteriskOrDot = "hello world 123";
            boolean rowWithoutAsteriskOrDot = validator.validateRowContent(inputWithNoAsteriskOrDot);
            Assert.assertFalse(rowWithoutAsteriskOrDot);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasTrailingSpaces() {
            String trailingWhiteSpaceString = "*....   ";
            boolean rowWithTrailingSpaces = validator.validateRowContent(trailingWhiteSpaceString);
            Assert.assertFalse(rowWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasLeadingSpaces() {
            String leadingWhiteSpaceString = "*....   ";
            boolean rowWithLeadingSpaces = validator.validateRowContent(leadingWhiteSpaceString);
            Assert.assertFalse(rowWithLeadingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsBlank() {
            String blankString = "       ";
            boolean blankRow = validator.validateRowContent(blankString);
            Assert.assertFalse(blankRow);
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsEmpty() {
            String emptyString = "";
            boolean emptyRow = validator.validateRowContent(emptyString);
            Assert.assertFalse(emptyRow);
        }
    }

    public static class TestValidateLengthOfRowInput {

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            int givenDimension = 4;
            boolean rowLengthMatchesDimension = validator.validateLengthOfRowInput(rowWithFourCharacters, givenDimension);
            Assert.assertTrue(rowLengthMatchesDimension);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsDimension() {
            String rowWithSixCharacters = "*.*.*.";
            int givenDimension = 4;
            boolean rowLengthTooLong = validator.validateLengthOfRowInput(rowWithSixCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsDimension() {
            String rowWithTwoCharacters = "..";
            int givenDimension = 4;
            boolean rowLengthTooShort = validator.validateLengthOfRowInput(rowWithTwoCharacters, givenDimension);
            Assert.assertFalse(rowLengthTooShort);
        }
    }
}
