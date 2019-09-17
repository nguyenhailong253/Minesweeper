package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.DimensionsOutOfRangeException;
import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.exception.InvalidRowFormatException;
import com.myob.minesweeper.utils.StringValidator;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MineFieldService {

    public static MineField constructNewField(int numRows, int numColumns) {
        if (!MineFieldValidator.validateDimensionValuesInRange(numRows, numColumns, Constants.MIN_SIZE, Constants.MAX_SIZE)) {
            throw new DimensionsOutOfRangeException(Constants.DIMENSION_OUT_OF_RANGE);
        }
        return new MineField(numRows, numColumns);
    }

    public static void updateFieldValues(MineField field, String[][] newFieldValues) {
        if (newFieldValues.length != field.getRowDimension()) {
            throw new InvalidFieldValuesException();
        }
        for (int rowIndex = 0; rowIndex < newFieldValues.length; rowIndex++) {
            String rowValue = String.join(Constants.INPUT_DELIMITER, newFieldValues[rowIndex]);
            updateRowValue(field, rowValue, rowIndex);
        }
    }

    public static void updateRowValue(MineField field, String inputRow, int rowIndex) {
        String validPattern = field.getFieldState() == MineFieldState.CONSTRUCTED
                ? Constants.INPUT_ROW_PATTERN : Constants.RESULT_ROW_PATTERN;

        if (!StringValidator.isStringMatchedPattern(inputRow, validPattern)
                || inputRow.length() != field.getColumnDimension()) {
            throw new InvalidRowFormatException(Constants.INVALID_ROW_FORMAT);
        }

        field.setRowValue(inputRow.split(Constants.INPUT_DELIMITER), rowIndex);
    }

    public static void updateFieldState(MineField field, MineFieldState newState) {
        field.setFieldState(newState);
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
