package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ConsoleInputService implements IInputService {

    private IUserInputParser parser;
    private IUserInputValidator validator;
    private IUserInputConverter converter;

    public ConsoleInputService(IUserInputParser inputParser, IUserInputValidator inputValidator, IUserInputConverter inputConverter) {
        parser = inputParser;
        validator = inputValidator;
        converter = inputConverter;
    }

    @Override
    public List<MineField> getValidInput() {
        List<MineField> validListOfFields = new ArrayList<>();
        boolean endOfInput = false;

        while (!endOfInput) {
            try {
                MineField newEmptyField = createNewEmptyField();
                if (isFieldDimensionZeroByZero(newEmptyField)) {
                    endOfInput = true;
                } else {
                    MineField newFieldWithMines = plantMinesInNewField(newEmptyField);
                    validListOfFields.add(newFieldWithMines);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return validListOfFields;
    }

    private MineField createNewEmptyField() {
        boolean gotNewField = false;
        int[] userInputDimensions = new int[]{};
        System.out.println(Constants.INPUT_DIMENSION_PROMPT);

        while (!gotNewField) {
            String inputDimensions = parser.readUserInput();
            boolean validInputFormat = validator.validateInputDimensionFormat(inputDimensions);

            if (validInputFormat) {
                userInputDimensions = converter.convertToNumericalDimensions(inputDimensions);
                if (isDimensionWithinRange(userInputDimensions)) {
                    gotNewField = true;
                } else {
                    System.out.println(Constants.DIMENSION_OUT_OF_RANGE);
                }
            } else {
                System.out.println(Constants.INVALID_DIMENSION_FORMAT);
            }
        }
        return new MineField(userInputDimensions);
    }

    private MineField plantMinesInNewField(MineField field) {

        int numRows = field.getRowDimension();
        int numColumns = field.getColumnDimension();
        int inputRowCounter = 0;
        System.out.println(Constants.PLANT_MINE_PROMPT);

        while (inputRowCounter != numRows) {
            String inputRow = parser.readUserInput();

            if (isInputRowValid(inputRow, numColumns)) {
                String[] arrayOfUserInput = inputRow.split(Constants.EMPTY_STRING);
                field.setRowValue(arrayOfUserInput, inputRowCounter);
                inputRowCounter += 1;
            } else {
                System.out.println(Constants.INVALID_ROW_FORMAT);
            }
        }
        System.out.println(Constants.FIELD_CREATED);
        return field;
    }

    private boolean isDimensionWithinRange(int[] dimensions) {
        int numRows = dimensions[0];
        int numColumns = dimensions[1];
        return validator.validateDimensionsInRange(numRows, numColumns);
    }

    private boolean isFieldDimensionZeroByZero(MineField field) {
        return field.getRowDimension() == 0 && field.getColumnDimension() == 0;
    }

    private boolean isInputRowValid(String inputRow, int rowSize) {
        boolean rowContentIsValid = validator.validateRowContent(inputRow);
        boolean rowLengthIsValid = validator.validateLengthOfRowInput(inputRow, rowSize);
        return rowContentIsValid && rowLengthIsValid;
    }
}

