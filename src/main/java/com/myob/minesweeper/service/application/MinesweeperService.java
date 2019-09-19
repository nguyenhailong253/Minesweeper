package com.myob.minesweeper.service.application;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.calculator.Calculator;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.service.result.ResultService;

import java.util.List;

public class MinesweeperService {

    private InputService inputService;
    private ResultService resultService;
    private Calculator calculator;

    public MinesweeperService(InputService inputService, ResultService resultService, Calculator calculator) {
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
