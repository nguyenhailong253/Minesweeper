package com.myob.minesweeper.service.calculator;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperCalculator {

    public static List<MineField> processAllFields(List<MineField> inputFields) {

        List<MineField> resultFields = new ArrayList<>();

        for (MineField field: inputFields) {
            if (field.getFieldState() == MineFieldState.INITIALISED) {
                field = processSingleField(field);
            }
            resultFields.add(field);
        }
        return resultFields;
    }

    private static MineField processSingleField(MineField field) {
        int rowDimension = field.getRowDimension();
        int columnDimension = field.getColumnDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                String currentSquareValue = field.getSquareValue(rowIndex, columnIndex);

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    List<Integer> adjacentRows = field.getAdjacentRowIndices(rowIndex, Constants.ADJACENT_RANGE);
                    List<Integer> adjacentColumns = field.getAdjacentColumnIndices(columnIndex, Constants.ADJACENT_RANGE);
                    String processedSquare = calculateAdjacentMines(field.getFieldValue(), adjacentRows, adjacentColumns);
                    field.setSquareValue(processedSquare, rowIndex, columnIndex);
                }
            }
        }
        field.setFieldState(MineFieldState.CALCULATED);
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
