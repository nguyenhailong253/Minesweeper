package com.myob.minesweeper.utils;

public class Constants {

    // Exception messages
    public static final String STRING_TO_NUMBER_EXCEPTION = "Input is not numerical. Cannot convert to integers";
    public static final String INVALID_DIMENSION_FORMAT = "Invalid dimension format. " +
            "Make sure you follow the format of 2 numbers being separated by 1 space";
    public static final String DIMENSION_OUT_OF_RANGE = "Dimensions out of range";
    public static final String INVALID_ROW_FORMAT = "Invalid characters or number of characters in a row";

    // Game rules
    public static final String INPUT_DIMENSION_PROMPT = "Please input dimension to create a new mine field." +
            "\nIt should be 2 numbers separated by a white space. Both dimensions need to be from 1 to 100." +
            "\nIf you no longer wants to create new fields, type in " + Constants.END_OF_INPUT_PATTERN;
    public static final String PLANT_MINE_PROMPT = "Please plant the mines for each row." +
            "\nPut '*' where you want the mine to be, '.' where there's no mine.\nNo other characters are allowed." +
            "\nPlease enter the correct number of characters (the dimension you specified - row of length 3 cannot have " +
            "4 or more characters or 2 or fewer characters; same with column)";
    public static final String FIELD_CREATED = "Field is created\n";
    public static final String RESULT_FIELD_CALCULATED = "\nAll fields are calculated. Results: \n";

    // Other utils
    public static final String LABEL = "Field #";
    public static final String ZERO_STRING = "0";
    public static final String VALID_FIELD_DIMENSION_PATTERN = "[0-9]{1,}\\s[0-9]{1,}";
    public static final String VALID_ROW_PATTERN = "[\\*\\.]+";
    public static final String SINGLE_SPACE_PATTERN = "\\s";
    public static final String MINE_SQUARE = "*";
    public static final String SAFE_SQUARE = ".";
    public static final String END_OF_INPUT_PATTERN = "0 0";
    public static final String EMPTY_STRING = "";
    public static final int MAX_FIELD_SIZE = 100;
    public static final int MIN_FIELD_SIZE = 0;
    public static final int TWO_DIMENSION = 2;
    public static final int[] END_OF_INPUT_VALUE_SET = {0, 0};
}
