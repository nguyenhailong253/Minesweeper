package com.myob.minesweeper.utils;

public class Constants {

    // Exception messages
    public static final String STRING_TO_NUMBER_EXCEPTION = "Invalid numbers";
    public static final String WRONG_NUMBER_OF_DIMENSIONS = "Invalid number of dimensions";
    public static final String TOO_MANY_CHARACTERS_IN_A_ROW = "Invalid number of characters";

    public static final String WELCOME = "Welcome!";
    public static final String GAME_OVER = "Game Over";
    public static final String VALID_BOARD_DIMENSION_PATTERN = "[0-9]{1,}\\s[0-9]{1,}";
    public static final String VALID_ROW_PATTERN = "[\\*\\.]+";
    public static final String SINGLE_SPACE_PATTERN = "\\s";
    public static final String MINE_SQUARE = "*";
    public static final String SAFE_SQUARE = ".";
    public static final String END_OF_INPUT_PATTERN = "0 0";
    public static final String EMPTY_STRING = "";
    public static final int MAX_FIELD_SIZE = 100;
    public static final int MIN_FIELD_SIZE = 0;
    public static final int TWO_DIMENSION = 2;
}
