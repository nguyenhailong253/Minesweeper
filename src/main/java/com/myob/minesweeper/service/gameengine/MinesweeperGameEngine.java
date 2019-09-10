package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
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

    private static MineField processSingleField(MineField field) {
        field.initialiseField();

        int rowDimension = field.getRowDimension();
        int columnDimension = field.getColumnDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                String currentSquareValue = field.getSquareValue(rowIndex, columnIndex);

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    String processedSquare = AdjacentMinesCalculator.calculateAdjacentMines(field, rowIndex, columnIndex);
                    field.setSquareValue(processedSquare, rowIndex, columnIndex);
                }
            }
        }
        return field;
    }
}
