package com.myob.minesweeper.utils;

public class NumberValidator {
    public static boolean validateNumberInRange(int number, int min, int max) {
        return min <= number && number <= max;
    }
}
