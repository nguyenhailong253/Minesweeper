package com.myob.minesweeper.service.application;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.result.IResultService;

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

        List<MineField> resultFields = MinesweeperCalculator.processAllFields(inputFields);

        resultService.displayResultFields(resultFields);
    }
}
