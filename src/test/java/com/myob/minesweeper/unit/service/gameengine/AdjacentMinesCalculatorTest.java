package com.myob.minesweeper.unit.service.gameengine;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.gameengine.AdjacentMinesCalculator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdjacentMinesCalculatorTest {

    private static int[] defaultFieldDimensions = new int[]{3,5};
    private static MineField fieldWithAllSafeSquares = new MineField(defaultFieldDimensions);
    private static MineField fieldWithOnly1SafeSquare = new MineField(defaultFieldDimensions);
    private static MineField fieldWithRandomNumOfMineAndSafeSquares = new MineField(defaultFieldDimensions);

    public static class TestCalculateAdjacentMines {

        @BeforeClass
        public static void initialiseSampleInputs() {
            String[][] fieldValues = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"}};
            fieldWithAllSafeSquares.setFieldValue(fieldValues);

            fieldValues = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "0", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"}};
            fieldWithOnly1SafeSquare.setFieldValue(fieldValues);

            fieldValues = new String[][]{
                    {"*", "0", "*", "0", "*"},
                    {"0", "*", "0", "*", "0"},
                    {"*", "0", "*", "0", "*"}};
            fieldWithRandomNumOfMineAndSafeSquares.setFieldValue(fieldValues);
        }

        @Test
        public void shouldReturn0_WhenAllAdjacentSquaresOfSquareAtRow1Column1AreNotMines_InFieldWithAllSafeSquares() {
            String expectedSquareValue = "0";

            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
                    fieldWithAllSafeSquares, 1,1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }

        @Test
        public void shouldReturn8_WhenAllAdjacentSquaresOfSquareAtRow1Column1AreMines_InFieldWithOnly1SafeSquare() {
            String expectedSquareValue = "8";

            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
                    fieldWithOnly1SafeSquare, 1,1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }

        @Test
        public void shouldReturn3_When3MinesAdjacentToSafeSquareAtRow0Column1_InFieldWithRandomNumOfMineAndSafeSquares() {
            String expectedSquareValue = "3";

            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
                    fieldWithRandomNumOfMineAndSafeSquares, 0, 1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }
    }
}