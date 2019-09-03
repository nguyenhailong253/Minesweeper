package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGameEngine {

    private MineFieldInitiator initiator;
    private AdjacentMinesCalculator calculator;

    public MinesweeperGameEngine(MineFieldInitiator fieldInitiator, AdjacentMinesCalculator minesCalculator) {
        initiator = fieldInitiator;
        calculator = minesCalculator;
    }

    public List<MineField> processAllFields(List<MineField> inputFields) {

        List<MineField> resultFields = new ArrayList<>();

        for (MineField field: inputFields) {
            resultFields.add(processSingleField(field));
        }
        System.out.println(Constants.RESULT_FIELD_CALCULATED);
        return resultFields;
    }

    private MineField processSingleField(MineField inputField) {
        MineField resultField = initiator.initialiseField(inputField);

        int rowDimension = resultField.getRowDimension();
        int columnDimension = resultField.getColumnDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                String currentSquareValue = resultField.getSquareValue(rowIndex, columnIndex);

                if (!currentSquareValue.equals(Constants.MINE_SQUARE)) {
                    String processedSquare = calculator.calculateAdjacentMines(inputField, rowIndex, columnIndex);
                    resultField.setSquareValue(processedSquare, rowIndex, columnIndex);
                }
            }
        }
        return resultField;
    }
}
