package com.myob.minesweeper.infrastructure.io;

import java.util.Scanner;

public class ConsoleIOService implements IOService {

    @Override
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    @Override
    public void displayOutput(String output) {
        System.out.println(output);
    }
}
