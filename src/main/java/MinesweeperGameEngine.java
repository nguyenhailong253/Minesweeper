import com.sun.org.apache.bcel.internal.Const;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperGameEngine {

    private String[] initialiseRow(MinesweeperBoard inputBoard, int rowIndex) {
        String[] row = inputBoard.getRowContent(rowIndex);
        for (int i = 0; i < row.length; i++) {
            if (row[i] == Constants.SAFE_SQUARE) {
                row[i] = new String("0");
            }
        }
        return row;
    }

    public MinesweeperBoard initialiseBoard(MinesweeperBoard inputBoard) {
        int rowDimension = inputBoard.getRowDimension();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            String[] initialisedRow = initialiseRow(inputBoard, rowIndex);
            inputBoard.setRowContent(initialisedRow, rowIndex);
        }
        return inputBoard;
    }

    private ArrayList<Integer> getIndicesOfAdjacentRows(int currentRowIndex, int rowDimension) {
        ArrayList<Integer> indicesOfRowNeighbours = new ArrayList<>();
        indicesOfRowNeighbours.add(currentRowIndex);
        if (currentRowIndex - 1 >= 0) {
            indicesOfRowNeighbours.add(currentRowIndex - 1);
        }
        if (currentRowIndex + 1 < rowDimension) {
            indicesOfRowNeighbours.add(currentRowIndex + 1);
        }
        return indicesOfRowNeighbours;
    }

    private ArrayList<Integer> getIndicesOfAdjacentColumns(int currentColumnIndex, int columnDimension) {
        ArrayList<Integer> indicesOfColumnNeighbours = new ArrayList<>();
        indicesOfColumnNeighbours.add(currentColumnIndex);
        if (currentColumnIndex - 1 >= 0) {
            indicesOfColumnNeighbours.add(currentColumnIndex - 1);
        }
        if (currentColumnIndex + 1 < columnDimension) {
            indicesOfColumnNeighbours.add(currentColumnIndex + 1);
        }
        return indicesOfColumnNeighbours;
    }

    private String calculateNumOfAdjacentMines(String[][] board, ArrayList<Integer> neighbourRows, ArrayList<Integer> neighbourColumns) {
        int numOfMines = 0;

        for (int rowIndex: neighbourRows) {
            for (int colIndex: neighbourColumns) {
                if (board[rowIndex][colIndex] == Constants.MINE_SQUARE) {
                    numOfMines += 1;
                }
            }
        }
        return Integer.toString(numOfMines);
    }

    private String processSingleSquare(MinesweeperBoard board, int rowIndex, int columnIndex) {
        int rowDimension = board.getRowDimension();
        int columnDimension = board.getColumnDimension();

        ArrayList<Integer> adjacentRows = getIndicesOfAdjacentRows(rowIndex, rowDimension);
        ArrayList<Integer> adjacentColumns = getIndicesOfAdjacentColumns(columnIndex, columnDimension);
        String processedSquare = calculateNumOfAdjacentMines(board.getBoardContent(), adjacentRows, adjacentColumns);
        return processedSquare;
    }

    public MinesweeperBoard processMineField(MinesweeperBoard board) {
        int rowDimension = board.getRowDimension();
        int columnDimension = board.getColumnDimension();
        String[][] boardContent = board.getBoardContent();

        for (int rowIndex = 0; rowIndex < rowDimension; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnDimension; columnIndex++) {
                if (boardContent[rowIndex][columnIndex] != Constants.MINE_SQUARE) {
                    String processedSquare = processSingleSquare(board, rowIndex, columnIndex);
                    board.setSquareContent(processedSquare, rowIndex, columnIndex);
                }
            }
        }

        return board;
    }
}
