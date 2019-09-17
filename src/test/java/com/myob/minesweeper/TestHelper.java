package com.myob.minesweeper;

import com.myob.minesweeper.model.MineField;

import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static boolean validateEqualListsOfFields(List<MineField> baseList, List<MineField> compareList) {

        if (baseList.size() != compareList.size()) {
            return false;
        }

        for (int fieldIndex = 0; fieldIndex < baseList.size(); fieldIndex++) {
            MineField baseField = baseList.get(fieldIndex);
            MineField compareField = compareList.get(fieldIndex);
            if (baseField.getFieldState() != compareField.getFieldState()
                    || !Arrays.deepEquals(baseField.getFieldValues(), compareField.getFieldValues())) {
                return false;
            }
        }
        return true;
    }
}
