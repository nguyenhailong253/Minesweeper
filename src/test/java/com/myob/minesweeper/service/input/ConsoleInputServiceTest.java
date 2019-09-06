package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/* INTEGRATION TEST */
public class ConsoleInputServiceTest {

    private static IUserInputConverter converter = new ConsoleUserInputConverter();
    private static IUserInputValidator validator = new ConsoleUserInputValidator();
    private static IUserInputParser mockParser = mock(ConsoleUserInputParser.class);
    private static IInputService inputService = new ConsoleInputService(mockParser, validator, converter);
    private static int[] default2By2Dimension = new int[]{2,2};
    private static MineField default2By2Field = new MineField(default2By2Dimension);

    public static class TestGetValidInput {

        @BeforeClass
        public static void initialise() {
            String[][] defaultFieldValue = new String[][]{{"*", "."}, {".", "."}};
            default2By2Field.setFieldValue(defaultFieldValue);
        }

        @Test
        public void shouldReturnEmptyList_GivenOnlyEndOfInputPattern() {
            List<MineField> expectedEmptyList = new ArrayList<>();

            when(mockParser.readUserInput()).thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualList = inputService.getValidInput();

            Assert.assertEquals(expectedEmptyList, actualList);
        }

        @Test
        public void shouldReturnListOf1ValidField_GivenValidDimensionAndRowContents() {
            List<MineField> expectedListOfFields = new ArrayList<>();
            expectedListOfFields.add(default2By2Field);

            when(mockParser.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualListOfFields = inputService.getValidInput();

            Assert.assertEquals(expectedListOfFields, actualListOfFields);
        }

        @Test
        public void shouldIgnoreInvalidDimension() {
            List<MineField> expectedListOfFields = new ArrayList<>();
            expectedListOfFields.add(default2By2Field);

            when(mockParser.readUserInput())
                    .thenReturn("this-is_1nvalid_dimension@!&*(")
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualListOfFields = inputService.getValidInput();

            Assert.assertEquals(expectedListOfFields, actualListOfFields);
        }

        @Test
        public void shouldIgnoreOutOfRangeDimension() {
            List<MineField> expectedListOfFields = new ArrayList<>();
            expectedListOfFields.add(default2By2Field);

            when(mockParser.readUserInput())
                    .thenReturn("1000 30000")
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualListOfFields = inputService.getValidInput();

            Assert.assertEquals(expectedListOfFields, actualListOfFields);
        }

        @Test
        public void shouldIgnoreInvalidRowContent() {
            List<MineField> expectedListOfFields = new ArrayList<>();
            expectedListOfFields.add(default2By2Field);

            when(mockParser.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("this-is_invalid@row(content)")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualListOfFields = inputService.getValidInput();

            Assert.assertEquals(expectedListOfFields, actualListOfFields);
        }

        @Test
        public void shouldIgnoreExtraRowContent() {
            List<MineField> expectedListOfFields = new ArrayList<>();
            expectedListOfFields.add(default2By2Field);

            when(mockParser.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MineField> actualListOfFields = inputService.getValidInput();

            Assert.assertEquals(expectedListOfFields, actualListOfFields);
        }
    }
}