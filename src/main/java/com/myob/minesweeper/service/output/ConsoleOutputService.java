package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;

import java.util.List;

public class ConsoleOutputService implements IOutputService {

    private void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printResultFields(List<MineField> resultFields) {

        for (int fieldIndex = 0; fieldIndex < resultFields.size(); fieldIndex++) {
            printFieldLabel(fieldIndex);

            MineField currentField = resultFields.get(fieldIndex);
            printAllRows(currentField);
            
            print(Constants.EMPTY_STRING);
        }
    }

    private void printFieldLabel(int fieldIndex) {
        String fieldNumber = Integer.toString(fieldIndex + 1);
        String fieldLabel = Constants.LABEL.concat(fieldNumber).concat(":");
        print(fieldLabel);
    }

    private void printAllRows(MineField field) {
        int numOfRows = field.getRowDimension();

        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            String[] arrayOfStringsInRow = field.getRowValue(rowIndex);
            String row = String.join(Constants.EMPTY_STRING, arrayOfStringsInRow);
            print(row);
        }
    }
}
