import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserInputConverterTest {

    static UserInputConverter converter = new UserInputConverter();

    public static class ConvertToNumericalDimensions {

        @Test
        public void shouldReturnListOfNumbers_WhenGivenStringArrayOfNumbers() {
            String[] stringWithTwoNumbers = new String[] {"100", "99"};
            ArrayList<Integer> actualOutput = converter.convertToNumericalDimensions(stringWithTwoNumbers);
            ArrayList<Integer> expectedOutput = new ArrayList<>(Arrays.asList(100, 99));
            Assert.assertEquals(actualOutput, expectedOutput);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowExceptionWhenGivenNonNumericalInput() {
            String[] inputWithNonNumericalCharacters = new String[] {"hello,./()", "123"};
            converter.convertToNumericalDimensions(inputWithNonNumericalCharacters);
        }

        @Test
        public void shouldReturnEmptyList_WhenGivenEmptyStringArray() {
            String[] emptyString = new String[]{};
            ArrayList<Integer> actualOutput = converter.convertToNumericalDimensions(emptyString);
            ArrayList<Integer> expectedEmptyOuput = new ArrayList<>();
            Assert.assertEquals(actualOutput, expectedEmptyOuput);
        }
    }
}
