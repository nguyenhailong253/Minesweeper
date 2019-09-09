package com.myob.minesweeper.unit.service.gameengine;

import com.myob.minesweeper.unit.model.MineField;
import com.myob.minesweeper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AdjacentMinesCalculator {

    public static String calculateAdjacentMines(MineField field, int rowIndex, int columnIndex) {
        int numOfMines = 0;

        List<Integer> adjacentRows = getAdjacentIndices(rowIndex, field.getRowDimension());
        List<Integer> adjacentColumns = getAdjacentIndices(columnIndex, field.getColumnDimension());

        for (int row : adjacentRows) {
            for (int column : adjacentColumns) {
                String adjacentSquare = field.getSquareValue(row, column);
                if (adjacentSquare.equals(Constants.MINE_SQUARE)) {
                    numOfMines += 1;
                }
            }
        }
        return Integer.toString(numOfMines);
    }

    private static List<Integer> getAdjacentIndices(int currentIndex, int maxDimension) {
        List<Integer> adjacentIndices = new ArrayList<>();
        adjacentIndices.add(currentIndex);
        if (currentIndex - 1 >= 0) {
            adjacentIndices.add(currentIndex - 1);
        }
        if (currentIndex + 1 < maxDimension) {
            adjacentIndices.add(currentIndex + 1);
        }
        return adjacentIndices;
    }
}
