package com.myob.minesweeper.unit.service.result;

import com.myob.minesweeper.unit.model.MineField;

import java.util.List;

public interface IResultService {
    void displayResultFields(List<MineField> resultFields);
}
