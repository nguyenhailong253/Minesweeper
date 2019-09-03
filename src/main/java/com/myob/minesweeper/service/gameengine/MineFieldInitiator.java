package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;

public class MineFieldInitiator {

    public MineField initialiseField(MineField inputField) {
        int rowDimension = inputField.getRowDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            String[] currentInputRow = inputField.getRowValue(rowIndex);
            String[] initialisedRow = initialiseRow(currentInputRow);
            inputField.setRowValue(initialisedRow, rowIndex);
        }
        return inputField;
    }

    private String[] initialiseRow(String[] currentRow) {
        for (int i = 0; i < currentRow.length; i++) {
            if (currentRow[i] != null && currentRow[i].equals(Constants.SAFE_SQUARE)) {
                currentRow[i] = Constants.ZERO_STRING;
            }
        }
        return currentRow;
    }
}
