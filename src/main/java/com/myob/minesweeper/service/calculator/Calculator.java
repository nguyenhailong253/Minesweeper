package com.myob.minesweeper.service.calculator;

import com.myob.minesweeper.model.MineField;

import java.util.List;

public interface Calculator {
    List<MineField> calculateHintNumbersInFields(List<MineField> inputFields);
}
