package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.service.input.UserInputValidator;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UserInputValidatorTest {

    private String validPattern;
    private String userInput;
    private Boolean expectedResult;

    @Parameterized.Parameters
    public static Collection userInputsAndValidPatterns() {
        return Arrays.asList(new Object[][] {
                {"100 90", Constants.FIELD_DIMENSION_PATTERN, true},
//                {"-1 9", Constants.FIELD_DIMENSION_PATTERN, true},
//                {"-1 -1", Constants.FIELD_DIMENSION_PATTERN, true},
//                {"9 -1", Constants.FIELD_DIMENSION_PATTERN, true},
//                {"500 90", Constants.FIELD_DIMENSION_PATTERN, true},
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

    public UserInputValidatorTest(String userInput, String validPattern, Boolean expectedResult) {
        this.userInput = userInput;
        this.validPattern = validPattern;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testValidateStringInputWithRequiredPattern() {
        Assert.assertEquals(expectedResult, UserInputValidator.validateStringInputWithRequiredPattern(userInput, validPattern));
    }
//
//    public static class TestValidateStringInputWithRequiredPattern_GivenFieldDimensionPattern {
//
//        private String validFieldDimensionPattern = Constants.FIELD_DIMENSION_PATTERN;
//
//        @Test
//        public void shouldReturnTrue_WhenInputContains2NumbersSeparatedBy1WhiteSpace() {
//            String spacesBetweenNumbers = "100 90";
//            boolean inputContainsSpacesBetweenNumbers =
//                    UserInputValidator.validateStringInputWithRequiredPattern(spacesBetweenNumbers, validFieldDimensionPattern);
//            Assert.assertTrue(inputContainsSpacesBetweenNumbers);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsANumberFollowedBy1WhiteSpace() {
//            String numberFollowedBySpace = "100 ";
//            boolean inputContains1NumberAnd1Space =
//                    UserInputValidator.validateStringInputWithRequiredPattern(numberFollowedBySpace, validFieldDimensionPattern);
//            Assert.assertFalse(inputContains1NumberAnd1Space);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContains1WhiteSpaceFollowedByANumber() {
//            String spaceFollowedByNumber = " 1";
//            boolean inputContains1SpaceAnd1Number =
//                    UserInputValidator.validateStringInputWithRequiredPattern(spaceFollowedByNumber, validFieldDimensionPattern);
//            Assert.assertFalse(inputContains1SpaceAnd1Number);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsMoreThan1WhiteSpaceBetween2Numbers() {
//            String numericalInput = "12       34";
//            boolean inputWithOnlyNumbers =
//                    UserInputValidator.validateStringInputWithRequiredPattern(numericalInput, validFieldDimensionPattern);
//            Assert.assertFalse(inputWithOnlyNumbers);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsNonNumericalCharacters() {
//            String inputWithNonNumericalCharacters = "hello12()-+ world34,./";
//            boolean inputWithWordCharacters =
//                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithNonNumericalCharacters, validFieldDimensionPattern);
//            Assert.assertFalse(inputWithWordCharacters);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsTrailingSpaces() {
//            String numbersAndTrailingSpaces = "34  90  ";
//            boolean inputWithTrailingSpaces =
//                    UserInputValidator.validateStringInputWithRequiredPattern(numbersAndTrailingSpaces, validFieldDimensionPattern);
//            Assert.assertFalse(inputWithTrailingSpaces);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsLeadingSpaces() {
//            String leadingSpacesAndNumbers = "     1 2";
//            boolean inputWithLeadingSpaces =
//                    UserInputValidator.validateStringInputWithRequiredPattern(leadingSpacesAndNumbers, validFieldDimensionPattern);
//            Assert.assertFalse(inputWithLeadingSpaces);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputIsEmpty() {
//            String emptyString = new String();
//            boolean emptyInput =
//                    UserInputValidator.validateStringInputWithRequiredPattern(emptyString, validFieldDimensionPattern);
//            Assert.assertFalse(emptyInput);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputIsBlank() {
//            String blankString = "      ";
//            boolean blankInput =
//                    UserInputValidator.validateStringInputWithRequiredPattern(blankString, validFieldDimensionPattern);
//            Assert.assertFalse(blankInput);
//        }
//    }
//
//    public static class TestValidateStringInputWithRequiredPattern_GivenRowPattern {
//
//        private String validRowPattern = Constants.ROW_PATTERN;
//
//        @Test
//        public void shouldReturnTrue_WhenInputOnlyContainsAsterisks() {
//            String allAsterisks = "*******";
//            boolean rowWithAsterisks = UserInputValidator.validateStringInputWithRequiredPattern(allAsterisks, validRowPattern);
//            Assert.assertTrue(rowWithAsterisks);
//        }
//
//        @Test
//        public void shouldReturnTrue_WhenInputOnlyContainsDots() {
//            String allDots = "......";
//            boolean rowWithDots = UserInputValidator.validateStringInputWithRequiredPattern(allDots, validRowPattern);
//            Assert.assertTrue(rowWithDots);
//        }
//
//        @Test
//        public void shouldReturnTrue_WhenInputContainsOnlyAsterisksAndDots() {
//            String inputWithBothAsteriskAndDot = "*...*";
//            boolean rowWithAsteriskAndDot =
//                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithBothAsteriskAndDot, validRowPattern);
//            Assert.assertTrue(rowWithAsteriskAndDot);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsAsteriskAndOtherCharacters() {
//            String asteriskAndOtherKindsOfCharacters = "(****)123hello";
//            boolean inputWithDifferentKindsOfCharacters =
//                    UserInputValidator.validateStringInputWithRequiredPattern(asteriskAndOtherKindsOfCharacters, validRowPattern);
//            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsDotsAndOtherCharacters() {
//            String dotsAndOtherKindsOfCharacters = "....$%^&world123";
//            boolean inputWithDifferentKindsOfCharacters =
//                    UserInputValidator.validateStringInputWithRequiredPattern(dotsAndOtherKindsOfCharacters, validRowPattern);
//            Assert.assertFalse(inputWithDifferentKindsOfCharacters);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsCharactersAsteriskAndDotAndOtherCharacters() {
//            String inputWithMultipleCharacters = "hello***world....123()!@#$%^";
//            boolean rowWithMultipleKindsOfCharacters =
//                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithMultipleCharacters, validRowPattern);
//            Assert.assertFalse(rowWithMultipleKindsOfCharacters);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputContainsNoAsterisksAndNoDots() {
//            String inputWithNoAsteriskOrDot = "hello world 123";
//            boolean rowWithoutAsteriskOrDot =
//                    UserInputValidator.validateStringInputWithRequiredPattern(inputWithNoAsteriskOrDot, validRowPattern);
//            Assert.assertFalse(rowWithoutAsteriskOrDot);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputHasTrailingSpaces() {
//            String trailingWhiteSpaceString = "*....   ";
//            boolean rowWithTrailingSpaces =
//                    UserInputValidator.validateStringInputWithRequiredPattern(trailingWhiteSpaceString, validRowPattern);
//            Assert.assertFalse(rowWithTrailingSpaces);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputHasLeadingSpaces() {
//            String leadingWhiteSpaceString = "   *....";
//            boolean rowWithLeadingSpaces =
//                    UserInputValidator.validateStringInputWithRequiredPattern(leadingWhiteSpaceString, validRowPattern);
//            Assert.assertFalse(rowWithLeadingSpaces);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputStringIsBlank() {
//            String blankString = "       ";
//            boolean blankRow = UserInputValidator.validateStringInputWithRequiredPattern(blankString, validRowPattern);
//            Assert.assertFalse(blankRow);
//        }
//
//        @Test
//        public void shouldReturnFalse_WhenInputStringIsEmpty() {
//            String emptyString = new String();
//            boolean emptyRow = UserInputValidator.validateStringInputWithRequiredPattern(emptyString, validRowPattern);
//            Assert.assertFalse(emptyRow);
//        }
//    }


}
