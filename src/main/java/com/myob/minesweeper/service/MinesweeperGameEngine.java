package com.myob.minesweeper.service;

import com.myob.minesweeper.model.MinesweeperBoard;

import java.util.List;

public class MinesweeperGameEngine {

    public List<MinesweeperBoard> processBoards(List<MinesweeperBoard> inputBoards) {
        return null;
    }

//    private String[] initialiseRow(MinesweeperBoard inputBoard, int rowIndex) {
//        String[] row = inputBoard.getRowContent(rowIndex);
//        for (int i = 0; i < row.length; i++) {
//            if (row[i] == Constants.SAFE_SQUARE) {
//                row[i] = "0";
//            }
//        }
//        return row;
//    }
//
//    public MinesweeperBoard initialiseBoard(MinesweeperBoard inputBoard) {
//        int rowDimension = inputBoard.getRowDimension();
//
//        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
//            String[] initialisedRow = initialiseRow(inputBoard, rowIndex);
//            inputBoard.setRowContent(initialisedRow, rowIndex);
//        }
//        return inputBoard;
//    }
//
//    private ArrayList<Integer> getAdjacentIndices(int currentIndex, int maxDimension) {
//        ArrayList<Integer> indicesOfNeighbours = new ArrayList<>();
//        indicesOfNeighbours.add(currentIndex);
//        if (currentIndex - 1 >= 0) {
//            indicesOfNeighbours.add(currentIndex - 1);
//        }
//        if (currentIndex + 1 < maxDimension) {
//            indicesOfNeighbours.add(currentIndex + 1);
//        }
//        return indicesOfNeighbours;
//    }
//
//    private String calculateNumOfAdjacentMines(MinesweeperBoard board, ArrayList<Integer> neighbourRows, ArrayList<Integer> neighbourColumns) {
//        int numOfMines = 0;
//
//        for (int rowIndex : neighbourRows) {
//            for (int colIndex : neighbourColumns) {
//                if (board.getSquareContent(rowIndex, colIndex) == Constants.MINE_SQUARE) {
//                    numOfMines += 1;
//                }
//            }
//        }
//        return Integer.toString(numOfMines);
//    }
//
//    private String processSingleSquare(MinesweeperBoard board, int rowIndex, int columnIndex) {
//        int rowDimension = board.getRowDimension();
//        int columnDimension = board.getColumnDimension();
//
//        ArrayList<Integer> adjacentRows = getAdjacentIndices(rowIndex, rowDimension);
//        ArrayList<Integer> adjacentColumns = getAdjacentIndices(columnIndex, columnDimension);
//        String processedSquare = calculateNumOfAdjacentMines(board, adjacentRows, adjacentColumns);
//        return processedSquare;
//    }
//
//    public MinesweeperBoard processMineField(MinesweeperBoard board) {
//        int rowDimension = board.getRowDimension();
//        int columnDimension = board.getColumnDimension();
//        String[][] boardContent = board.getBoardContent();
//
//        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
//            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
//                if (boardContent[rowIndex][columnIndex] != Constants.MINE_SQUARE) {
//                    String processedSquare = processSingleSquare(board, rowIndex, columnIndex);
//                    board.setSquareContent(processedSquare, rowIndex, columnIndex);
//                }
//            }
//        }
//
//        return board;
//    }
}
