package com.myob.minesweeper.model;

import com.myob.minesweeper.utils.Constants;

import java.util.Arrays;

public class MineField {
    private int rowDimension;
    private int columnDimension;
    private String[][] fieldValue;

    public MineField(int[] input2Dimensions) {
        createDimensions(input2Dimensions);
        createEmptyField();
    }

    public MineField(int numRows, int numColumns) {
        rowDimension = numRows;
        columnDimension = numColumns;
        createEmptyField();
    }

    private void createDimensions(int[] input2Dimensions) {
        rowDimension = input2Dimensions[0];
        columnDimension = input2Dimensions[1];
    }

    private void createEmptyField() {
        fieldValue = new String[rowDimension][columnDimension];
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MineField comparedField = (MineField) obj;
        return comparedField.rowDimension == this.rowDimension &&
                comparedField.columnDimension == this.columnDimension &&
                Arrays.deepEquals(comparedField.fieldValue, this.fieldValue);
    }

    public String[] getRowValue(int rowIndex) {
        return fieldValue[rowIndex];
    }

    public void setRowValue(String[] rowValue, int rowIndex) {
        fieldValue[rowIndex] = rowValue;
    }

    public String getSquareValue(int rowIndex, int colIndex) {
        return fieldValue[rowIndex][colIndex];
    }

    public void setSquareValue(String newSquareValue, int rowIndex, int colIndex) {
        fieldValue[rowIndex][colIndex] = newSquareValue;
    }

    public MineField initialiseField() {
        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                if (fieldValue[rowIndex][columnIndex] != null && fieldValue[rowIndex][columnIndex].equals(Constants.SAFE_SQUARE)) {
                    fieldValue[rowIndex][columnIndex] = Constants.ZERO_STRING;
                }
            }
        }
        return this;
    }
}
