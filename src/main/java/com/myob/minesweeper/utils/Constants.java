package com.myob.minesweeper.utils;

public class Constants {

    // Exception messages
    public static final String STRING_TO_NUMBER_EXCEPTION = "Input is not numerical. Cannot convert to integers. " +
            "Please re-enter dimension of your field";
    public static final String INVALID_INPUT_DIMENSION = "Invalid dimension.";
    public static final String DIMENSION_OUT_OF_RANGE = "Dimension out of range. " +
            "Please re-enter dimension of your field";
    public static final String INVALID_ROW_FORMAT = "Invalid characters or number of characters in a row. " +
            "Please re-enter your row.";

    // Game rules + messages
    public static final String INPUT_DIMENSION_PROMPT = "Please input dimension to create a new mine field." +
            "\nIt should be 2 numbers separated by a white space. Both dimensions need to be from " +
            Constants.MIN_SIZE + " to " + Constants.MAX_SIZE + "." +
            "\nIf you no longer wants to create new fields, type in " + Constants.END_OF_INPUT_STRING;
    public static final String PLANT_MINE_PROMPT = "\nDimensions are valid. Please plant the mines for each row." +
            "\nPut '*' where you want the mine to be, '.' where there's no mine.\nNo other characters are allowed." +
            "\nPlease enter the correct number of characters (the dimension you specified - row of length 3 cannot have " +
            "4 or more characters or 2 or fewer characters; same with column)";
    public static final String FIELD_CREATED = "Field is created\n";
    public static final String RESULTS_CALCULATED = "\nAll fields are calculated. Results: \n";

    // Other utils
    public static final String LABEL = "Field #";
    public static final String FIELD_DIMENSION_PATTERN = "[0-9]{1,}\\s[0-9]{1,}";
    public static final String INPUT_ROW_PATTERN = "[*.]+";
    public static final String RESULT_ROW_PATTERN = "[*.\\d]+";
    public static final String WHITESPACE_DELIMITER = "\\s";
    public static final String MINE_SQUARE = "*";
    public static final String END_OF_INPUT_STRING = "0 0";
    public static final String INPUT_DELIMITER = "";
    public static final int MAX_SIZE = 100;
    public static final int MIN_SIZE = 1;
    public static final int ADJACENT_RANGE = 1;
}
