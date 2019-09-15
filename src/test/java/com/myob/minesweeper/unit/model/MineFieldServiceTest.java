package com.myob.minesweeper.unit.model;

import com.myob.minesweeper.exception.InvalidFieldValuesException;
import com.myob.minesweeper.exception.InvalidRowFormatException;
import com.myob.minesweeper.exception.InvalidSquareContentLength;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MineFieldServiceTest {

    private static int sampleNumRows = 2;
    private static int sampleNumColumns = 3;
    private static MineField baseField;

    // TODO: 15/9/19 Why do I have to do this?
    private static void initialiseNewField() {
        String[][] fieldValues = new String[][]{{"*", ".", "."}, {".", "*", "."}};
        baseField = MineFieldService.initialiseNewField(sampleNumRows, sampleNumColumns);
        baseField = MineFieldService.updateFieldValues(baseField, fieldValues);
    }

    public static class TestUpdateFieldValues {

        @BeforeClass
        public static void initialiseBaseMineField() {
            initialiseNewField();
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

        @Test(expected = InvalidFieldValuesException.class)
        public void shouldThrowFieldValuesException_WhenNewFieldValuesAreNull() {
            String[][] inputFieldValues = null;
            MineFieldService.updateFieldValues(baseField, inputFieldValues);
        }
    }

    public static class TestGetRowOfFieldByIndex {

        @BeforeClass
        public static void initialiseBaseMineField() {
            initialiseNewField();
        }

        @Test
        public void shouldReturnRowContent_WhenReceiveRowIndex() {
            String[] expectedRowContent = new String[]{".", "*", "."};

            String[] actualRowContent = MineFieldService.getRowOfFieldByIndex(baseField, 1);

            Assert.assertArrayEquals(expectedRowContent, actualRowContent);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowIndexOutOfBoundsException_WhenReceiveRowIndexAt1000() {
            int outOfRangeRows = 1000;
            MineFieldService.getRowOfFieldByIndex(baseField, outOfRangeRows);
        }
    }

    public static class TestSetRowOfFieldByIndex {

        @BeforeClass
        public static void initialiseBaseMineField() {
            initialiseNewField();
        }

        @Test
        public void shouldSetCorrectStringArray_AtCorrectRowInMineField() {
            String[] expectedRowContent = new String[]{".", ".", "."};
            String userInputRow = new String("...");

            MineFieldService.setRowOfFieldByIndex(baseField, 1, userInputRow);
            String[] actualRowContent = MineFieldService.getRowOfFieldByIndex(baseField, 1);

            Assert.assertArrayEquals(expectedRowContent, actualRowContent);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowIndexOutOfBoundsException_WhenReceiveRowIndexAt1000() {
            MineFieldService.setRowOfFieldByIndex(baseField, 1000, "...");
        }

        @Test(expected = InvalidRowFormatException.class)
        public void shouldThrowRowFormatException_WhenReceive10StringsFor1Row() {
            MineFieldService.setRowOfFieldByIndex(baseField, 1, ".*.*.*.*.*");
        }
    }

    public static class TestGetSquareOfFieldByIndices {

        @BeforeClass
        public static void initialiseBaseMineField() {
            initialiseNewField();
        }

        @Test
        public void shouldReturnSquareContent_WhenReceiveRowAndColumnIndices() {
            String expectedSquareContent = "*";

            String actualSquareContent = MineFieldService.getSquareOfFieldByIndices(baseField, 0, 0);

            Assert.assertEquals(expectedSquareContent, actualSquareContent);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowIndexIs3000() {
            MineFieldService.getSquareOfFieldByIndices(baseField, 3000, 0);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenColumnIndexIs3000() {
            MineFieldService.getSquareOfFieldByIndices(baseField, 0, 3000);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenBothRowAndColumnAre500() {
            MineFieldService.getSquareOfFieldByIndices(baseField, 500, 500);
        }
    }

    public static class TestSetSquareOfFieldByIndices {

        @BeforeClass
        public static void initialiseBaseMineField() {
            initialiseNewField();
        }

        @Test
        public void shouldSetCorrectSquareContent_AtCorrectRowAndColumnIndices() {
            String expectedSquareContent = "8";

            MineFieldService.setSquareOfFieldByIndices(baseField, 0, 0, "8");
            String actualSquareContent = MineFieldService.getSquareOfFieldByIndices(baseField, 0, 0);

            Assert.assertEquals(expectedSquareContent, actualSquareContent);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenRowIndexIs3000() {
            MineFieldService.setSquareOfFieldByIndices(baseField, 3000, 0, "5");
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenColumnIndexIs3000() {
            MineFieldService.setSquareOfFieldByIndices(baseField, 0, 3000, "5");
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowOutOfBoundsException_WhenBothRowAndColumnAre500() {
            MineFieldService.setSquareOfFieldByIndices(baseField, 500, 500, "5");
        }

        @Test(expected = InvalidSquareContentLength.class)
        public void shouldThrowSquareContentLengthException_WhenSquareLengthIsMoreThan1() {
            MineFieldService.setSquareOfFieldByIndices(baseField, 0, 0, "10");
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