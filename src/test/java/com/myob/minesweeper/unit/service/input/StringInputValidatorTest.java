package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.service.input.StringInputValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class StringInputValidatorTest {

    // TODO: 15/9/19 Does not look like TDD?

    @RunWith(Parameterized.class)
    public static class TestIsStringMatchedPattern {
        private String validPattern;
        private String userInput;
        private boolean expectedResult;

        public TestIsStringMatchedPattern(String userInput, String validPattern, boolean expectedResult) {
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
        public void testIsStringMatchedPattern() {
            Assert.assertEquals(expectedResult, StringInputValidator.isStringMatchedPattern(userInput, validPattern));
        }
    }
}
