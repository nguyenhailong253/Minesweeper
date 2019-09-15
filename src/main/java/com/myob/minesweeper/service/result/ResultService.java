package com.myob.minesweeper.service.result;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.utils.Constants;

import java.util.List;

public class ResultService implements IResultService {

    private IIOService ioService;

    public ResultService(IIOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void displayResultFields(List<MineField> resultFields) {

        ioService.displayOutput(Constants.RESULTS_CALCULATED);
        for (int fieldIndex = 0; fieldIndex < resultFields.size(); fieldIndex++) {
            displayFieldLabel(fieldIndex);

            MineField currentField = resultFields.get(fieldIndex);
            displayAllRows(currentField);
            
            ioService.displayOutput(Constants.EMPTY_STRING);
        }
    }

    private void displayFieldLabel(int fieldIndex) {
        String fieldNumber = Integer.toString(fieldIndex + 1);
        String fieldLabel = Constants.LABEL.concat(fieldNumber).concat(":");
        ioService.displayOutput(fieldLabel);
    }

    private void displayAllRows(MineField field) {
        int numOfRows = field.getRowDimension();

        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            String[] arrayOfStringsInRow = MineFieldService.getRowOfFieldByIndex(field, rowIndex);
            String row = String.join(Constants.EMPTY_STRING, arrayOfStringsInRow);
            ioService.displayOutput(row);
        }
    }
}
