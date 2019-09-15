package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.DimensionsOutOfRangeException;
import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.exception.InvalidRowFormatException;
import com.myob.minesweeper.exception.InvalidSquareContentLength;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MineFieldService {

    public static MineField initialiseNewField(int numRows, int numColumns) {
        if (!MineFieldValidator.validateDimensionValuesInRange(numRows, numColumns, Constants.MIN_SIZE, Constants.MAX_SIZE)) {
            throw new DimensionsOutOfRangeException(Constants.DIMENSION_OUT_OF_RANGE);
        }
        return new MineField(numRows, numColumns);
    }

    public static MineField updateFieldValues(MineField field, String[][] newFieldValues) {
        if (newFieldValues == null
                || newFieldValues.length != field.getRowDimension()
                || newFieldValues[0].length != field.getColumnDimension()) {
            throw new InvalidFieldValuesException();
        }
        field.setFieldValues(newFieldValues);
        return field;
    }

    public static MineField updateFieldState(MineField field, MineFieldState newState) {
        field.setFieldState(newState);
        return field;
    }

    public static String[] getRowOfFieldByIndex(MineField field, int rowIndex) {
        if (rowIndex >= field.getRowDimension()) {
            throw new IndexOutOfBoundsException();
        }
        return field.getFieldValues()[rowIndex];
    }

    public static MineField setRowOfFieldByIndex(MineField field, int rowIndex, String newRow) {
        if (rowIndex >= field.getRowDimension()) {
            throw new IndexOutOfBoundsException();
        }
        if (!MineFieldValidator.validateLengthOfRowInput(newRow, field.getColumnDimension())) {
            throw new InvalidRowFormatException(Constants.INVALID_ROW_FORMAT);
        }

        String[][] newFieldValues = field.getFieldValues();
        newFieldValues[rowIndex] = newRow.split(Constants.EMPTY_STRING);
        field.setFieldValues(newFieldValues);
        return field;
    }

    public static String getSquareOfFieldByIndices(MineField field, int rowIndex, int colIndex) {
        if (rowIndex >= field.getRowDimension() || colIndex >= field.getColumnDimension()) {
            throw new IndexOutOfBoundsException();
        }
        return field.getFieldValues()[rowIndex][colIndex];
    }

    public static MineField setSquareOfFieldByIndices(MineField field, int rowIndex, int colIndex, String newSquare) {
        if (rowIndex >= field.getRowDimension() || colIndex >= field.getColumnDimension()) {
            throw new IndexOutOfBoundsException();
        }

        if (newSquare.length() != Constants.SQUARE_CONTENT_LENGTH) {
            throw new InvalidSquareContentLength();
        }

        String[][] newFieldValues = field.getFieldValues();
        newFieldValues[rowIndex][colIndex] = newSquare;
        field.setFieldValues(newFieldValues);
        return field;
    }

    public static List<Integer> getAdjacentIndices(int currentIndex, int maxIndex, int adjacentRange) {
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
