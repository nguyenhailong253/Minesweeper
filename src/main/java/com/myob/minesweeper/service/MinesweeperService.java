package com.myob.minesweeper.service;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.gameengine.AdjacentMinesCalculator;
import com.myob.minesweeper.service.gameengine.MineFieldInitiator;
import com.myob.minesweeper.service.gameengine.MinesweeperGameEngine;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.output.IOutputService;

import java.util.List;

public class MinesweeperService {

    private IInputService inputService;
    private IOutputService outputService;
    private MinesweeperGameEngine gameEngine;

    public MinesweeperService(IInputService inputService, IOutputService outputService) {
        MineFieldInitiator initiator = new MineFieldInitiator();
        AdjacentMinesCalculator calculator = new AdjacentMinesCalculator();
        gameEngine = new MinesweeperGameEngine(initiator, calculator);

        this.inputService = inputService;
        this.outputService = outputService;
    }

    public void startGame() {
        List<MineField> inputFields = inputService.getValidInput();

        List<MineField> resultFields = gameEngine.processAllFields(inputFields);

        outputService.printResultFields(resultFields);
    }
}
