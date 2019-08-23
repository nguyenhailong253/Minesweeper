import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserInputValidatorTest {

    static UserInputValidator validator = new UserInputValidator();

    public static class ValidateInputDimensionsAreNumbersTest {

        @Test
        public void shouldReturnFalse_WhenInputContainsNonNumericalCharacters() {
            String inputWithNonNumericalCharacters = "hello1234";
            Assert.assertFalse(validator.validateInputDimensionsAreNumbers(inputWithNonNumericalCharacters));
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String inputDimensionsWithTrailingSpaces = "    34  ";
            Assert.assertFalse(validator.validateInputDimensionsAreNumbers(inputDimensionsWithTrailingSpaces));
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsSpacesBetweenNumbers() {
            String spacesBetweenNumbers = "100 90";
            Assert.assertFalse(validator.validateInputDimensionsAreNumbers(spacesBetweenNumbers));
        }

        @Test
        public void shouldReturnTrue_WhenInputContainsOnlyNumbers() {
            String numericalInput = "1234";
            Assert.assertTrue(validator.validateInputDimensionsAreNumbers(numericalInput));
        }
    }

    public static class ValidateMineFieldDimensionsTest {

        @Test
        public void shouldReturnFalse_WhenGiven101RowsAnd101Columns() {
            int rows = 101;
            int columns = 101;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrue_WhenGiven100RowsAnd100Columns() {
            int rows = 100;
            int columns = 100;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnFalse_WhenGiven1NegativeNumber() {
            int rows = -1;
            int columns = 2;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrue_WhenGiven0RowAnd0Column() {
            int rows = 0;
            int columns = 0;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnFalse_WhenGiven2NegativeNumbers() {
            int rows = -1;
            int columns = -1;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrue_WhenGiven3RowsAnd5Columns() {
            int rows = 3;
            int columns = 5;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }
    }

    public static class ValidateRowContentTest {

        @Test
        public void shouldReturnFalse_WhenInputContainsNoAsterisksAndNoDots() {
            String inputWithNoAsteriskOrDot = "hello world 123";
            Assert.assertFalse(validator.validateRowContent(inputWithNoAsteriskOrDot));
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsCharactersOtherThanAsteriskAndDot() {
            String inputWithMultipleCharacters = "hello*world.123()!@#$%^";
            Assert.assertFalse(validator.validateRowContent(inputWithMultipleCharacters));
        }

        @Test
        public void shouldReturnFalse_WhenInputHasTrailingWhiteSpaces() {
            String trailingWhiteSpaceString = "  *....   ";
            Assert.assertFalse(validator.validateRowContent(trailingWhiteSpaceString));
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsBlank() {
            String blankString = "       ";
            Assert.assertFalse(validator.validateRowContent(blankString));
        }

        @Test
        public void shouldReturnFalse_WhenInputStringIsEmpty() {
            String emptyString = "";
            Assert.assertFalse(validator.validateRowContent(emptyString));
        }

        @Test
        public void shouldReturnTrue_WhenInputContainsBothAsterisksAndDots() {
            String inputWithBothAsteriskAndDot = "*...*";
            Assert.assertTrue(validator.validateRowContent(inputWithBothAsteriskAndDot));
        }

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsAsterisks() {
            String allAsterisks = "*******";
            Assert.assertTrue(validator.validateRowContent(allAsterisks));
        }

        @Test
        public void shouldReturnTrue_WhenInputOnlyContainsDots() {
            String allDots = "......";
            Assert.assertTrue(validator.validateRowContent(allDots));
        }
    }

    public static class ValidateLengthOfRowInput {

        @Test
        public void shouldReturnFalse_WhenRowHasMoreCharactersThanItsGivenDimension() {
            String rowWithSixCharacters = "*.*.*.";
            int givenRowDimension = 4;
            Assert.assertFalse(validator.validateLengthOfRowInput(rowWithSixCharacters, givenRowDimension));
        }

        @Test
        public void shouldReturnFalse_WhenRowHasFewerCharactersThanItsGivenDimension() {
            String rowWithTwoCharacters = "..";
            int givenRowDimension = 4;
            Assert.assertFalse(validator.validateLengthOfRowInput(rowWithTwoCharacters, givenRowDimension));
        }

        @Test
        public void shouldReturnTrue_WhenRowHasSameNumberOfCharactersAsItsDimension() {
            String rowWithFourCharacters = "****";
            int givenRowDimension = 4;
            Assert.assertTrue(validator.validateLengthOfRowInput(rowWithFourCharacters, givenRowDimension));
        }
    }

    public static class ValidateEndOfInputTest {

        @Test
        public void shouldReturnTrue_WhenInputIsAStringOf00() {
            String doubleZeros = "00";
            Assert.assertTrue(validator.validateEndOfInput(doubleZeros));
        }

        @Test
        public void shouldReturnFalse_WhenInputHasMoreThan2Zeros() {
            String multipleZeros = "0000 ";
            Assert.assertFalse(validator.validateEndOfInput(multipleZeros));
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
            String twoZerosWithTrailingSpaces = "   00    ";
            Assert.assertFalse(validator.validateEndOfInput(twoZerosWithTrailingSpaces));
        }

        @Test
        public void shouldReturnFalse_WhenInputContainsMoreThan2ZeroChracters() {
            String twoZerosWithExtraCharacters = "hello00000123";
            Assert.assertFalse(validator.validateEndOfInput(twoZerosWithExtraCharacters));
        }
    }
}
