package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MineFieldServiceTest {

    private static int sampleNumRows = 2;
    private static int sampleNumColumns = 3;
    private static MineField baseField;

    private static void initialiseNewFieldWithMines() {
        String[][] fieldValues = new String[][]{{"*", ".", "."}, {".", "*", "."}};
        baseField = MineFieldService.initialiseNewField(sampleNumRows, sampleNumColumns);
        MineFieldService.updateFieldValues(baseField, fieldValues);
    }

    public static class TestUpdateFieldValues {

        @Before
        public void initialiseBaseMineField() {
            initialiseNewFieldWithMines();
        }

        @Test
        public void shouldSetFieldValues_WhenReceiveStringArrayWithMatchingDimensions() {
            String[][] expectedFieldValues = new String[][]{{"*", "*", "*"}, {"*", "*", "*"}};
            MineFieldService.updateFieldValues(baseField, expectedFieldValues);

            String[][] actualFieldValues = baseField.getFieldValues();

            Assert.assertArrayEquals(expectedFieldValues, actualFieldValues);
        }

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesHaveDifferentNumberOfColumns() {
            String[][] inputFieldValues = new String[][]{{"*", "*"}, {"*", "*"}};
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesHaveDifferentNumberOfRows() {
            String[][] inputFieldValues = new String[][]{{"*", "*", "*"}, {"*", "*", "*"}, {"*", "*", "*"}};
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesHaveDifferentNumberOfBothRowsAndColumns() {
            String[][] inputFieldValues = new String[][]{{"*", "*"}, {"*", "*"}, {"*", "*"}};
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesAreEmpty() {
            String[][] inputFieldValues = new String[][]{};
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }
    }

    public static class TestGetAdjacentIndices {
        private static final int adjacentRange = Constants.ADJACENT_RANGE;
        private static final int maxIndex = 3;
        private static List<Integer> expectedIndices;

        @Before
        public void initialiseListOfAdjacentIndices() {
            expectedIndices = new ArrayList<>();
        }

        @Test
        public void shouldReturnListOf3AdjacentIndices_WhenCurrentIndexIsNeitherAt0NorMaxIndex() {
            expectedIndices.add(1);
            expectedIndices.add(0);
            expectedIndices.add(2);

            List<Integer> actualIndices = MineFieldService.getAdjacentIndices(1, maxIndex, adjacentRange);

            Assert.assertEquals(expectedIndices, actualIndices);
        }

        @Test
        public void shouldReturnListOf2AdjacentIndices_WhenCurrentIndexIsAt0() {
            expectedIndices.add(0);
            expectedIndices.add(1);

            List<Integer> actualIndices = MineFieldService.getAdjacentIndices(0, maxIndex, adjacentRange);

            Assert.assertEquals(expectedIndices, actualIndices);
        }

        @Test
        public void shouldReturnListOf2AdjacentIndices_WhenCurrentIndexIsAtMaxIndex() {
            expectedIndices.add(2);
            expectedIndices.add(1);

            List<Integer> actualIndices = MineFieldService.getAdjacentIndices(maxIndex - 1, maxIndex, adjacentRange);

            Assert.assertEquals(expectedIndices, actualIndices);
        }
    }
}