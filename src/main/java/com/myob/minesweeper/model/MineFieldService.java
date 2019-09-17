package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.DimensionsOutOfRangeException;
import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.exception.InvalidRowFormatException;
import com.myob.minesweeper.utils.Constants;
import com.myob.minesweeper.utils.NumberValidator;

import java.util.ArrayList;
import java.util.List;

public class MineFieldService {

    public static MineField constructMineField(int numRows, int numColumns) {
        if (!NumberValidator.validateNumberInRange(numRows, Constants.MIN_SIZE, Constants.MAX_SIZE)
                || !NumberValidator.validateNumberInRange(numColumns, Constants.MIN_SIZE, Constants.MAX_SIZE)) {
            throw new DimensionsOutOfRangeException(Constants.DIMENSION_OUT_OF_RANGE);
        }
        return new MineField(numRows, numColumns);
    }

    public static void updateFieldValues(MineField mineField, String[][] newFieldValues) {
        if (newFieldValues.length != mineField.getRowDimension()) {
            throw new InvalidFieldValuesException();
        }
        for (int rowIndex = 0; rowIndex < newFieldValues.length; rowIndex++) {
            updateRowValue(mineField, newFieldValues[rowIndex], rowIndex);
        }
    }

    public static void updateRowValue(MineField mineField, String[] inputRow, int rowIndex) {
        updateRowValue(mineField, String.join(Constants.INPUT_DELIMITER, inputRow), rowIndex);
    }

    public static void updateRowValue(MineField mineField, String inputRow, int rowIndex) {
        String validPattern = mineField.getFieldState() == MineFieldState.CONSTRUCTED
                ? Constants.INPUT_ROW_PATTERN : Constants.RESULT_ROW_PATTERN;

        if (!inputRow.matches(validPattern)
                || inputRow.length() != mineField.getColumnDimension()) {
            throw new InvalidRowFormatException(Constants.INVALID_ROW_FORMAT);
        }

        mineField.setRowValue(inputRow.split(Constants.INPUT_DELIMITER), rowIndex);
    }

    public static void updateFieldState(MineField mineField, MineFieldState newState) {
        mineField.setFieldState(newState);
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
