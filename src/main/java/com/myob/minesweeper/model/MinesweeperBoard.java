package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.TooManyCharactersInARowException;
import com.myob.minesweeper.utils.Constants;

import java.util.Arrays;

public class MinesweeperBoard {
    private int rowDimension;
    private int columnDimension;
    private String[][] boardContent;

    public MinesweeperBoard(int[] input2Dimensions) {
        createDimensions(input2Dimensions);
        createEmptyBoard();
    }

    private void createDimensions(int[] input2Dimensions) {
        rowDimension = input2Dimensions[0];
        columnDimension = input2Dimensions[1];
    }

    private void createEmptyBoard() {
        boardContent = new String[rowDimension][columnDimension];
    }

    public String[][] getBoardContent() {
        return boardContent;
    }

    public int getColumnDimension() {
        return columnDimension;
    }

    public int getRowDimension() {
        return rowDimension;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MinesweeperBoard comparedBoard = (MinesweeperBoard) obj;
        return comparedBoard.rowDimension == this.rowDimension &&
                comparedBoard.columnDimension == this.columnDimension &&
                Arrays.deepEquals(comparedBoard.boardContent, this.boardContent);
    }

    public String[] getRowValue(int rowIndex) {
        return boardContent[rowIndex];
    }

    public void setRowValue(String[] rowValue, int rowIndex) {
        if (rowValue.length != getColumnDimension()) {
            throw new TooManyCharactersInARowException(Constants.TOO_MANY_CHARACTERS_IN_A_ROW);
        }
        boardContent[rowIndex] = rowValue;
    }

    public String getCellValue(int rowIndex, int colIndex) {
        return boardContent[rowIndex][colIndex];
    }

    public void setCellValue(String newCellValue, int rowIndex, int colIndex) {
        boardContent[rowIndex][colIndex] = newCellValue;
    }
}
