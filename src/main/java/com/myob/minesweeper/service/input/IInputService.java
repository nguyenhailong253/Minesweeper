package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MineField;

import java.util.List;

public interface IInputService {
    List<MineField> getListOfNewMineFields();
}
