package com.myob.minesweeper.unit.utils;

import com.myob.minesweeper.utils.NumberValidator;
import org.junit.Assert;
import org.junit.Test;

public class NumberValidatorTest {

    private static final int MAX = 100;
    private static final int MIN = 0;

    @Test
    public void shouldReturnTrue_WhenNumberIsBiggerThanMinValueAndSmallerThanMaxValue() {
        boolean isNumberInRange = NumberValidator.validateNumberInRange(50, MIN, MAX);
        Assert.assertTrue(isNumberInRange);
    }

    @Test
    public void shouldReturnTrue_WhenNumberIsEqualToMinValue() {
        boolean isNumberInRange = NumberValidator.validateNumberInRange(0, MIN, MAX);
        Assert.assertTrue(isNumberInRange);
    }

    @Test
    public void shouldReturnTrue_WhenNumberIsEqualToMaxValue() {
        boolean isNumberInRange = NumberValidator.validateNumberInRange(100, MIN, MAX);
        Assert.assertTrue(isNumberInRange);
    }

    @Test
    public void shouldReturnFalse_WhenNumberIsSmallerThanMinValue() {
        boolean isNumberInRange = NumberValidator.validateNumberInRange(-10, MIN, MAX);
        Assert.assertFalse(isNumberInRange);
    }

    @Test
    public void shouldReturnFalse_WhenNumberIsBiggerThanMaxValue() {
        boolean isNumberInRange = NumberValidator.validateNumberInRange(999, MIN, MAX);
        Assert.assertFalse(isNumberInRange);
    }
}
