package com.myob.minesweeper.unit.model;

import org.junit.Assert;
import org.junit.Test;

public class MineFieldTest {

    private static int[] firstSetOf2Dimensions = new int[]{1,1};
    private static int[] secondSetOf2Dimensions = new int[]{1,1};
    private static MineField firstFieldSize1By1 = new MineField(firstSetOf2Dimensions);
    private static MineField secondFieldSize1By1 = new MineField(secondSetOf2Dimensions);

    public static class TestSetRowValue {

        @Test
        public void shouldSetArrayOf1DotAtRow0() {
            String[] expectedRowValue = new String[]{"."};
            int rowIndex = 0;
            firstFieldSize1By1.setRowValue(new String[]{"."}, rowIndex);

            String[] actualRowValue = firstFieldSize1By1.getRowValue(rowIndex);

            Assert.assertArrayEquals(expectedRowValue, actualRowValue);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenSetArrayAtOutOfBoundsRowIndex() {
            int rowIndex = 100;
            firstFieldSize1By1.setRowValue(new String[]{"."}, rowIndex);
        }
    }

    public static class TestSetSquareValue {

        @Test
        public void shouldSetValueOf5ForSquareAtRow0Column0() {
            String expectedNewSquareValue = "5";
            int rowIndex = 0;
            int columnIndex = 0;
            firstFieldSize1By1.setSquareValue("5", rowIndex, columnIndex);

            String actualSquareValue = firstFieldSize1By1.getSquareValue(rowIndex, columnIndex);

            Assert.assertEquals(expectedNewSquareValue, actualSquareValue);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowIndexIsOutOfBounds() {
            int outOfBoundsRow = 10;
            int columnIndex = 0;
            firstFieldSize1By1.setSquareValue("5", outOfBoundsRow, columnIndex);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenColumnIndexIsOutOfBounds() {
            int rowIndex = 0;
            int outOfBoundsColumn = 100;
            firstFieldSize1By1.setSquareValue("5", rowIndex, outOfBoundsColumn);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowAndColumnIndicesAreOutOfBounds() {
            int rowIndex = 11110;
            int outOfBoundsColumn = 10110;
            firstFieldSize1By1.setSquareValue("5", rowIndex, outOfBoundsColumn);
        }
    }

    public static class TestEquals {

        @Test
        public void shouldReturnTrue_WhenCompareTheSameFieldObject() {
            Assert.assertEquals(firstFieldSize1By1, firstFieldSize1By1);
        }

        @Test
        public void shouldReturnTrue_WhenCompare2FieldsWithSameRowsAndColumnsAndContents() {
            String sameSquareContent = new String("*");
            int rowIndex = 0;
            int columnIndex = 0;

            firstFieldSize1By1.setSquareValue(sameSquareContent, rowIndex, columnIndex);
            secondFieldSize1By1.setSquareValue(sameSquareContent, rowIndex, columnIndex);

            Assert.assertEquals(firstFieldSize1By1, secondFieldSize1By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentRowsButSameColumns() {
            int[] fourByOneDimension = new int[]{4,1};
            MineField newFieldSize4By1 = new MineField(fourByOneDimension);
            Assert.assertNotEquals(firstFieldSize1By1, newFieldSize4By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithSameRowsButDifferentColumns() {
            int[] oneByFourDimension = new int[]{1,4};
            MineField newFieldSize1By4 = new MineField(oneByFourDimension);
            Assert.assertNotEquals(firstFieldSize1By1, newFieldSize1By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentRowsAndColumns() {
            int[] fourByFourDimension = new int[]{4,4};
            MineField newFieldSize4By4 = new MineField(fourByFourDimension);
            Assert.assertNotEquals(firstFieldSize1By1, newFieldSize4By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithSameRowsAndColumnsButDifferentContents() {
            String squareContent = "*";
            String newSquareContent = ".";
            int rowIndex = 0;
            int columnIndex = 0;

            firstFieldSize1By1.setSquareValue(squareContent, rowIndex, columnIndex);
            secondFieldSize1By1.setSquareValue(newSquareContent, rowIndex, columnIndex);

            Assert.assertNotEquals(firstFieldSize1By1, secondFieldSize1By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentDimensionsAndContents() {
            int rowIndex = 0;
            int columnIndex = 0;
            String squareContent = "*";
            String newSquareContent = ".";
            int[] fiveByFiveDimensions = new int[] {5,5};
            MineField newField5By5 = new MineField(fiveByFiveDimensions);

            firstFieldSize1By1.setSquareValue(squareContent, rowIndex, columnIndex);
            newField5By5.setSquareValue(newSquareContent, rowIndex, columnIndex);

            Assert.assertNotEquals(firstFieldSize1By1, newField5By5);
        }
    }
}