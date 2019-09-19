package com.myob.minesweeper.service.application;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.service.calculator.Calculator;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.service.result.ResultService;

import java.util.List;

public class MinesweeperService {

    private InputService inputReader;
    private ResultService resultWriter;
    private Calculator calculator;

    public MinesweeperService(InputService inputReader, ResultService resultWriter, Calculator calculator) {
        this.inputReader = inputReader;
        this.resultWriter = resultWriter;
        this.calculator = calculator;
    }

    public void startGame() {
        List<MineField> inputFields = inputReader.getListOfNewMineFields();

        List<MineField> resultFields = calculator.calculateHintNumbersInFields(inputFields);

        resultWriter.displayResultFields(resultFields);
    }
}
