package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidInputDimensionPattern;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.utils.Constants;
import com.myob.minesweeper.utils.UserInputConverter;

import java.util.ArrayList;
import java.util.List;

public class InputService implements IInputService {

    private IIOService ioService;

    public InputService(IIOService ioService) {
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
                MineField newField = constructNewMineField(userInputDimension);
                updateFieldValuesWithUserInput(newField);

                validListOfFields.add(newField);
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private MineField constructNewMineField(String userInputDimension) {
        if (!userInputDimension.matches(Constants.FIELD_DIMENSION_PATTERN)) {
            throw new InvalidInputDimensionPattern(Constants.INVALID_INPUT_DIMENSION);
        }

        int[] fieldDimensions = UserInputConverter.convertStringToIntegerArray(userInputDimension, Constants.WHITESPACE_DELIMITER);
        int numRows = fieldDimensions[0];
        int numColumns = fieldDimensions[1];

        return MineFieldService.constructNewField(numRows, numColumns);
    }

    private void updateFieldValuesWithUserInput(MineField field) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int rowIndex = 0;
        int numRows = field.getRowDimension();

        while (rowIndex < numRows) {
            try {
                String inputRow = ioService.readUserInput();
                MineFieldService.updateRowValue(field, inputRow, rowIndex);
                rowIndex++;
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        MineFieldService.updateFieldState(field, MineFieldState.INITIALISED);
        ioService.displayOutput(Constants.FIELD_CREATED);
    }
}