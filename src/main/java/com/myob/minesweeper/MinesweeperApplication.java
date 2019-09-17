package com.myob.minesweeper;

import com.myob.minesweeper.service.application.MinesweeperService;
import com.myob.minesweeper.service.calculator.IMinesweeperCalculator;
import com.myob.minesweeper.service.calculator.MinesweeperCalculator;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.service.result.IResultService;
import com.myob.minesweeper.service.result.ResultService;

public class MinesweeperApplication {
    private static IIOService consoleIOService = new ConsoleIOService();
    private static IInputService inputService = new InputService(consoleIOService);
    private static IResultService resultService = new ResultService(consoleIOService);
    private static IMinesweeperCalculator calculator = new MinesweeperCalculator();

    private static MinesweeperService service = new MinesweeperService(inputService, resultService, calculator);

    public static void main(String[] args) {
        service.startGame();
    }
}
