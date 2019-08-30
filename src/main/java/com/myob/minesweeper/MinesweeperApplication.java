package com.myob.minesweeper;

import com.myob.minesweeper.service.MinesweeperService;

public class MinesweeperApplication {
    private static MinesweeperService service = new MinesweeperService();

    public static void main(String[] args) {
        service.startGame();
    }
}


// TODO: 2019-08-27 complete tests & implementation









//    private UserInputValidator inputValidator;
//    private UserInputConverter inputConverter;
//    private IInputService inputService;
//    private MinesweeperGameEngine gameEngine;
//
//    public MinesweeperApplication(UserInputConverter converter, UserInputValidator validator, IInputService service, MinesweeperGameEngine engine) {
//        inputValidator = validator;
//        inputConverter = converter;
//        inputService = service;
//        gameEngine = engine;
//    }
//
//    public static void main(String[] args) {
//
//        UserInputValidator validator = new UserInputValidator();
//        UserInputConverter converter = new UserInputConverter();
//        IInputService inputService = new ConsoleInputService();
//        MinesweeperGameEngine engine = new MinesweeperGameEngine();
//        IOutputService outputService = new ConsoleOutputService();
//
//        MinesweeperApplication game = new MinesweeperApplication(converter, validator, inputService, engine);
//
//        game.startGame(outputService);
////        game.runGame(outputService);
////        engine.runGame();
//        game.endGame(outputService);
//    }
//
//    public void runGame(IOutputService outputService) {
//        ArrayList<MinesweeperBoard> userInputBoards = processUserInput();
//        ArrayList<MinesweeperBoard> processedBoards = processBoards(userInputBoards);
//        displayProcessedBoards(processedBoards, outputService);
//    }
//
//    public void startGame(IOutputService outputService) {
//        outputService.print(Messages.WELCOME);
//    }
//
//    public void endGame(IOutputService outputService) {
//        outputService.print(Messages.GAME_OVER);
//    }
//
//    private String getUserInput() {
//        return inputService.getUserInput();
//    }
//
//    private ArrayList<MinesweeperBoard> processUserInput() {
//        ArrayList<MinesweeperBoard> userInputBoards = new ArrayList<>();
//
//        boolean endOfInput = false;
//
//        while (!endOfInput) {
//            String userInput = getUserInput();
//            // check if user input correct format of 2 dimensions
//            if (inputValidator.validateInputHavingNumbersWithSpaceBetween(userInput)) {
//
//                if (inputValidator.validateEndOfInput(userInput)) {
//                    endOfInput = true;
//
//                } else {
//                    // split dimensions and convert to int array
//                    String[] splittedInput = inputConverter.splitStringToArrayOf2Elements(userInput);
//                    int[] inputDimensions = inputConverter.convertToNumericalDimensions(splittedInput);
//
//                    // validate int array
//                    if (inputValidator.validateDimensionsInRange(inputDimensions[0], inputDimensions[1])) {
//
//                        // if valid dimensions, create new board based on that dimensions
//                        MinesweeperBoard newBoard = new MinesweeperBoard(inputDimensions);
//                        userInputBoards.add(newBoard);
//
//                        // using given dimensions, ask user to enter corresponding content for each row
//                        int numOfRowInput = newBoard.getRowDimension();
//                        int columnDimension = newBoard.getColumnDimension();
//                        int trackingIndex = 0;
//
//                        // keep asking for row input until all rows are filled
//                        while (trackingIndex < numOfRowInput) {
//                            String rowContentInput = getUserInput();
//                            boolean validRowContent = inputValidator.validateRowContent(rowContentInput);
//                            boolean validContentLength = inputValidator.validateLengthOfRowInput(rowContentInput, columnDimension);
//
//                            // if input contains valid characters & matches length of board, add that row to board
//                            if (validRowContent && validContentLength) {
//                                String[] rowContent = rowContentInput.split("");
//                                newBoard.setRowContent(rowContent, trackingIndex);
//                                trackingIndex += 1;
//                            } else {
//                                // continue to ask for new input;
//                                continue;
//                            }
//                        }
//                    } else {
//                        // ask to enter dimension again
//                        continue;
//                    }
//                }
//            }
//        }
//
//        return userInputBoards;
//    }
//
//    private ArrayList<MinesweeperBoard> processBoards(ArrayList<MinesweeperBoard> userInputBoards) {
//        ArrayList<MinesweeperBoard> boards = new ArrayList<>();
//
//        for (int boardIndex = 0; boardIndex < userInputBoards.size(); boardIndex++) {
//            MinesweeperBoard processedBoard = gameEngine.initialiseBoard(userInputBoards.get(boardIndex));
//            processedBoard = gameEngine.processMineField(processedBoard);
//            boards.add(processedBoard);
//        }
//        return boards;
//    }
//
//    private void displayProcessedBoards(ArrayList<MinesweeperBoard> processedBoards, IOutputService outputService) {
//        for (int boardIndex = 0; boardIndex < processedBoards.size(); boardIndex++) {
//            outputService.print(Integer.toString(boardIndex + 1));
//            String[][] boardContent = processedBoards.get(boardIndex).getBoardContent();
//            for (String[] rowContent : boardContent) {
//                outputService.print(Arrays.toString(rowContent));
//            }
//        }
//    }