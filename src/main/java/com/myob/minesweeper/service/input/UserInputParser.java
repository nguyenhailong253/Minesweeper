package com.myob.minesweeper.service.input;

import java.util.Scanner;

public class UserInputParser {

    public String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
