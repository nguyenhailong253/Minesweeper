package com.myob.minesweeper;

import com.myob.minesweeper.service.MinesweeperService;
import com.myob.minesweeper.service.input.*;
import com.myob.minesweeper.service.output.ConsoleOutputService;
import com.myob.minesweeper.service.output.IOutputService;

public class MinesweeperApplication {
    private static IUserInputParser parser = new ConsoleUserInputParser();
    private static IUserInputValidator validator = new ConsoleUserInputValidator();
    private static IUserInputConverter converter = new ConsoleUserInputConverter();
    private static IInputService inputService = new ConsoleInputService(parser, validator, converter);
    private static IOutputService outputService = new ConsoleOutputService();

    private static MinesweeperService service = new MinesweeperService(inputService, outputService);

    public static void main(String[] args) {
        service.startGame();
    }
}
