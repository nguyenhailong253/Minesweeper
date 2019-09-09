package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.model.MineField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MineFieldTest {

    private static int[] fieldDimensions = new int[]{2,3};
    private static MineField inputField;

    public static class TestInitialiseField {

        @Before
        public void initialiseNewField() {
            inputField = new MineField(fieldDimensions);
        }

        @Test
        public void shouldReturnEmptyField_WhenReceiveFieldWithNoContent() {
            String[][] expectedEmptyField = new String[2][3];

            String[][] actualField = inputField.initialiseField().getFieldValue();

            Assert.assertArrayEquals(expectedEmptyField, actualField);
        }

        @Test
        public void shouldReturnFieldWithDotsReplacedByZeros_WhenReceiveFieldWithBothDotsAndAsterisks() {
            String[][] inputFieldValue = new String[][]{
                    {"*", ".", "."},
                    {".", "*", "."}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"*", "0", "0"}, {"0", "*", "0"}};

            String[][] actualField = inputField.initialiseField().getFieldValue();

            Assert.assertArrayEquals(expectedField, actualField);
        }

        @Test
        public void shouldReturnFieldWithSameContent_WhenReceiveFieldWithAllAsterisks() {
            String[][] inputFieldValue = new String[][]{
                    {"*", "*", "*"},
                    {"*", "*", "*"}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"*", "*", "*"}, {"*", "*", "*"}};

            String[][] actualField = inputField.initialiseField().getFieldValue();

            Assert.assertArrayEquals(expectedField, actualField);
        }

        @Test
        public void shouldReturnFieldWithAllZeros_WhenReceiveFieldWithAllDots() {
            String[][] inputFieldValue = new String[][]{
                    {".", ".", "."},
                    {".", ".", "."}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"0", "0", "0"}, {"0", "0", "0"}};

            String[][] actualField = inputField.initialiseField().getFieldValue();

            Assert.assertArrayEquals(expectedField, actualField);
        }
    }

    public static class TestSetRowValue {

        @BeforeClass
        public static void initialise(){
            inputField = new MineField(fieldDimensions);
        }

        @Test
        public void shouldSetArrayOf1DotAtRow0() {
            String[] expectedRowValue = new String[]{"."};
            int rowIndex = 0;
            inputField.setRowValue(new String[]{"."}, rowIndex);

            String[] actualRowValue = inputField.getRowValue(rowIndex);

            Assert.assertArrayEquals(expectedRowValue, actualRowValue);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenSetArrayAtOutOfBoundsRowIndex() {
            int rowIndex = 100;
            inputField.setRowValue(new String[]{"."}, rowIndex);
        }
    }

    public static class TestSetSquareValue {

        @BeforeClass
        public static void initialise(){
            inputField = new MineField(fieldDimensions);
        }

        @Test
        public void shouldSetValueOf5ForSquareAtRow0Column0() {
            String expectedNewSquareValue = "5";
            int rowIndex = 0;
            int columnIndex = 0;
            inputField.setSquareValue("5", rowIndex, columnIndex);

            String actualSquareValue = inputField.getSquareValue(rowIndex, columnIndex);

            Assert.assertEquals(expectedNewSquareValue, actualSquareValue);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowIndexIsOutOfBounds() {
            int outOfBoundsRow = 10;
            int columnIndex = 0;
            inputField.setSquareValue("5", outOfBoundsRow, columnIndex);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenColumnIndexIsOutOfBounds() {
            int rowIndex = 0;
            int outOfBoundsColumn = 100;
            inputField.setSquareValue("5", rowIndex, outOfBoundsColumn);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowAndColumnIndicesAreOutOfBounds() {
            int rowIndex = 11110;
            int outOfBoundsColumn = 10110;
            inputField.setSquareValue("5", rowIndex, outOfBoundsColumn);
        }
    }

    public static class TestEquals {

        private int[] defaultSetOf2Dimensions = new int[]{1,1};
        private MineField baseField = new MineField(defaultSetOf2Dimensions);
        private MineField comparedField = new MineField(defaultSetOf2Dimensions);

        @Test
        public void shouldReturnTrue_WhenCompareTheSameFieldObject() {
            Assert.assertEquals(baseField, baseField);
        }

        @Test
        public void shouldReturnTrue_WhenCompare2FieldsWithSameRowsAndColumnsAndContents() {
            String sameSquareContent = new String("*");
            int rowIndex = 0;
            int columnIndex = 0;

            baseField.setSquareValue(sameSquareContent, rowIndex, columnIndex);
            comparedField.setSquareValue(sameSquareContent, rowIndex, columnIndex);

            Assert.assertEquals(baseField, comparedField);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentRowsButSameColumns() {
            int[] fourByOneDimension = new int[]{4,1};
            MineField newFieldSize4By1 = new MineField(fourByOneDimension);
            Assert.assertNotEquals(baseField, newFieldSize4By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithSameRowsButDifferentColumns() {
            int[] oneByFourDimension = new int[]{1,4};
            MineField newFieldSize1By4 = new MineField(oneByFourDimension);
            Assert.assertNotEquals(baseField, newFieldSize1By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentRowsAndColumns() {
            int[] fourByFourDimension = new int[]{4,4};
            MineField newFieldSize4By4 = new MineField(fourByFourDimension);
            Assert.assertNotEquals(baseField, newFieldSize4By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithSameRowsAndColumnsButDifferentContents() {
            String squareContent = "*";
            String newSquareContent = ".";
            int rowIndex = 0;
            int columnIndex = 0;

            baseField.setSquareValue(squareContent, rowIndex, columnIndex);
            comparedField.setSquareValue(newSquareContent, rowIndex, columnIndex);

            Assert.assertNotEquals(baseField, comparedField);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2FieldsWithDifferentDimensionsAndContents() {
            int rowIndex = 0;
            int columnIndex = 0;
            String squareContent = "*";
            String newSquareContent = ".";
            int[] fiveByFiveDimensions = new int[] {5,5};
            MineField newField5By5 = new MineField(fiveByFiveDimensions);

            baseField.setSquareValue(squareContent, rowIndex, columnIndex);
            newField5By5.setSquareValue(newSquareContent, rowIndex, columnIndex);

            Assert.assertNotEquals(baseField, newField5By5);
        }
    }
}