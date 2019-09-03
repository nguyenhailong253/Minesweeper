package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/* INTEGRATION TEST */
public class MinesweeperGameEngineTest {

    private static MineFieldInitiator initiator = new MineFieldInitiator();
    private static AdjacentMinesCalculator calculator = new AdjacentMinesCalculator();
    private static MinesweeperGameEngine gameEngine = new MinesweeperGameEngine(initiator, calculator);
    private static int[] firstFieldDimension = new int[]{3,5};
    private static int[] secondFieldDimension = new int[]{4,4};
    private static MineField inputFirstField;
    private static MineField inputSecondField;
    private static MineField resultFirstField;
    private static MineField resultSecondField;
    private static List<MineField> inputListOfFields;
    private static List<MineField> expectedResultListOfFields;

    public static class ProcessMineFieldTest {

        @Before
        public void initialise() {
            inputFirstField = new MineField(firstFieldDimension);
            inputSecondField = new MineField(secondFieldDimension);
            resultFirstField = new MineField(firstFieldDimension);
            resultSecondField = new MineField(secondFieldDimension);
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

            List<MineField> actualResult = gameEngine.processAllFields(inputListOfFields);

            Assert.assertEquals(expectedResultListOfFields, actualResult);
        }

        @Test
        public void shouldReturnEmptyList_WhenInputListIsEmpty() {
            List<MineField> inputEmptyList = new ArrayList<>();
            List<MineField> expectedEmptyList = new ArrayList<>();

            List<MineField> actualResultList = gameEngine.processAllFields(inputEmptyList);

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

            List<MineField> actualResult = gameEngine.processAllFields(inputListOfFields);

            Assert.assertEquals(expectedResultListOfFields, actualResult);
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

            List<MineField> actualResult = gameEngine.processAllFields(inputListOfFields);

            Assert.assertEquals(expectedResultListOfFields, actualResult);
        }
    }
}
