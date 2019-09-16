package com.myob.minesweeper.integration;

import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputServiceTest {

    private static IIOService mockConsoleIOService = mock(ConsoleIOService.class);
    private static IInputService inputService = new InputService(mockConsoleIOService);
    private static int defaultNumRows = 2;
    private static int defaultNumColumns = 2;
    private static String[][] defaultFieldValues = new String[][]{{"*", "."}, {".", "."}};
    private static MineField defaultField;
    private static List<MineField> expectedListOfFields;

    // TODO: 16/9/19 Duplicated methods (also in calculator test)

    private static boolean validateEqualFields(MineField baseField, MineField compareField) {
        return baseField.getRowDimension() == compareField.getRowDimension()
                && baseField.getColumnDimension() == compareField.getColumnDimension()
                && Arrays.deepEquals(baseField.getFieldValues(), compareField.getFieldValues());
    }

    private static boolean compareSizeOfTwoFieldLists(List<MineField> baseList, List<MineField> compareList) {
        return baseList.size() == compareList.size();
    }

    private static boolean validateEqualListsOfFields(List<MineField> baseList, List<MineField> compareList) {

        if (!compareSizeOfTwoFieldLists(baseList, compareList)) {
            return false;
        }

        for (int fieldIndex = 0; fieldIndex < baseList.size(); fieldIndex++) {
            MineField baseField = baseList.get(fieldIndex);
            MineField compareField = compareList.get(fieldIndex);
            if (!validateEqualFields(baseField, compareField)) {
                return false;
            }
        }
        return true;
    }

    private static void initialiseFieldAndList() {
        defaultField = MineFieldService.initialiseNewField(defaultNumRows, defaultNumColumns);
        MineFieldService.updateFieldValues(defaultField, defaultFieldValues);
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

            boolean equalLists = validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldReturnEmptyList_GivenOnlyEndOfInputPattern() {
            List<MineField> expectedEmptyList = new ArrayList<>();

            when(mockConsoleIOService.readUserInput()).thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualList = inputService.getListOfNewMineFields();

            boolean equalLists = validateEqualListsOfFields(expectedEmptyList, actualList);
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

            boolean equalLists = validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
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

            boolean equalLists = validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldIgnoreInvalidRowContent() {
            when(mockConsoleIOService.readUserInput())
                    .thenReturn("2 2")
                    .thenReturn("this-is_invalid@row(content)")
                    .thenReturn("...88888")
                    .thenReturn("11")
                    .thenReturn("*.")
                    .thenReturn("..")
                    .thenReturn(Constants.END_OF_INPUT_STRING);
            List<MineField> actualListOfFields = inputService.getListOfNewMineFields();

            boolean equalLists = validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
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

            boolean equalLists = validateEqualListsOfFields(expectedListOfFields, actualListOfFields);
            Assert.assertTrue(equalLists);
        }
    }
}