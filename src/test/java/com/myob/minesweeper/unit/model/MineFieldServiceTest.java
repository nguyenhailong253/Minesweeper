package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.exception.InvalidRowFormatException;
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
        public void shouldThrowFieldValuesException_WhenReceiveStringArrayWithDifferentNumberOfRows() {
            String[][] inputFieldValues = new String[][]{{"*", "*", "*"}};
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesAreEmpty() {
            String[][] emptyFieldValues = new String[][]{};
            MineFieldService.updateFieldValues(baseField, emptyFieldValues);
        }
    }

    public static class TestUpdateRowValue {

        @Before
        public void initialiseBaseMineField() {
            initialiseNewFieldWithMines();
        }

        @Test
        public void shouldSetRowValue_WhenReceiveValidStringAndRowIndex() {
            MineFieldService.updateRowValue(baseField, "***", 1);
            String[] expectedRowValue = new String[]{"*", "*", "*"};

            String[] actualRowValue = baseField.getFieldValues()[1];

            Assert.assertArrayEquals(expectedRowValue, actualRowValue);
        }

        @Test(expected = InvalidRowFormatException.class)
        public void shouldThrowRowFormatException_WhenStringInputHasInvalidPattern() {
            MineFieldService.updateRowValue(baseField, "hello_world!@#!$12412531", 1);
        }

        @Test(expected = InvalidRowFormatException.class)
        public void shouldThrowRowFormatException_WhenStringInputHasInvalidLength() {
            MineFieldService.updateRowValue(baseField, "*........**", 1);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowIndexIsOutOfBounds() {
            MineFieldService.updateRowValue(baseField, "*.*", 100);
        }

        @Test(expected = InvalidRowFormatException.class)
        public void shouldThrowRowFormatException_WhenStringInputHasInvalidPatternAndLengthAndRowIndexIsOutOfBounds() {
            MineFieldService.updateRowValue(baseField, "hello_world!@#!$12412531", 100);
        }

        @Test(expected = InvalidRowFormatException.class)
        public void shouldThrowRowFormatException_WhenStringInputIsEmpty() {
            MineFieldService.updateRowValue(baseField, "", 1);
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