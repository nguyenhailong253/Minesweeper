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
                if (isEndOfInput(newValidDimensions, Constants.END_OF_INPUT_VALUE_SET)) {
                    endOfInput = true;
                } else {
                    String[][] newValidFieldValue = getNewFieldValue(newValidDimensions);
                    MineField newFieldWithMines = createNewFieldWithMines(newValidDimensions, newValidFieldValue);
                    validListOfFields.add(newFieldWithMines);
                }
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private int[] getNewFieldDimension() {
        String inputDimensions = ioService.readUserInput();

        if (isInputDimensionValid(inputDimensions, Constants.FIELD_DIMENSION_PATTERN)) {
            String[] splitInput = UserInputConverter
                    .splitStringToArray(inputDimensions, Constants.WHITESPACE_DELIMITER, Constants.REQUIRED_LENGTH);
            int[] userInputDimensions = UserInputConverter.convertStringToIntegerArray(splitInput);

            if (isDimensionWithinRange(userInputDimensions, Constants.MIN_SIZE, Constants.MAX_SIZE)) {
                return userInputDimensions;

            } else {
                ioService.displayOutput(Constants.DIMENSION_OUT_OF_RANGE);
            }
        } else {
            ioService.displayOutput(Constants.INVALID_INPUT_DIMENSION);
        }
        return getNewFieldDimension();
    }

    private String[][] getNewFieldValue(int[] fieldDimensions) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        int numRows = fieldDimensions[0];
        int numColumns = fieldDimensions[1];
        String[][] validFieldValue = new String[numRows][numColumns];

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            validFieldValue[rowIndex] = getValidRowValue(numColumns);
        }
        ioService.displayOutput(Constants.FIELD_CREATED);
        return validFieldValue;
    }

    private String[] getValidRowValue(int rowLength) {
        String inputRow = ioService.readUserInput();

        if (isRowContentValid(inputRow, Constants.ROW_PATTERN) && isRowLengthValid(inputRow, rowLength)) {
            return inputRow.split(Constants.EMPTY_STRING);
        }
        ioService.displayOutput(Constants.INVALID_ROW_FORMAT);
        return getValidRowValue(rowLength);
    }

    private MineField createNewFieldWithMines(int[] fieldDimensions, String[][] fieldValue) {
        MineField newFieldWithMines = new MineField(fieldDimensions);
        newFieldWithMines.setFieldValue(fieldValue);
        return newFieldWithMines;
    }

    private boolean isEndOfInput(int[] inputDimensions, int[] endOfInputValues) {
        return UserInputValidator.validateEndOfInputValues(inputDimensions, endOfInputValues);
    }

    private boolean isInputDimensionValid(String inputDimension, String validPattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputDimension, validPattern);
    }

    private boolean isDimensionWithinRange(int[] dimensions, int min, int max) {
        return UserInputValidator.validateDimensionValuesInRange(dimensions, min, max);
    }

    private boolean isRowContentValid(String inputRow, String pattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputRow, pattern);
    }

    private boolean isRowLengthValid(String inputRow, int rowLength) {
        return UserInputValidator.validateLengthOfRowInput(inputRow, rowLength);
    }
}