package com.myob.minesweeper;

import com.myob.minesweeper.service.application.MinesweeperService;
import com.myob.minesweeper.service.calculator.Calculator;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.service.input.InputReader;
import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IOService;
import com.myob.minesweeper.service.result.ResultService;
import com.myob.minesweeper.service.result.ResultWriter;

public class MinesweeperApplication {
    private static IOService consoleIOService = new ConsoleIOService();
    private static InputService inputReader = new InputReader(consoleIOService);
    private static ResultService resultWriter = new ResultWriter(consoleIOService);
    private static Calculator calculator = new MinesweeperCalculator();

    private static MinesweeperService service = new MinesweeperService(inputReader, resultWriter, calculator);

    public static void main(String[] args) {
        service.startGame();
    }
}
