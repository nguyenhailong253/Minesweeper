package com.myob.minesweeper.model;

import com.myob.minesweeper.exception.DimensionsOutOfRangeException;
import com.myob.minesweeper.exception.InvalidFieldValuesException;
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

    public static void updateFieldValues(MineField field, String[][] newFieldValues) {
        if (!MineFieldValidator.validateContentOf2DStringArray(newFieldValues, Constants.VALID_SQUARE)
                || newFieldValues.length != field.getRowDimension()
                || newFieldValues[0].length != field.getColumnDimension()) {
            throw new InvalidFieldValuesException();
        }
        field.setFieldValues(newFieldValues);
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
