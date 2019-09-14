package com.myob.minesweeper.integration;

import com.myob.minesweeper.model.MineField;
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

    public static class TestProcessAllFields {

        @Before
        public void initialiseNewLists() {
            String[][] firstFieldValue = new String[firstFieldNumRows][firstFieldNumColumns];
            String[][] secondFieldValue = new String[secondFieldNumRows][secondFieldNumColumns];
            inputFirstField = new MineField(firstFieldNumRows, firstFieldNumColumns, firstFieldValue);
            inputSecondField = new MineField(secondFieldNumRows, secondFieldNumColumns, secondFieldValue);
            resultFirstField = new MineField(firstFieldNumRows, firstFieldNumColumns, firstFieldValue);
            resultSecondField = new MineField(secondFieldNumRows, secondFieldNumColumns, secondFieldValue);
            inputListOfFields = new ArrayList<>();
            expectedResultListOfFields = new ArrayList<>();

            inputListOfFields.add(inputFirstField);
            inputListOfFields.add(inputSecondField);

            expectedResultListOfFields.add(resultFirstField);
            expectedResultListOfFields.add(resultSecondField);
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
            inputFirstField.setFieldValue(inputFirstFieldValue);
            inputSecondField.setFieldValue(inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"*", "*", "1", "0", "0"},
                    {"3", "3", "2", "0", "0",},
                    {"1", "*", "1", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"*", "1", "0", "0"},
                    {"2", "2", "1", "0"},
                    {"1", "*", "1", "0"},
                    {"1", "1", "1", "0"},};
            resultFirstField.setFieldValue(resultFirstFieldValue);
            resultSecondField.setFieldValue(resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.processAllFields(inputListOfFields);

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }

        @Test
        public void shouldReturnEmptyList_WhenInputListIsEmpty() {
            List<MineField> inputEmptyList = new ArrayList<>();
            List<MineField> expectedEmptyList = new ArrayList<>();

            List<MineField> actualResultList = MinesweeperCalculator.processAllFields(inputEmptyList);

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
            inputFirstField.setFieldValue(inputFirstFieldValue);
            inputSecondField.setFieldValue(inputSecondFieldValue);
            resultFirstField.setFieldValue(inputFirstFieldValue);
            resultSecondField.setFieldValue(inputSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.processAllFields(inputListOfFields);

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
            inputFirstField.setFieldValue(inputFirstFieldValue);
            inputSecondField.setFieldValue(inputSecondFieldValue);

            String[][] resultFirstFieldValue = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0",},
                    {"0", "0", "0", "0", "0",},};
            String[][] resultSecondFieldValue = new String[][]{
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},
                    {"0", "0", "0", "0"},};
            resultFirstField.setFieldValue(resultFirstFieldValue);
            resultSecondField.setFieldValue(resultSecondFieldValue);

            List<MineField> actualResult = MinesweeperCalculator.processAllFields(inputListOfFields);

            boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
            Assert.assertTrue(equalLists);
        }
    }
}
