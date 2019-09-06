package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.utils.Constants;

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
        boolean endOfInput = false;

        while (!endOfInput) {
            try {
                ioService.displayOutput(Constants.INPUT_DIMENSION_PROMPT);
                int[] newValidDimensions = getNewFieldDimension();
                if (UserInputValidator.validateEndOfInputValues(newValidDimensions, Constants.END_OF_INPUT_VALUE_SET)) {
                    endOfInput = true;
                } else {
                    String[][] newValidFieldValue = getValidFieldValue(newValidDimensions);
                    MineField newFieldWithMines = createNewFieldWithMines(newValidDimensions, newValidFieldValue);
                    validListOfFields.add(newFieldWithMines);
                }
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private MineField createNewFieldWithMines(int[] fieldDimensions, String[][] fieldValue) {
        MineField newFieldWithMines = new MineField(fieldDimensions);
        newFieldWithMines.setFieldValue(fieldValue);
        return newFieldWithMines;
    }

    private int[] getNewFieldDimension() {
        String inputDimensions = ioService.readUserInput();
        boolean validInputFormat = UserInputValidator
                .validateStringInputWithRequiredPattern(inputDimensions, Constants.VALID_FIELD_DIMENSION_PATTERN);

        if (validInputFormat) {
            String[] splitInput = UserInputConverter
                    .splitStringToArray(inputDimensions, Constants.WHITESPACE_DELIMITER, Constants.REQUIRED_LENGTH);
            int[] userInputDimensions = UserInputConverter.convertStringToIntegerArray(splitInput);

            if (isDimensionWithinRange(userInputDimensions)) {
                return userInputDimensions;

            } else {
                ioService.displayOutput(Constants.DIMENSION_OUT_OF_RANGE);
            }
        } else {
            ioService.displayOutput(Constants.INVALID_INPUT_DIMENSION);
        }
        return getNewFieldDimension();
    }

    private String[][] getValidFieldValue(int[] fieldDimensions) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int numRows = fieldDimensions[0];
        int numColumns = fieldDimensions[1];
        int inputRowCounter = 0;
        String[][] validFieldContent = new String[numRows][numColumns];

        while (inputRowCounter != numRows) {
            String inputRow = ioService.readUserInput();

            if (isInputRowValid(inputRow, numColumns)) {
                String[] arrayOfUserInput = inputRow.split(Constants.EMPTY_STRING);
                validFieldContent[inputRowCounter] = arrayOfUserInput;
                inputRowCounter += 1;
            } else {
                ioService.displayOutput(Constants.INVALID_ROW_FORMAT);
            }
        }
        ioService.displayOutput(Constants.FIELD_CREATED);
        return validFieldContent;
    }

    private boolean isDimensionWithinRange(int[] dimensions) {
        return UserInputValidator.validateDimensionValuesInRange(dimensions, Constants.MIN_FIELD_SIZE, Constants.MAX_FIELD_SIZE);
    }

    private boolean isInputRowValid(String inputRow, int rowSize) {
        boolean rowContentIsValid = UserInputValidator.validateStringInputWithRequiredPattern(inputRow, Constants.VALID_ROW_PATTERN);
        boolean rowLengthIsValid = UserInputValidator.validateLengthOfRowInput(inputRow, rowSize);
        return rowContentIsValid && rowLengthIsValid;
    }
}