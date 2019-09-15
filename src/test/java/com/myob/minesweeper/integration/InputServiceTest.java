package com.myob.minesweeper.integration;

import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.unit.TestHelper;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputServiceTest {

    // TODO: 15/9/19 remove TestHelper

    private static IIOService mockConsoleIOService = mock(ConsoleIOService.class);
    private static IInputService inputService = new InputService(mockConsoleIOService);
    private static int defaultNumRows = 2;
    private static int defaultNumColumns = 2;
    private static String[][] defaultFieldValues = new String[][]{{"*", "."}, {".", "."}};
    private static MineField defaultField;
    private static List<MineField> expectedListOfFields;

    private static void initialiseFieldAndList() {
        defaultField = MineFieldService.initialiseNewField(defaultNumRows, defaultNumColumns);
        defaultField = MineFieldService.updateFieldValues(defaultField, defaultFieldValues);
        expectedListOfFields = new ArrayList<>();
        expectedListOfFields.add(defaultField);
    }

    public static class TestGetListOfNewMineFields {

        @Before
        public void initialiseExpectedResults() {
            initialiseFieldAndList();
        }

        @Test
        public void shouldReturnListOf1ValidField_GivenValidDimensionAndRowContents() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldReturnEmptyList_GivenOnlyEndOfInputPattern() {
            List<MineField> expectedEmptyList = new ArrayList<>();

            when(mockConsoleIOService.readUserInput()).thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualList = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedEmptyList, actualList);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldIgnoreInvalidDimension() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("this-is_1nvalid_dimension@!&*(")
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldIgnoreOutOfRangeDimension() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("1000 30000")
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldIgnoreInvalidRowContent() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("this-is_invalid@row(content)")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldIgnoreExtraRowContent() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }
    }
}