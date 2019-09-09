package com.myob.minesweeper.unit.service.gameengine;

import com.myob.minesweeper.unit.model.MineField;
import com.myob.minesweeper.utils.Constants;

public class MineFieldInitiator {

    public static MineField initialiseField(MineField inputField) {
        int rowDimension = inputField.getRowDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            String[] currentInputRow = inputField.getRowValue(rowIndex);
            String[] initialisedRow = initialiseRow(currentInputRow);
            inputField.setRowValue(initialisedRow, rowIndex);
        }
        return inputField;
    }

    private static String[] initialiseRow(String[] currentRow) {
        for (int i = 0; i < currentRow.length; i++) {
            if (currentRow[i] != null && currentRow[i].equals(Constants.SAFE_SQUARE)) {
                currentRow[i] = Constants.ZERO_STRING;
            }
        }
        return currentRow;
    }
}
