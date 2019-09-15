package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.service.input.UserInputValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class UserInputValidatorTest {

    // TODO: 15/9/19 Does not look like TDD?

    @RunWith(Parameterized.class)
    public static class TestValidateStringInputWithRequiredPattern {
        private String validPattern;
        private String userInput;
        private boolean expectedResult;

        public TestValidateStringInputWithRequiredPattern(String userInput, String validPattern, boolean expectedResult) {
            this.userInput = userInput;
            this.validPattern = validPattern;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static Collection userInputsAndValidPatterns() {
            return Arrays.asList(new Object[][] {
                    {"100 90", Constants.FIELD_DIMENSION_PATTERN, true},
                    {"100 ", Constants.FIELD_DIMENSION_PATTERN, false},
                    {" 1", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"12       34", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"hello12()-+ world34,./", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"34  90  ", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"     1 2", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"", Constants.FIELD_DIMENSION_PATTERN, false},
                    {"      ", Constants.FIELD_DIMENSION_PATTERN, false},

                    {"*******", Constants.ROW_PATTERN, true},
                    {"......", Constants.ROW_PATTERN, true},
                    {"*...*", Constants.ROW_PATTERN, true},
                    {"(****)123hello", Constants.ROW_PATTERN, false},
                    {"....$%^&world123", Constants.ROW_PATTERN, false},
                    {"hello***world....123()!@#$%^", Constants.ROW_PATTERN, false},
                    {"hello world 123", Constants.ROW_PATTERN, false},
                    {"*....   ", Constants.ROW_PATTERN, false},
                    {"   *....", Constants.ROW_PATTERN, false},
            });
        }

        @Test
        public void testValidateStringInputWithRequiredPattern() {
            Assert.assertEquals(expectedResult, UserInputValidator.validateStringInputWithRequiredPattern(userInput, validPattern));
        }
    }

    @RunWith(Parameterized.class)
    public static class TestValidateEndOfInputValues {
        private int[] endOfInputValues;
        private int[] userInput;
        private boolean expectedResult;

        public TestValidateEndOfInputValues(int[] userInput, int[] endOfInputValues, boolean expectedResult) {
            this.userInput = userInput;
            this.endOfInputValues = endOfInputValues;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static Collection userInputsAndValidPatterns() {
            return Arrays.asList(new Object[][] {
                    {new int[]{0,0}, Constants.END_OF_INPUT_VALUE_SET, true},
                    {new int[]{1,100}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{1,0}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{-1,-100}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{-1,0}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{-1,1}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{0,0,10}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{0}, Constants.END_OF_INPUT_VALUE_SET, false},
                    {new int[]{}, Constants.END_OF_INPUT_VALUE_SET, false},
            });
        }

        @Test
        public void testValidateEndOfInputValues() {
            Assert.assertEquals(expectedResult, UserInputValidator.validateEndOfInputValues(userInput, endOfInputValues));
        }
    }
}
