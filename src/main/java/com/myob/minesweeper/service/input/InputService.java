package com.myob.minesweeper.service.input;

import com.myob.minesweeper.exception.InvalidRowFormatException;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class InputService implements IInputService {

    private IIOService ioService;

    public InputService(IIOService ioService) {
        this.ioService = ioService;
    }

    // TODO: 16/9/19 is exception handling good here? 
    
    @Override
    public List<MineField> getListOfNewMineFields() {
        List<MineField> validListOfFields = new ArrayList<>();
        boolean endOfInput = false;

        while (!endOfInput) {
            try {
                ioService.displayOutput(Constants.INPUT_DIMENSION_PROMPT);
                int[] newValidDimensions = getNewFieldDimension();
                if (isEndOfInput(newValidDimensions, Constants.END_OF_INPUT_VALUE_SET)) {
                    endOfInput = true;
                } else {
                    int numRows = newValidDimensions[0];
                    int numColumns = newValidDimensions[1];
                    MineField newField = MineFieldService.initialiseNewField(numRows, numColumns);
                    newField = getNewFieldValue(newField);
                    validListOfFields.add(newField);
                }
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private int[] getNewFieldDimension() {
        boolean dimensionIsValid = false;
        int[] userInputDimensions = new int[]{};

        while (!dimensionIsValid) {
            String inputDimensions = ioService.readUserInput();

            dimensionIsValid = isInputDimensionValid(inputDimensions, Constants.FIELD_DIMENSION_PATTERN);
            if (dimensionIsValid) {
                userInputDimensions = convertUserInputToFieldDimensions(inputDimensions);
            } else {
                ioService.displayOutput(Constants.INVALID_INPUT_DIMENSION);
            }
        }
        return userInputDimensions;
    }

    private int[] convertUserInputToFieldDimensions(String userInput) {
        String[] splitInput = UserInputConverter
                .splitStringToArray(userInput, Constants.WHITESPACE_DELIMITER);
        return UserInputConverter.convertStringArrayToIntegerArray(splitInput);
    }

    private MineField getNewFieldValue(MineField field) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int numRowsFilled = 0;

        while (numRowsFilled < field.getRowDimension()) {
            try {
                field = getNewRowContent(field, numRowsFilled);
                numRowsFilled += 1;
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        ioService.displayOutput(Constants.FIELD_CREATED);
        return field;
    }

    private MineField getNewRowContent(MineField field, int rowIndex) {
        String inputRow = ioService.readUserInput();

        if (isRowContentValid(inputRow, Constants.ROW_PATTERN)) {
            field = MineFieldService.setRowOfFieldByIndex(field, rowIndex, inputRow);
            return field;
        }
        throw new InvalidRowFormatException(Constants.INVALID_ROW_FORMAT);
    }

    private boolean isEndOfInput(int[] inputDimensions, int[] endOfInputValues) {
        return UserInputValidator.validateEndOfInputValues(inputDimensions, endOfInputValues);
    }

    private boolean isInputDimensionValid(String inputDimension, String validPattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputDimension, validPattern);
    }

    private boolean isRowContentValid(String inputRow, String pattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputRow, pattern);
    }
}