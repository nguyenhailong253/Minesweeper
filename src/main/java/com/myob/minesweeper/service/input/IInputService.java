package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MinesweeperBoard;

import java.util.List;

public interface IInputService {
    public List<MinesweeperBoard> getValidInput();
}
