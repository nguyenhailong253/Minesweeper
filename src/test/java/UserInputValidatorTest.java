import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserInputValidatorTest {

    static UserInputValidator validator = new UserInputValidator();

    public static class ValidateInputDimensionsAreNumbersTest {

        @Test
        public void shouldReturnFalse_WhenInputContainsNonNumericalCharacters() {
            String inputWithNonNumericalCharacters = "hello1234";
            boolean inputWithWordCharacters = validator.validateInputDimensionsAreNumbers(inputWithNonNumericalCharacters);
            Assert.assertFalse(inputWithWordCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String inputDimensionsWithTrailingSpaces = "    34  ";
            boolean inputWithTrailingSpaces = validator.validateInputDimensionsAreNumbers(inputDimensionsWithTrailingSpaces);
            Assert.assertFalse(inputWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsSpacesBetweenNumbers() {
            String spacesBetweenNumbers = "100 90";
            boolean inputContainsSpacesBetweenNumbers = validator.validateInputDimensionsAreNumbers(spacesBetweenNumbers);
            Assert.assertFalse(inputContainsSpacesBetweenNumbers);
        }

        @Test
        public void shouldReturnTrue_WhenInputContainsOnlyNumbers() {
            String numericalInput = "1234";
            boolean inputWithOnlyNumbers = validator.validateInputDimensionsAreNumbers(numericalInput);
            Assert.assertTrue(inputWithOnlyNumbers);
        }
    }

    public static class ValidateMineFieldDimensionsTest {

        @Test
        public void shouldReturnFalse_WhenGiven101RowsAnd101Columns() {
            int rows = 101;
            int columns = 101;
            boolean dimensionMoreThan100 = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertFalse(dimensionMoreThan100);
        }

        @Test
        public void shouldReturnTrue_WhenGiven100RowsAnd100Columns() {
            int rows = 100;
            int columns = 100;
            boolean dimensionLessThan100 = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertTrue(dimensionLessThan100);
        }

        @Test
        public void shouldReturnFalse_WhenGiven1NegativeNumber() {
            int rows = -1;
            int columns = 2;
            boolean oneNegativeDimension = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertFalse(oneNegativeDimension);
        }

        @Test
        public void shouldReturnTrue_WhenGiven0RowAnd0Column() {
            int rows = 0;
            int columns = 0;
            boolean zeroDimension = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertTrue(zeroDimension);
        }

        @Test
        public void shouldReturnFalse_WhenGiven2NegativeNumbers() {
            int rows = -1;
            int columns = -1;
            boolean twoNegativeDimensions = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertFalse(twoNegativeDimensions);
        }

        @Test
        public void shouldReturnTrue_WhenGiven3RowsAnd5Columns() {
            int rows = 3;
            int columns = 5;
            boolean dimensionsNotNegativeButSmallerThan100 = validator.validateMineFieldDimensions(rows, columns);
            Assert.assertTrue(dimensionsNotNegativeButSmallerThan100);
        }
    }

    public static class ValidateRowContentTest {

        @Test
        public void shouldReturnFalse_WhenInputContainsNoAsterisksAndNoDots() {
            String inputWithNoAsteriskOrDot = "hello world 123";
            boolean rowWithoutAsteriskOrDot = validator.validateRowContent(inputWithNoAsteriskOrDot);
            Assert.assertFalse(rowWithoutAsteriskOrDot);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsCharactersOtherThanAsteriskAndDot() {
            String inputWithMultipleCharacters = "hello*world.123()!@#$%^";
            boolean rowWithMultipleKindsOfCharacters = validator.validateRowContent(inputWithMultipleCharacters);
            Assert.assertFalse(rowWithMultipleKindsOfCharacters);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasTrailingWhiteSpaces() {
            String trailingWhiteSpaceString = "  *....   ";
            boolean rowWithTrailingSpaces = validator.validateRowContent(trailingWhiteSpaceString);
            Assert.assertFalse(rowWithTrailingSpaces);
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

        @Test
        public void shouldReturnTrue_WhenInputContainsBothAsterisksAndDots() {
            String inputWithBothAsteriskAndDot = "*...*";
            boolean rowWithAsteriskAndDot = validator.validateRowContent(inputWithBothAsteriskAndDot);
            Assert.assertTrue(rowWithAsteriskAndDot);
        }

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
    }

    public static class ValidateLengthOfRowInput {

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsGivenDimension() {
            String rowWithSixCharacters = "*.*.*.";
            int givenRowDimension = 4;
            boolean rowLengthTooLong = validator.validateLengthOfRowInput(rowWithSixCharacters, givenRowDimension);
            Assert.assertFalse(rowLengthTooLong);
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsGivenDimension() {
            String rowWithTwoCharacters = "..";
            int givenRowDimension = 4;
            boolean rowLengthTooShort = validator.validateLengthOfRowInput(rowWithTwoCharacters, givenRowDimension);
            Assert.assertFalse(rowLengthTooShort);
        }

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            int givenRowDimension = 4;
            boolean rowLengthMatchesDimension = validator.validateLengthOfRowInput(rowWithFourCharacters, givenRowDimension);
            Assert.assertTrue(rowLengthMatchesDimension);
        }
    }

    public static class ValidateEndOfInputTest {

        @Test
        public void shouldReturnTrue_WhenInputIsAStringOf00() {
            String doubleZeros = "00";
            boolean inputWithDoubleZeros = validator.validateEndOfInput(doubleZeros);
            Assert.assertTrue(inputWithDoubleZeros);
        }

        @Test
        public void shouldReturnFalse_WhenInputHasMoreThan2Zeros() {
            String multipleZeros = "0000 ";
            boolean inputWithMoreThanTwoZeros = validator.validateEndOfInput(multipleZeros);
            Assert.assertFalse(inputWithMoreThanTwoZeros);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String twoZerosWithTrailingSpaces = "   00    ";
            boolean inputWithTrailingSpaces = validator.validateEndOfInput(twoZerosWithTrailingSpaces);
            Assert.assertFalse(inputWithTrailingSpaces);
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsMoreThan2ZeroChracters() {
            String twoZerosWithExtraCharacters = "hello00000123...";
            boolean inputWithMultipleKindsOfCharacters = validator.validateEndOfInput(twoZerosWithExtraCharacters);
            Assert.assertFalse(inputWithMultipleKindsOfCharacters);
        }
    }
}
