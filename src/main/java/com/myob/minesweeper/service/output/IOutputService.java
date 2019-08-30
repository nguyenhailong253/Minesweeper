package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MinesweeperBoard;

import java.util.List;

public interface IOutputService {
    public void print(String message);

    public void printBoards(List<MinesweeperBoard> resultBoards);
}
