package com.myob.minesweeper.service.result;

import com.myob.minesweeper.model.MineField;

import java.util.List;

public interface ResultService {
    void displayResultFields(List<MineField> resultFields);
}
