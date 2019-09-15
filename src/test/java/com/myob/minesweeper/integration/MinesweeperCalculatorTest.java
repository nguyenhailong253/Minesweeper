package com.myob.minesweeper.integration;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
import com.myob.minesweeper.unit.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    private static void initialiseDefaultFields() {
        inputFirstField = MineFieldService.initialiseNewField(firstFieldNumRows, firstFieldNumColumns);
        inputSecondField = MineFieldService.initialiseNewField(secondFieldNumRows, secondFieldNumColumns);
        resultFirstField = MineFieldService.initialiseNewField(firstFieldNumRows, firstFieldNumColumns);
        resultSecondField = MineFieldService.initialiseNewField(secondFieldNumRows, secondFieldNumColumns);
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
            initialiseDefaultFields();
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
            inputFirstField = MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            inputSecondField = MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"*", "*", "1", "0", "0"},
                    {"3", "3", "2", "0", "0",},
                    {"1", "*", "1", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"*", "1", "0", "0"},
                    {"2", "2", "1", "0"},
                    {"1", "*", "1", "0"},
                    {"1", "1", "1", "0"},};
            resultFirstField = MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
            resultSecondField = MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
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
            inputFirstField = MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            inputSecondField = MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);
            resultFirstField = MineFieldService.updateFieldValues(resultFirstField, inputFirstFieldValue);
            resultSecondField = MineFieldService.updateFieldValues(resultSecondField, inputSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
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
            inputFirstField = MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
            inputSecondField = MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0",},
                    {"0", "0", "0", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},};
            resultFirstField = MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
            resultSecondField = MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.calculateAllFields(inputListOfFields);

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }
    }
}
