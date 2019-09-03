package com.myob.minesweeper.service.input;

import java.util.Scanner;

public class ConsoleUserInputParser implements IUserInputParser {

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
