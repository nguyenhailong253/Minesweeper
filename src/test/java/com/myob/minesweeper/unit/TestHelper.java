package com.myob.minesweeper.unit;

import com.myob.minesweeper.model.MineField;

import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static boolean validateEqualFields(MineField baseField, MineField compareField) {
        return baseField.getRowDimension() == compareField.getRowDimension()
                && baseField.getColumnDimension() == compareField.getColumnDimension()
                && Arrays.deepEquals(baseField.getFieldValue(), compareField.getFieldValue());
    }

    public static boolean compareSizeOfTwoFieldLists(List<MineField> baseList, List<MineField> compareList) {
        return baseList.size() == compareList.size();
    }

    public static boolean validateEqualListsOfFields(List<MineField> baseList, List<MineField> compareList) {

        if (!compareSizeOfTwoFieldLists(baseList, compareList)) {
            return false;
        }

        for (int fieldIndex = 0; fieldIndex < baseList.size(); fieldIndex++) {
            MineField baseField = baseList.get(fieldIndex);
            MineField compareField = compareList.get(fieldIndex);
            if (!validateEqualFields(baseField, compareField)) {
                return false;
            }
        }
        return true;
    }
}
