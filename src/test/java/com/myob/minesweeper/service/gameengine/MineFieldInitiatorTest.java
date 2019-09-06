package com.myob.minesweeper.service.gameengine;

import com.myob.minesweeper.model.MineField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MineFieldInitiatorTest {

<<<<<<< HEAD
=======
    private static MineFieldInitiator initiator = new MineFieldInitiator();
>>>>>>> master
    private static int[] defaultFieldDimensions = new int[]{2,3};
    private static MineField inputField;

    public static class TestInitialiseField {

        @Before
        public void initialiseNewFieldBeforeEachTest() {
            inputField = new MineField(defaultFieldDimensions);
        }

        @Test
        public void shouldReturnEmptyField_WhenReceiveFieldWithNoContent() {
            String[][] expectedEmptyField = new String[2][3];

<<<<<<< HEAD
            String[][] actualField = MineFieldInitiator.initialiseField(inputField).getFieldValue();
=======
            String[][] actualField = initiator.initialiseField(inputField).getFieldValue();
>>>>>>> master

            Assert.assertArrayEquals(expectedEmptyField, actualField);
        }

        @Test
        public void shouldReturnFieldWithDotsReplacedByZeros_WhenReceiveFieldWithBothDotsAndAsterisks() {
            String[][] inputFieldValue = new String[][]{
                    {"*", ".", "."},
                    {".", "*", "."}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"*", "0", "0"}, {"0", "*", "0"}};

<<<<<<< HEAD
            String[][] actualField = MineFieldInitiator.initialiseField(inputField).getFieldValue();
=======
            String[][] actualField = initiator.initialiseField(inputField).getFieldValue();
>>>>>>> master

            Assert.assertArrayEquals(expectedField, actualField);
        }

        @Test
        public void shouldReturnFieldWithSameContent_WhenReceiveFieldWithAllAsterisks() {
            String[][] inputFieldValue = new String[][]{
                    {"*", "*", "*"},
                    {"*", "*", "*"}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"*", "*", "*"}, {"*", "*", "*"}};

<<<<<<< HEAD
            String[][] actualField = MineFieldInitiator.initialiseField(inputField).getFieldValue();
=======
            String[][] actualField = initiator.initialiseField(inputField).getFieldValue();
>>>>>>> master

            Assert.assertArrayEquals(expectedField, actualField);
        }

        @Test
        public void shouldReturnFieldWithAllZeros_WhenReceiveFieldWithAllDots() {
            String[][] inputFieldValue = new String[][]{
                    {".", ".", "."},
                    {".", ".", "."}};
            inputField.setFieldValue(inputFieldValue);
            String[][] expectedField = new String[][]{{"0", "0", "0"}, {"0", "0", "0"}};

<<<<<<< HEAD
            String[][] actualField = MineFieldInitiator.initialiseField(inputField).getFieldValue();
=======
            String[][] actualField = initiator.initialiseField(inputField).getFieldValue();
>>>>>>> master

            Assert.assertArrayEquals(expectedField, actualField);
        }
    }
}