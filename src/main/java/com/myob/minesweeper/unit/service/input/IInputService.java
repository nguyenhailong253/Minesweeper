package com.myob.minesweeper.unit.service.input;

import com.myob.minesweeper.unit.model.MineField;

import java.util.List;

public interface IInputService {
    List<MineField> getListOfNewMineFields();
}
