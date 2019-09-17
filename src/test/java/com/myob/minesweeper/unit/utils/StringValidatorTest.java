package com.myob.minesweeper.unit.utils;

import com.myob.minesweeper.utils.Constants;
import com.myob.minesweeper.utils.StringValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StringValidatorTest {
    private String validPattern;
    private String userInput;
    private boolean expectedResult;

    public StringValidatorTest(String userInput, String validPattern, boolean expectedResult) {
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

                {"*******", Constants.INPUT_ROW_PATTERN, true},
                {"......", Constants.INPUT_ROW_PATTERN, true},
                {"*...*", Constants.INPUT_ROW_PATTERN, true},
                {"(****)123hello", Constants.INPUT_ROW_PATTERN, false},
                {"....$%^&world123", Constants.INPUT_ROW_PATTERN, false},
                {"hello***world....123()!@#$%^", Constants.INPUT_ROW_PATTERN, false},
                {"hello world 123", Constants.INPUT_ROW_PATTERN, false},
                {"*....   ", Constants.INPUT_ROW_PATTERN, false},
                {"   *....", Constants.INPUT_ROW_PATTERN, false},
        });
    }

    @Test
    public void testIsStringMatchedPattern() {
        Assert.assertEquals(expectedResult, StringValidator.isStringMatchedPattern(userInput, validPattern));
    }
}
