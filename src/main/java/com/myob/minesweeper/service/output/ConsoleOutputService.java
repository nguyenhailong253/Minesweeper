package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MinesweeperBoard;
import com.myob.minesweeper.utils.Constants;

import java.util.List;

public class ConsoleOutputService implements IOutputService {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printBoards(List<MinesweeperBoard> resultBoards) {

        for (int boardIndex = 0; boardIndex < resultBoards.size(); boardIndex++) {
            printBoardLabel(boardIndex);

            MinesweeperBoard currentBoard = resultBoards.get(boardIndex);
            printAllRows(currentBoard);
            
            print(Constants.EMPTY_STRING);
        }
    }

    private void printBoardLabel(int boardIndex) {
        String boardNumber = Integer.toString(boardIndex + 1);
        String boardLabel = "Field #".concat(boardNumber).concat(":");
        print(boardLabel);
    }

    private void printAllRows(MinesweeperBoard board) {
        int numOfRows = board.getRowDimension();

        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            String[] arrayOfCharactersInRow = board.getRowValue(rowIndex);
            String row = String.join(Constants.EMPTY_STRING, arrayOfCharactersInRow);
            print(row);
        }
    }
}
