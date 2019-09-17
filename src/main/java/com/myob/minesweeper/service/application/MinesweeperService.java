package com.myob.minesweeper.service.application;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.calculator.IMinesweeperCalculator;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.result.IResultService;

import java.util.List;

public class MinesweeperService {

    private IInputService inputService;
    private IResultService resultService;
    private IMinesweeperCalculator calculator;

    public MinesweeperService(IInputService inputService, IResultService resultService, IMinesweeperCalculator calculator) {
        this.inputService = inputService;
        this.resultService = resultService;
        this.calculator = calculator;
    }

    public void startGame() {
        List<MineField> inputFields = inputService.getListOfNewMineFields();

        List<MineField> resultFields = calculator.calculateHintNumbersInFields(inputFields);

        resultService.displayResultFields(resultFields);
    }
}
