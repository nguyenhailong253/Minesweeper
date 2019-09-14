package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MineFieldTest {

    public static boolean compareSizeOfTwoIntegerLists(List<Integer> baseList, List<Integer> compareList) {
        return baseList.size() == compareList.size();
    }

    private static boolean compareListWithSameIntegersButNotInSameOrder(List<Integer> baseList, List<Integer> compareList) {
        boolean sameContent = true;

        if (!compareSizeOfTwoIntegerLists(baseList, compareList)) {
            return false;
        }

        for (int compareInt: compareList) {
            if (!baseList.contains(compareInt)) {
                sameContent = false;
                break;
            }
        }
        return sameContent;
    }

    private static int adjacentRange = Constants.ADJACENT_RANGE;
    private static int sampleNumRows = 3;
    private static int sampleNumColumns = 4;
    private static String[][] sampleFieldValue = new String[][]{
            {".", "*", ".", "."},
            {".", ".", "*", "*"},
            {".", ".", ".", "."}};
    private static MineField baseField = new MineField(sampleNumRows, sampleNumColumns, sampleFieldValue);

    public static class TestGetAdjacentRowIndices {

        @Test
        public void shouldReturnListOf3AdjacentIndices_WhenCurrentIndexIsNotAt0OrMaxIndex() {
            List<Integer> expectedIndices = new ArrayList<>();
            expectedIndices.add(0);
            expectedIndices.add(1);
            expectedIndices.add(2);
            int currentIndex = 1;

            List<Integer> actualIndices = baseField.getAdjacentRowIndices(currentIndex, adjacentRange);

            Assert.assertTrue(compareListWithSameIntegersButNotInSameOrder(expectedIndices, actualIndices));
        }

        @Test
        public void shouldReturnListOf2AdjacentIndices_WhenCurrentIndexIsAt0() {
            List<Integer> expectedIndices = new ArrayList<>();
            expectedIndices.add(0);
            expectedIndices.add(1);
            int currentIndex = 0;

            List<Integer> actualIndices = baseField.getAdjacentRowIndices(currentIndex, adjacentRange);

            Assert.assertTrue(compareListWithSameIntegersButNotInSameOrder(expectedIndices, actualIndices));
        }

        @Test
        public void shouldReturnListOf2AdjacentIndices_WhenCurrentIndexIsAtMaxIndex() {
            List<Integer> expectedIndices = new ArrayList<>();
            expectedIndices.add(1);
            expectedIndices.add(2);
            int currentIndex = sampleNumRows - 1;

            List<Integer> actualIndices = baseField.getAdjacentRowIndices(currentIndex, adjacentRange);

            Assert.assertTrue(compareListWithSameIntegersButNotInSameOrder(expectedIndices, actualIndices));
        }
    }
}