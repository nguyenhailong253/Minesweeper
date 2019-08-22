import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserInputValidatorTest {

    static UserInputValidator validator = new UserInputValidator();

    public static class ValidateMineFieldDimensionsTest {

        @Test
        public void shouldReturnFalseWhenGiven101RowsAnd101Columns() {
            int rows = 101;
            int columns = 101;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrueWhenGiven100RowsAnd100Columns() {
            int rows = 100;
            int columns = 100;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnFalseWhenGiven1NegativeNumber() {
            int rows = -1;
            int columns = 2;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrueWhenGiven0RowAnd0Column() {
            int rows = 0;
            int columns = 0;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnFalseWhenGiven2NegativeNumbers() {
            int rows = -1;
            int columns = -1;
            Assert.assertFalse(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrueWhenGiven3RowsAnd5Columns() {
            int rows = 3;
            int columns = 5;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }

        @Test
        public void shouldReturnTrueWhenGiven30RowsAnd40Columns() {
            int rows = 30;
            int columns = 40;
            Assert.assertTrue(validator.validateMineFieldDimensions(rows, columns));
        }
    }

    public static class ValidateRowContentTest {

        @Test
        public void shouldReturnFalseWhenInputContainsNoAsterisksAndNoDots() {
            String invalidInput = "hello world 123";
            Assert.assertFalse(validator.validateRowContent(invalidInput));
        }

        @Test
        public void shouldReturnFalseWhenInputContainsCharactersOtherThanAsteriskAndDot() {
            String invalidInput = "hello*world.123()!@#$%^";
            Assert.assertFalse(validator.validateRowContent(invalidInput));
        }

        @Test
        public void shouldReturnFalseWhenInputHasTrailingWhiteSpaces() {
            String trailingWhiteSpaceString = "  *....   ";
            Assert.assertFalse(validator.validateRowContent(trailingWhiteSpaceString));
        }

        @Test
        public void shouldReturnFalseWhenInputStringIsBlank() {
            String blankString = "       ";
            Assert.assertFalse(validator.validateRowContent(blankString));
        }

        @Test
        public void shouldReturnFalseWhenInputStringIsEmpty() {
            String emptyString = "";
            Assert.assertFalse(validator.validateRowContent(emptyString));
        }

        @Test
        public void shouldReturnTrueWhenInputContainsBothAsterisksAndDots() {
            String validInput = "*...*";
            Assert.assertTrue(validator.validateRowContent(validInput));
        }

        @Test
        public void shouldReturnTrueWhenInputOnlyContainsAsterisks() {
            String allAsterisks = "*******";
            Assert.assertTrue(validator.validateRowContent(allAsterisks));
        }

        @Test
        public void shouldReturnTrueWhenInputOnlyContainsDots() {
            String allDots = "......";
            Assert.assertTrue(validator.validateRowContent(allDots));
        }
    }
}
