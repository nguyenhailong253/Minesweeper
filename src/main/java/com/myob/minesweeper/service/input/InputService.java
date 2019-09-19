package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MineField;

import java.util.List;

public interface InputService {
    List<MineField> getListOfNewMineFields();
}
