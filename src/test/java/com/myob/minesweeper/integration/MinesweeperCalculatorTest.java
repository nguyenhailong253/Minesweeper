package com.myob.minesweeper.integration;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinesweeperCalculatorTest {

    private static int firstFieldNumRows = 3;
    private static int firstFieldNumColumns = 5;
    private static int secondFieldNumRows = 4;
    private static int secondFieldNumColumns = 4;
    private static MineField inputFirstField;
    private static MineField inputSecondField;
    private static MineField resultFirstField;
    private static MineField resultSecondField;
    private static List<MineField> inputListOfFields;
    private static List<MineField> expectedResultListOfFields;

    private static boolean validateEqualFields(MineField baseField, MineField compareField) {
        return baseField.getFieldState() == compareField.getFieldState()
                && baseField.getRowDimension() == compareField.getRowDimension()
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

    private static void constructDefaultFields() {
        inputFirstField = MineFieldService.constructNewField(firstFieldNumRows, firstFieldNumColumns);
        inputSecondField = MineFieldService.constructNewField(secondFieldNumRows, secondFieldNumColumns);
        resultFirstField = MineFieldService.constructNewField(firstFieldNumRows, firstFieldNumColumns);
        resultSecondField = MineFieldService.constructNewField(secondFieldNumRows, secondFieldNumColumns);
    }

    private static void initialiseDefaultListOfFields() {
        inputListOfFields = new ArrayList<>();
        expectedResultListOfFields = new ArrayList<>();

        inputListOfFields.add(inputFirstField);
        inputListOfFields.add(inputSecondField);

        expectedResultListOfFields.add(resultFirstField);
        expectedResultListOfFields.add(resultSecondField);
    }

    public static class TestCalculateAllFields {

        @Before
        public void initialiseDefaultValues() {
            constructDefaultFields();
            initialiseDefaultListOfFields();
        }

        @Test
        public void shouldReturnListOfCalculatedField_WhenReceiveListOfValidInputFields() {
            String[][] inputFirstFieldValue = new String[][]{
                    {"*", "*", ".", ".", "."},
                    {".", ".", ".", ".", ".",},
                    {".", "*", ".", ".", ".",},};
            String[][] inputSecondFieldValue = new String[][]{
                    {"*", ".", ".", "."},
                    {".", ".", ".", "."},
                    {".", "*", ".", "."},
                    {".", ".", ".", "."},};
            MineFieldService.updateFieldState(inputFirstField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldState(inputSecondField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"*", "*", "1", "0", "0"},
                    {"3", "3", "2", "0", "0",},
                    {"1", "*", "1", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"*", "1", "0", "0"},
                    {"2", "2", "1", "0"},
                    {"1", "*", "1", "0"},
                    {"1", "1", "1", "0"},};
            MineFieldService.updateFieldState(resultFirstField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldState(resultSecondField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
            MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldReturnEmptyList_WhenInputListIsEmpty() {
            List<MineField> inputEmptyList = new ArrayList<>();
            List<MineField> expectedEmptyList = new ArrayList<>();

            List<MineField> actualResultList = MinesweeperCalculator.calculateAllFields(inputEmptyList);

            Assert.assertEquals(expectedEmptyList, actualResultList);
        }

        @Test
        public void shouldReturnSameList_WhenEachFieldIsFullOfMines() {
            String[][] inputFirstFieldValue = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*",},
                    {"*", "*", "*", "*", "*",},};
            String[][] inputSecondFieldValue = new String[][]{
                    {"*", "*", "*", "*"},
                    {"*", "*", "*", "*"},
                    {"*", "*", "*", "*"},
                    {"*", "*", "*", "*"},};
            MineFieldService.updateFieldState(inputFirstField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldState(inputSecondField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldState(resultFirstField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldState(resultSecondField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);
            MineFieldService.updateFieldValues(resultFirstField, inputFirstFieldValue);
            MineFieldService.updateFieldValues(resultSecondField, inputSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldReturnListOfFieldsWithAllZeros_WhenEachFieldHasNoMines() {
            String[][] inputFirstFieldValue = new String[][]{
                    {".", ".", ".", ".", "."},
                    {".", ".", ".", ".", ".",},
                    {".", ".", ".", ".", ".",},};
            String[][] inputSecondFieldValue = new String[][]{
                    {".", ".", ".", "."},
                    {".", ".", ".", "."},
                    {".", ".", ".", "."},
                    {".", ".", ".", "."},};
            MineFieldService.updateFieldState(inputFirstField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldState(inputSecondField, MineFieldState.INITIALISED);
            MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0",},
                    {"0", "0", "0", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},};
            MineFieldService.updateFieldState(resultFirstField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldState(resultSecondField, MineFieldState.CALCULATED);
            MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
            MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }
    }
}
