package com.myob.minesweeper.service.calculator;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperCalculator {

    public static List<MineField> calculateAllFields(List<MineField> inputFields) {

        List<MineField> resultFields = new ArrayList<>();

        for (MineField field: inputFields) {
            if (field.getFieldState() == MineFieldState.INITIALISED) {
                String[][] calculatedFieldValues =
                        calculateEachField(field.getFieldValues(), field.getRowDimension(), field.getColumnDimension());
                MineFieldService.updateFieldValues(field, calculatedFieldValues);
                MineFieldService.updateFieldState(field, MineFieldState.CALCULATED);
            }
            resultFields.add(field);
        }
        return resultFields;
    }

    private static String[][] calculateEachField(String[][] fieldValues, int numRows, int numColumns) {
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
                String currentSquareValue = fieldValues[rowIndex][columnIndex];

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    String calculatedSquare = calculateSingleSquare(fieldValues, rowIndex, columnIndex);
                    fieldValues[rowIndex][columnIndex] = calculatedSquare;
                }
            }
        }
        return fieldValues;
    }

    private static String calculateSingleSquare(String[][] fieldValues, int rowIndex, int columnIndex) {
        int maxRowIndex = fieldValues.length;
        int maxColumnIndex = fieldValues[0].length;

        List<Integer> adjacentRows =
                MineFieldService.getAdjacentIndices(rowIndex, maxRowIndex, Constants.ADJACENT_RANGE);
        List<Integer> adjacentColumns =
                MineFieldService.getAdjacentIndices(columnIndex, maxColumnIndex, Constants.ADJACENT_RANGE);

        String calculatedSquare = calculateAdjacentMines(fieldValues, adjacentRows, adjacentColumns);
        return calculatedSquare;
    }

    private static String calculateAdjacentMines(String[][] fieldValue, List<Integer> adjacentRows, List<Integer> adjacentColumns) {
        int numOfMines = 0;

        for (int row : adjacentRows) {
            for (int column : adjacentColumns) {
                String adjacentSquare = fieldValue[row][column];
                if (adjacentSquare.equals(Constants.MINE_SQUARE)) {
                    numOfMines++;
                }
            }
        }
        return Integer.toString(numOfMines);
    }
}
