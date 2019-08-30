package com.myob.minesweeper.service;

import com.myob.minesweeper.model.MinesweeperBoard;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.output.IOutputService;

import java.util.List;

public class MinesweeperService {

    private IInputService userInputService;
    private IOutputService outputService;
    private MinesweeperGameEngine ruleEngine;

    // TODO: where to inject those dependencies

    public void startGame() {
        List<MinesweeperBoard> inputBoards = userInputService.getValidInput();

        List<MinesweeperBoard> resultBoards = ruleEngine.processBoards(inputBoards);

        outputService.printBoards(resultBoards);
    }
}
