package com.myob.minesweeper.model;

public class MineField {
    private int rowDimension;
    private int columnDimension;
    private String[][] fieldValues;
    private MineFieldState fieldState;

    protected MineField(int numRows, int numColumns) {
        this.rowDimension = numRows;
        this.columnDimension = numColumns;
        this.fieldState = MineFieldState.INITIALISED;
        this.fieldValues = new String[numRows][numColumns];
    }

    public String[][] getFieldValues() {
        return fieldValues;
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

    protected void setFieldValues(String[][] fieldValues) {
        this.fieldValues = fieldValues;
    }

    protected void setRowValue(String[] rowValue, int rowIndex) {
        this.fieldValues[rowIndex] = rowValue;
    }

    protected void setFieldState(MineFieldState fieldState) {
        this.fieldState = fieldState;
    }
}
