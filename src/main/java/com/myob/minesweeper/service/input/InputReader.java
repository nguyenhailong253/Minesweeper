package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidInputDimensionPattern;
import com.myob.minesweeper.infrastructure.io.IOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.utils.Constants;
import com.myob.minesweeper.utils.UserInputConverter;

import java.util.ArrayList;
import java.util.List;

public class InputReader implements InputService {

    private IOService ioService;

    public InputReader(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public List<MineField> getListOfNewMineFields() {
        List<MineField> validListOfFields = new ArrayList<>();

        while (true) {
            try {
                ioService.displayOutput(Constants.INPUT_DIMENSION_PROMPT);
                String userInputDimension = ioService.readUserInput();

                if (userInputDimension.matches(Constants.END_OF_INPUT_STRING)) {
                    break;
                }
                MineField newMineField = constructMineField(userInputDimension);
                updateFieldValuesWithUserInput(newMineField);

                validListOfFields.add(newMineField);
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private MineField constructMineField(String userInputDimension) {
        if (!userInputDimension.matches(Constants.FIELD_DIMENSION_PATTERN)) {
            throw new InvalidInputDimensionPattern(Constants.INVALID_INPUT_DIMENSION);
        }

        int[] fieldDimensions = UserInputConverter.convertStringToIntegerArray(userInputDimension, Constants.WHITESPACE_DELIMITER);
        int numRows = fieldDimensions[0];
        int numColumns = fieldDimensions[1];

        return MineFieldService.constructMineField(numRows, numColumns);
    }

    private void updateFieldValuesWithUserInput(MineField mineField) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int rowIndex = 0;
        int numRows = mineField.getRowDimension();

        while (rowIndex < numRows) {
            try {
                String inputRow = ioService.readUserInput();
                MineFieldService.updateRowValue(mineField, inputRow, rowIndex);
                rowIndex++;
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        MineFieldService.updateFieldState(mineField, MineFieldState.VALIDATED);
        ioService.displayOutput(Constants.FIELD_CREATED);
    }
}