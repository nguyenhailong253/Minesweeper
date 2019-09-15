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
                field = calculateEachField(field);
            }
            resultFields.add(field);
        }
        return resultFields;
    }

    private static MineField calculateEachField(MineField field) {
        int rowDimension = field.getRowDimension();
        int columnDimension = field.getColumnDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                String currentSquareValue = MineFieldService.getSquareOfFieldByIndices(field, rowIndex, columnIndex);

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    field = calculateSingleSquare(field, rowIndex, columnIndex);
                }
            }
        }
        field = MineFieldService.updateFieldState(field, MineFieldState.CALCULATED);
        return field;
    }

    private static MineField calculateSingleSquare(MineField field, int rowIndex, int columnIndex) {
        List<Integer> adjacentRows =
                MineFieldService.getAdjacentIndices(rowIndex, field.getRowDimension(), Constants.ADJACENT_RANGE);
        List<Integer> adjacentColumns =
                MineFieldService.getAdjacentIndices(columnIndex, field.getColumnDimension(), Constants.ADJACENT_RANGE);

        String calculatedSquare = calculateAdjacentMines(field.getFieldValues(), adjacentRows, adjacentColumns);

        field = MineFieldService.setSquareOfFieldByIndices(field, rowIndex, columnIndex, calculatedSquare);
        return field;
    }

    private static String calculateAdjacentMines(String[][] fieldValue, List<Integer> adjacentRows, List<Integer> adjacentColumns) {
        int numOfMines = 0;

        for (int row : adjacentRows) {
            for (int column : adjacentColumns) {
                String adjacentSquare = fieldValue[row][column];
                if (adjacentSquare.equals(Constants.MINE_SQUARE)) {
                    numOfMines += 1;
                }
            }
        }
        return Integer.toString(numOfMines);
    }
}
