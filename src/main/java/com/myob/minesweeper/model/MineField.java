package com.myob.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class MineField {
    private int rowDimension;
    private int columnDimension;
    private String[][] fieldValue;
    private MineFieldState fieldState;

    public MineField(int numRows, int numColumns, String[][] fieldValue) {
        this.rowDimension = numRows;
        this.columnDimension = numColumns;
        this.fieldValue = fieldValue;
        this.fieldState = MineFieldState.INITIALISED;
    }

    public String[][] getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String[][] fieldValue) {
        this.fieldValue = fieldValue;
    }

    public int getColumnDimension() {
        return columnDimension;
    }

    public int getRowDimension() {
        return rowDimension;
    }

    public MineFieldState getFieldState() {
        return fieldState;
    }

    public void setFieldState(MineFieldState fieldState) {
        this.fieldState = fieldState;
    }

    public String[] getRowValue(int rowIndex) {
        return fieldValue[rowIndex];
    }

    public String getSquareValue(int rowIndex, int colIndex) {
        return fieldValue[rowIndex][colIndex];
    }

    public void setSquareValue(String newSquareValue, int rowIndex, int colIndex) {
        fieldValue[rowIndex][colIndex] = newSquareValue;
    }

    public List<Integer> getAdjacentRowIndices(int currentRowIndex, int adjacentRange) {
        return getAdjacentIndices(currentRowIndex, rowDimension, adjacentRange);
    }

    public List<Integer> getAdjacentColumnIndices(int currentColumnIndex, int adjacentRange) {
        return getAdjacentIndices(currentColumnIndex, columnDimension, adjacentRange);
    }

    private List<Integer> getAdjacentIndices(int currentIndex, int maxIndex, int adjacentRange) {
        List<Integer> adjacentIndices = new ArrayList<>();
        adjacentIndices.add(currentIndex);
        if (currentIndex - adjacentRange >= 0) {
            adjacentIndices.add(currentIndex - adjacentRange);
        }
        if (currentIndex + adjacentRange < maxIndex) {
            adjacentIndices.add(currentIndex + adjacentRange);
        }
        return adjacentIndices;
    }
}
