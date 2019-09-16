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
                    MineField newField = constructNewMineField(newValidDimensions);
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
            String userInput = ioService.readUserInput();

            dimensionIsValid = isInputDimensionValid(userInput, Constants.FIELD_DIMENSION_PATTERN);
            if (dimensionIsValid) {
                userInputDimensions = convertUserInputToFieldDimensions(userInput);
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

    private MineField constructNewMineField(int[] fieldDimensions) {
        int numRows = fieldDimensions[0];
        int numColumns = fieldDimensions[1];

        MineField newField = MineFieldService.initialiseNewField(numRows, numColumns);
        String[][] newFieldValues = getNewFieldValues(numRows, numColumns);

        MineFieldService.updateFieldValues(newField, newFieldValues);
        return newField;
    }

    private String[][] getNewFieldValues(int numRows, int numColumns) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int numRowsFilled = 0;
        String[][] newFieldValues = new String[numRows][numColumns];

        while (numRowsFilled < numRows) {
            try {
                newFieldValues[numRowsFilled] = getNewRowContent(numColumns);
                numRowsFilled += 1;
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        ioService.displayOutput(Constants.FIELD_CREATED);
        return newFieldValues;
    }

    private String[] getNewRowContent(int rowLength) {
        String inputRow = ioService.readUserInput();

        if (isRowContentValid(inputRow, Constants.ROW_PATTERN)
                && isRowLengthValid(inputRow, rowLength)) {
            return inputRow.split(Constants.EMPTY_STRING);
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

    private boolean isRowLengthValid(String inputRow, int rowLength) {
        return UserInputValidator.validateLengthOfRowInput(inputRow, rowLength);
    }
}