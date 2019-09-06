package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdjacentMinesCalculatorTest {

<<<<<<< HEAD
=======
    private static AdjacentMinesCalculator calculator = new AdjacentMinesCalculator();
>>>>>>> master
    private static int[] defaultFieldDimensions = new int[]{3,5};
    private static MineField fieldWithAllSafeSquares = new MineField(defaultFieldDimensions);
    private static MineField fieldWithOnly1SafeSquare = new MineField(defaultFieldDimensions);
    private static MineField fieldWithRandomNumOfMineAndSafeSquares = new MineField(defaultFieldDimensions);

    public static class TestCalculateAdjacentMines {

        @BeforeClass
        public static void initialise() {
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

<<<<<<< HEAD
            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
=======
            String actualSquareValue = calculator.calculateAdjacentMines(
>>>>>>> master
                    fieldWithAllSafeSquares, 1,1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }

        @Test
        public void shouldReturn8_WhenAllAdjacentSquaresOfSquareAtRow1Column1AreMines_InFieldWithOnly1SafeSquare() {
            String expectedSquareValue = "8";

<<<<<<< HEAD
            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
=======
            String actualSquareValue = calculator.calculateAdjacentMines(
>>>>>>> master
                    fieldWithOnly1SafeSquare, 1,1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }

        @Test
        public void shouldReturn3_When3MinesAdjacentToSafeSquareAtRow0Column1_InFieldWithRandomNumOfMineAndSafeSquares() {
            String expectedSquareValue = "3";

<<<<<<< HEAD
            String actualSquareValue = AdjacentMinesCalculator.calculateAdjacentMines(
=======
            String actualSquareValue = calculator.calculateAdjacentMines(
>>>>>>> master
                    fieldWithRandomNumOfMineAndSafeSquares, 0, 1);

            Assert.assertEquals(expectedSquareValue, actualSquareValue);
        }
    }
}