package com.myob.minesweeper.unit.service.gameengine;

import com.myob.minesweeper.unit.model.MineField;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGameEngine {

    public static List<MineField> processAllFields(List<MineField> inputFields) {

        List<MineField> resultFields = new ArrayList<>();

        for (MineField field: inputFields) {
            resultFields.add(processSingleField(field));
        }
        return resultFields;
    }

    private static MineField processSingleField(MineField inputField) {
        MineField resultField = MineFieldInitiator.initialiseField(inputField);

        int rowDimension = resultField.getRowDimension();
        int columnDimension = resultField.getColumnDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                String currentSquareValue = resultField.getSquareValue(rowIndex, columnIndex);

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    String processedSquare = AdjacentMinesCalculator.calculateAdjacentMines(inputField, rowIndex, columnIndex);
                    resultField.setSquareValue(processedSquare, rowIndex, columnIndex);
                }
            }
        }
        return resultField;
    }
}
