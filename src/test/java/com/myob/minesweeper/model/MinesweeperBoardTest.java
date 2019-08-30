package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.TooManyCharactersInARowException;
import org.junit.Assert;
import org.junit.Test;

public class MinesweeperBoardTest {

    private static int[] firstSetOf2Dimensions = new int[]{1,1};
    private static int[] secondSetOf2Dimensions = new int[]{1,1};
    private static MinesweeperBoard firstBoardSize1By1 = new MinesweeperBoard(firstSetOf2Dimensions);
    private static MinesweeperBoard secondBoardSize1By1 = new MinesweeperBoard(secondSetOf2Dimensions);

    public static class TestSetRowValue {

        @Test
        public void shouldReturnArrayOf1Dots_WhenSetArrayOf1DotsAtRow0() {
            String[] expectedRowValue = new String[]{"."};
            int rowIndex = 0;
            firstBoardSize1By1.setRowValue(new String[]{"."}, rowIndex);

            String[] actualRowValue = firstBoardSize1By1.getRowValue(rowIndex);

            Assert.assertArrayEquals(expectedRowValue, actualRowValue);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_WhenSetArrayAtOutOfBoundsRowIndex() {
            int rowIndex = 100;
            firstBoardSize1By1.setRowValue(new String[]{"."}, rowIndex);
        }

        @Test(expected = TooManyCharactersInARowException.class)
        public void shouldThrowException_WhenSetArrayLongerThanRowDimension() {
            int rowIndex = 0;
            firstBoardSize1By1.setRowValue(new String[]{".", ".", ".", "."}, rowIndex);
        }
    }

    public static class TestSetCellValue {

        @Test
        public void shouldReturn5_WhenSetValueOf5ForCellAtRow0Column0() {
            String expectedNewCellValue = "5";
            int rowIndex = 0;
            int columnIndex = 0;
            firstBoardSize1By1.setCellValue("5", rowIndex, columnIndex);

            String actualCellValue = firstBoardSize1By1.getCellValue(rowIndex, columnIndex);

            Assert.assertEquals(expectedNewCellValue, actualCellValue);
        }

        // set index out of bounds
        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_WhenRowIndexIsOutOfBounds() {
            int outOfBoundsRow = 10;
            int columnIndex = 0;
            firstBoardSize1By1.setCellValue("5", outOfBoundsRow, columnIndex);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_WhenColumnIndexIsOutOfBounds() {
            int rowIndex = 0;
            int outOfBounrdsColumn = 100;
            firstBoardSize1By1.setCellValue("5", rowIndex, outOfBounrdsColumn);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_WhenRowAndColumnIndicesAreOutOfBounds() {
            int rowIndex = 11110;
            int outOfBounrdsColumn = 10110;
            firstBoardSize1By1.setCellValue("5", rowIndex, outOfBounrdsColumn);
        }
    }

    public static class TestEquals {

        @Test
        public void shouldReturnTrue_WhenCompareTheSameBoardObject() {
            Assert.assertEquals(firstBoardSize1By1, firstBoardSize1By1);
        }

        @Test
        public void shouldReturnTrue_WhenCompare2BoardsWithSameRowAndColumnAndContents() {
            String sameCellContent = new String("*");
            int rowIndex = 0;
            int columnIndex = 0;

            firstBoardSize1By1.setCellValue(sameCellContent, rowIndex, columnIndex);
            secondBoardSize1By1.setCellValue(sameCellContent, rowIndex, columnIndex);

            Assert.assertEquals(firstBoardSize1By1, firstBoardSize1By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2BoardsWithDifferentRowsButSameColumns() {
            int[] fourByOneDimension = new int[]{4,1};
            MinesweeperBoard newBoardSize4By1 = new MinesweeperBoard(fourByOneDimension);
            Assert.assertNotEquals(firstBoardSize1By1, newBoardSize4By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2BoardsWithSameRowsButDifferentColumns() {
            int[] oneByFourDimension = new int[]{1,4};
            MinesweeperBoard newBoardSize1By4 = new MinesweeperBoard(oneByFourDimension);
            Assert.assertNotEquals(firstBoardSize1By1, newBoardSize1By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2BoardsWithDifferentRowsAndColumns() {
            int[] fourByfourDimension = new int[]{4,4};
            MinesweeperBoard newBoardSize4By4 = new MinesweeperBoard(fourByfourDimension);
            Assert.assertNotEquals(firstBoardSize1By1, newBoardSize4By4);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2BoardsWithSameRowsAndColumnssButDifferentContents() {
            String cellContent = "*";
            String newCellContent = ".";
            int rowIndex = 0;
            int columnIndex = 0;

            firstBoardSize1By1.setCellValue(cellContent, rowIndex, columnIndex);
            secondBoardSize1By1.setCellValue(newCellContent, rowIndex, columnIndex);

            Assert.assertNotEquals(firstBoardSize1By1, secondBoardSize1By1);
        }

        @Test
        public void shouldReturnFalse_WhenCompare2BoardsWithDifferentDimensionsAndContents() {
            int rowIndex = 0;
            int columnIndex = 0;
            String cellContent = "*";
            String newCellContent = ".";
            int[] fiveByFiveDimensions = new int[] {5,5};
            MinesweeperBoard newBoard5By5 = new MinesweeperBoard(fiveByFiveDimensions);

            firstBoardSize1By1.setCellValue(cellContent, rowIndex, columnIndex);
            newBoard5By5.setCellValue(newCellContent, rowIndex, columnIndex);

            Assert.assertNotEquals(firstBoardSize1By1, newBoard5By5);
        }
    }
}