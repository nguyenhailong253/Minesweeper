package com.myob.minesweeper.service.input;

import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.model.MineField;
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
                    int numRows = newValidDimensions[0];
                    int numColumns = newValidDimensions[1];
                    String[][] newValidFieldValue = getNewFieldValue(numRows, numColumns);
                    validListOfFields.add(new MineField(numRows, numColumns, newValidFieldValue));
                }
            } catch (Exception e) {
                ioService.displayOutput(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private int[] getNewFieldDimension() {
        boolean isValidDimension = false;
        int[] userInputDimensions = new int[]{};

        while (!isValidDimension) {
            String inputDimensions = ioService.readUserInput();

            if (isInputDimensionValid(inputDimensions, Constants.FIELD_DIMENSION_PATTERN)) {
                String[] splitInput = UserInputConverter
                        .splitStringToArray(inputDimensions, Constants.WHITESPACE_DELIMITER);

                userInputDimensions = UserInputConverter.convertStringArrayToIntegerArray(splitInput);

                isValidDimension = isDimensionWithinRange(userInputDimensions, Constants.MIN_SIZE, Constants.MAX_SIZE);

                if (!isValidDimension) {
                    ioService.displayOutput(Constants.DIMENSION_OUT_OF_RANGE);
                }
            } else {
                ioService.displayOutput(Constants.INVALID_INPUT_DIMENSION);
            }
        }

        return userInputDimensions;
    }

    private String[][] getNewFieldValue(int numRows, int numColumns) {
        ioService.displayOutput(Constants.PLANT_MINE_PROMPT);

        String[][] validFieldValue = new String[numRows][numColumns];

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            validFieldValue[rowIndex] = getValidRowValue(numColumns);
        }

        ioService.displayOutput(Constants.FIELD_CREATED);
        return validFieldValue;
    }

    private String[] getValidRowValue(int rowLength) {
        boolean isRowValid = false;
        String[] rowValues = new String[rowLength];

        while (!isRowValid) {
            String inputRow = ioService.readUserInput();
            isRowValid = isRowContentValid(inputRow, Constants.ROW_PATTERN)
                    && isRowLengthValid(inputRow, rowLength);

            if (isRowValid) {
                rowValues = inputRow.split(Constants.EMPTY_STRING);
            } else {
                ioService.displayOutput(Constants.INVALID_ROW_FORMAT);
            }
        }
        return rowValues;
    }

    private boolean isEndOfInput(int[] inputDimensions, int[] endOfInputValues) {
        return MineFieldValidator.validateEndOfInputValues(inputDimensions, endOfInputValues);
    }

    private boolean isInputDimensionValid(String inputDimension, String validPattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputDimension, validPattern);
    }

    private boolean isDimensionWithinRange(int[] dimensions, int min, int max) {
        return MineFieldValidator.validateDimensionValuesInRange(dimensions, min, max);
    }

    private boolean isRowContentValid(String inputRow, String pattern) {
        return UserInputValidator.validateStringInputWithRequiredPattern(inputRow, pattern);
    }

    private boolean isRowLengthValid(String inputRow, int rowLength) {
        return MineFieldValidator.validateLengthOfRowInput(inputRow, rowLength);
    }
}