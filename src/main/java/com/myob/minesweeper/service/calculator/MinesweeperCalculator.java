package com.myob.minesweeper.service.calculator;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperCalculator implements Calculator {

    @Override
    public List<MineField> calculateHintNumbersInFields(List<MineField> inputFields) {

        List<MineField> resultFields = new ArrayList<>();

        for (MineField mineField: inputFields) {
            if (mineField.getFieldState() == MineFieldState.VALIDATED) {
                MineField calculatedField = calculateHintNumbersInSingleField(mineField);

                MineFieldService.updateFieldState(calculatedField, MineFieldState.CALCULATED);
                resultFields.add(calculatedField);
            }
        }
        return resultFields;
    }

    private MineField calculateHintNumbersInSingleField(MineField mineField) {
        String[][] fieldValues = mineField.getFieldValues();
        int numRows = mineField.getRowDimension();
        int numColumns = mineField.getColumnDimension();

        MineField calculatedField = MineFieldService.constructMineField(numRows, numColumns);
        MineFieldService.updateFieldState(calculatedField, MineFieldState.VALIDATED);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            String[] calculatedRow = calculateHintNumbersInSingleRow(fieldValues, numColumns, rowIndex);
            MineFieldService.updateRowValue(calculatedField, calculatedRow, rowIndex);
        }
        return calculatedField;
    }

    private String[] calculateHintNumbersInSingleRow(String[][] fieldValues, int numColumns, int rowIndex) {
        String[] originalRowValue = fieldValues[rowIndex];
        String[] calculatedRowValue = new String[numColumns];

        for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
            String calculatedSquare = originalRowValue[columnIndex];

            if (!calculatedSquare.equals(Constants.MINE_SQUARE)) {
                calculatedSquare = calculateHintNumberInSquare(fieldValues, rowIndex, columnIndex);
            }
            calculatedRowValue[columnIndex] = calculatedSquare;
        }
        return calculatedRowValue;
    }

    private String calculateHintNumberInSquare(String[][] fieldValues, int rowIndex, int columnIndex) {
        int maxRowIndex = fieldValues.length;
        int maxColumnIndex = fieldValues[0].length;

        List<Integer> adjacentRowIndices =
                MineFieldService.getAdjacentIndices(rowIndex, maxRowIndex, Constants.ADJACENT_RANGE);
        List<Integer> adjacentColumnIndices =
                MineFieldService.getAdjacentIndices(columnIndex, maxColumnIndex, Constants.ADJACENT_RANGE);

        String calculatedSquare = countAdjacentMines(fieldValues, adjacentRowIndices, adjacentColumnIndices);
        return calculatedSquare;
    }

    private String countAdjacentMines(String[][] fieldValues, List<Integer> adjacentRowIndices, List<Integer> adjacentColumnIndices) {
        int numOfMines = 0;

        for (int rowIndex : adjacentRowIndices) {
            for (int columnIndex : adjacentColumnIndices) {
                String adjacentSquare = fieldValues[rowIndex][columnIndex];
                if (adjacentSquare.equals(Constants.MINE_SQUARE)) {
                    numOfMines++;
                }
            }
        }
        return Integer.toString(numOfMines);
    }
}
