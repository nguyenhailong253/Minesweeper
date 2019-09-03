package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MineField;

import java.util.List;

public interface IOutputService {
    public void printResultFields(List<MineField> resultFields);
}
