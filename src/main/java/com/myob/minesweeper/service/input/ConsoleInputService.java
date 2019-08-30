package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MinesweeperBoard;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ConsoleInputService implements IInputService {

    private UserInputParser parser;
    private UserInputValidator validator;
    private UserInputConverter converter;

    public ConsoleInputService(UserInputParser inputParser, UserInputValidator inputValidator, UserInputConverter inputConverter) {
        parser = inputParser;
        validator = inputValidator;
        converter = inputConverter;
    }

    @Override
    public List<MinesweeperBoard> getValidInput() {
        List<MinesweeperBoard> validListOfBoards = new ArrayList<>();
        boolean endOfInput = false;

        while (!endOfInput) {
            MinesweeperBoard newEmptyBoard = createNewEmptyBoard();
            if (isBoardDimensionZeroByZero(newEmptyBoard)) {
                endOfInput = true;
            } else {
                MinesweeperBoard newBoardWithMines = plantMinesInNewBoard(newEmptyBoard);
                validListOfBoards.add(newBoardWithMines);
            }
        }
        return validListOfBoards;
    }

    private MinesweeperBoard createNewEmptyBoard() {
        boolean gotNewBoard = false;
        int[] userInputDimensions = new int[]{};

        while (!gotNewBoard) {
            String inputDimensions = parser.readFromConsole();
            boolean validInputFormat = validator.validateInputHavingNumbersWithSpaceAsDelimiter(inputDimensions);

            if (validInputFormat) {
                userInputDimensions = convertUserInputDimension(inputDimensions);
                if (isDimensionWithinRange(userInputDimensions)) {
                    gotNewBoard = true;
                }
            }
        }
        MinesweeperBoard newBoard = new MinesweeperBoard(userInputDimensions);
        return newBoard;
    }

    private int[] convertUserInputDimension(String userInput) {
        String[] splitString = converter.splitStringToArrayOf2Elements(userInput);
        int[] convertedDimensions = converter.convertToNumericalDimensions(splitString);
        return convertedDimensions;
    }

    private boolean isDimensionWithinRange(int[] dimensions) {
        int numRows = dimensions[0];
        int numColumns = dimensions[1];
        boolean valid = validator.validateDimensionsInRange(numRows, numColumns);
        return valid;
    }

    // quite specific to this app so leave it on this level? so if change condition, no touching the core
    private boolean isBoardDimensionZeroByZero(MinesweeperBoard board) {
        return board.getRowDimension() == 0 && board.getColumnDimension() == 0;
    }

    private MinesweeperBoard plantMinesInNewBoard(MinesweeperBoard board) {

        int numRows = board.getRowDimension();
        int numColumns = board.getColumnDimension();
        int inputRowCounter = 0;

        while (inputRowCounter != numRows) {
            String inputRow = parser.readFromConsole();

            if (isInputRowValid(inputRow, numColumns)) {
                String[] arrayOfUserInput = inputRow.split(Constants.EMPTY_STRING);
                board.setRowValue(arrayOfUserInput, inputRowCounter);
                inputRowCounter += 1;
            }
        }
        return board;
    }

    private boolean isInputRowValid(String inputRow, int rowSize) {
        boolean rowContentIsValid = validator.validateRowContent(inputRow);
        boolean rowLengthIsValid = validator.validateLengthOfRowInput(inputRow, rowSize);
        return rowContentIsValid && rowLengthIsValid;
    }
}

