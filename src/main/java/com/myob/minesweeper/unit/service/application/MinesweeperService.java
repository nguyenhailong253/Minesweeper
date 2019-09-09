package com.myob.minesweeper.unit.service.application;

import com.myob.minesweeper.unit.model.MineField;
import com.myob.minesweeper.unit.service.gameengine.MinesweeperGameEngine;
import com.myob.minesweeper.unit.service.input.IInputService;
import com.myob.minesweeper.unit.service.result.IResultService;

import java.util.List;

public class MinesweeperService {

    private IInputService inputService;
    private IResultService resultService;

    public MinesweeperService(IInputService inputService, IResultService resultService) {
        this.inputService = inputService;
        this.resultService = resultService;
    }

    public void startGame() {
        List<MineField> inputFields = inputService.getListOfNewMineFields();

        List<MineField> resultFields = MinesweeperGameEngine.processAllFields(inputFields);

        resultService.displayResultFields(resultFields);
    }
}
