import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserInputConverterTest {

    static UserInputConverter converter = new UserInputConverter();

    public static class SplitStringToArrayOf2ElementsTest {

        @Test
        public void shouldReturnStringArrayOfSize2_WhenGivenStringOf2Numbers() {
            String stringWith2Numbers = new String("100 99");
            String[] actualArray = converter.splitStringToArrayOf2Elements(stringWith2Numbers);
            String[] expectedArraySize2 = new String[] {"100", "99"};
            Assert.assertEquals(actualArray, expectedArraySize2);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGivenStringOf3Numbers() {
            String stringWith3Numbers = new String("100 99 98");
            converter.splitStringToArrayOf2Elements(stringWith3Numbers);
        }

        @Test(expected = InvalidNumberOfDimensionsException.class)
        public void shouldThrowException_WhenGivenEmptyString() {
            String emptyString = new String();
            converter.splitStringToArrayOf2Elements(emptyString);
        }
    }

    public static class ConvertToNumericalDimensions {

        @Test
        public void shouldReturnArrayOf2Numbers_WhenGivenStringArrayOf2Numbers() {
            String[] stringArrayWith2Numbers = new String[] {"100", "99"};
            int[] actualOutput = converter.convertToNumericalDimensions(stringArrayWith2Numbers);
            int[] expectedOutputWith2Numbers = new int[]{100, 99};
            Assert.assertArrayEquals(actualOutput, expectedOutputWith2Numbers);
        }

        @Test(expected = StringToNumberConversionException.class)
        public void shouldThrowException_WhenGivenNonNumericalInput() {
            String[] inputWithNonNumericalCharacters = new String[] {"hello,./()", "123"};
            converter.convertToNumericalDimensions(inputWithNonNumericalCharacters);
        }

        @Test(expected = Exception.class)
        public void shouldThrowException_WhenGivenStringArrayOf3Numbers() {
            String[] stringArrayWith2Numbers = new String[] {"100", "99", "98"};
            converter.convertToNumericalDimensions(stringArrayWith2Numbers);
        }

        @Test
        public void shouldReturnEmptyArray_WhenGivenEmptyStringArray() {
            String[] emptyString = new String[]{};
            int[] actualOutput = converter.convertToNumericalDimensions(emptyString);
            int[] expectedEmptyOuput = new int[2];
            Assert.assertArrayEquals(actualOutput, expectedEmptyOuput);
        }
    }
}
