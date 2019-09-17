package com.myob.minesweeper.integration;

import com.myob.minesweeper.TestHelper;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.service.calculator.IMinesweeperCalculator;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
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
    private static IMinesweeperCalculator calculator = new MinesweeperCalculator();

    private static void constructDefaultFields() {
        inputFirstField = MineFieldService.constructMineField(firstFieldNumRows, firstFieldNumColumns);
        inputSecondField = MineFieldService.constructMineField(secondFieldNumRows, secondFieldNumColumns);
        resultFirstField = MineFieldService.constructMineField(firstFieldNumRows, firstFieldNumColumns);
        resultSecondField = MineFieldService.constructMineField(secondFieldNumRows, secondFieldNumColumns);
    }

    private static void initialiseFieldStates() {
        MineFieldService.updateFieldState(inputFirstField, MineFieldState.VALIDATED);
        MineFieldService.updateFieldState(inputSecondField, MineFieldState.VALIDATED);
        MineFieldService.updateFieldState(resultFirstField, MineFieldState.CALCULATED);
        MineFieldService.updateFieldState(resultSecondField, MineFieldState.CALCULATED);
    }

    private static void initialiseDefaultListOfFields() {
        inputListOfFields = new ArrayList<>();
        expectedResultListOfFields = new ArrayList<>();

        inputListOfFields.add(inputFirstField);
        inputListOfFields.add(inputSecondField);

        expectedResultListOfFields.add(resultFirstField);
        expectedResultListOfFields.add(resultSecondField);
    }

    @Before
    public void initialiseDefaultValues() {
        constructDefaultFields();
        initialiseFieldStates();
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
        MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
        MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

        List<MineField> actualResult = calculator.calculateHintNumbersInFields(inputListOfFields);

        boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
        Assert.assertTrue(equalLists);
    }

    @Test
    public void shouldReturnEmptyList_WhenInputListIsEmpty() {
        List<MineField> inputEmptyList = new ArrayList<>();
        List<MineField> expectedEmptyList = new ArrayList<>();

        List<MineField> actualResultList = calculator.calculateHintNumbersInFields(inputEmptyList);

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
        MineFieldService.updateFieldValues(inputFirstField, inputFirstFieldValue);
        MineFieldService.updateFieldValues(inputSecondField, inputSecondFieldValue);
        MineFieldService.updateFieldValues(resultFirstField, inputFirstFieldValue);
        MineFieldService.updateFieldValues(resultSecondField, inputSecondFieldValue);

        List<MineField> actualResult = calculator.calculateHintNumbersInFields(inputListOfFields);

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
        MineFieldService.updateFieldValues(resultFirstField, resultFirstFieldValue);
        MineFieldService.updateFieldValues(resultSecondField, resultSecondFieldValue);

        List<MineField> actualResult = calculator.calculateHintNumbersInFields(inputListOfFields);

        boolean equalLists = TestHelper.validateEqualListsOfFields(expectedResultListOfFields, actualResult);
        Assert.assertTrue(equalLists);
    }
}
