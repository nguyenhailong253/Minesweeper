package com.myob.minesweeper.service.input;

import com.myob.minesweeper.model.MinesweeperBoard;
import com.myob.minesweeper.utils.Constants;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/* INTEGRATION TEST */
public class ConsoleInputServiceTest {

    private static UserInputConverter converter = new UserInputConverter();
    private static UserInputValidator validator = new UserInputValidator();
    private static UserInputParser mockParser = mock(UserInputParser.class);
    private static IInputService inputService = new ConsoleInputService(mockParser, validator, converter);
    private static int[] default2By2Dimension = new int[]{2,2};
    private static MinesweeperBoard default2By2Board = new MinesweeperBoard(default2By2Dimension);

    public static class TestGetValidInput {

        @BeforeClass
        public static void initialise() {
            default2By2Board.setCellValue("*", 0, 0);
            default2By2Board.setCellValue(".", 0, 1);
            default2By2Board.setCellValue(".", 1, 0);
            default2By2Board.setCellValue(".", 1, 1);
        }

        // TODO: 2019-08-30 Refactor names of tests 
        
        @Test
        public void shouldReturnEmptyList_GivenEndOfInputPattern() {
            List<MinesweeperBoard> expectedEmptyList = new ArrayList<>();

            when(mockParser.readFromConsole()).thenReturn(Constants.END_OF_INPUT_PATTERN);
            List<MinesweeperBoard> actualList = inputService.getValidInput();

            Assert.assertEquals(expectedEmptyList, actualList);
        }

        @Test
        public void shouldReturnListOf1ValidBoard_GivenValidDimensionAndRowContents() {
            List<MinesweeperBoard> expectedListOfBoards = new ArrayList<>();
            expectedListOfBoards.add(default2By2Board);

            when(mockParser.readFromConsole()).thenReturn(
                    "2 2", "*.", "..", "0 0");
            List<MinesweeperBoard> actualListOfBoards = inputService.getValidInput();

            Assert.assertEquals(expectedListOfBoards, actualListOfBoards);
        }

        @Test
        public void shouldReturnListOf1ValidBoard_GivenInvalidDimensionOnce() {
            List<MinesweeperBoard> expectedListOfBoards = new ArrayList<>();
            expectedListOfBoards.add(default2By2Board);

            when(mockParser.readFromConsole()).thenReturn(
                    "invalid dimension", "2 2", "*.", "..", "0 0");
            List<MinesweeperBoard> actualListOfBoards = inputService.getValidInput();

            Assert.assertEquals(expectedListOfBoards, actualListOfBoards);
        }

        @Test
        public void shouldReturnListOf1ValidBoard_GivenOutOfRangeDimensionOnce() {
            List<MinesweeperBoard> expectedListOfBoards = new ArrayList<>();
            expectedListOfBoards.add(default2By2Board);

            when(mockParser.readFromConsole()).thenReturn(
                    "1000 30000", "2 2", "*.", "..", "0 0");
            List<MinesweeperBoard> actualListOfBoards = inputService.getValidInput();

            Assert.assertEquals(expectedListOfBoards, actualListOfBoards);
        }

        @Test
        public void shouldReturnListOf1ValidBoard_GivenInvalidRowContentOnce() {
            List<MinesweeperBoard> expectedListOfBoards = new ArrayList<>();
            expectedListOfBoards.add(default2By2Board);

            when(mockParser.readFromConsole()).thenReturn(
                    "2 2", "invalid row content", "*.", "..", "0 0");
            List<MinesweeperBoard> actualListOfBoards = inputService.getValidInput();

            Assert.assertEquals(expectedListOfBoards, actualListOfBoards);
        }

        @Test
        public void shouldReturnListOf1ValidBoard_GivenExtraRowContentOnce() {
            List<MinesweeperBoard> expectedListOfBoards = new ArrayList<>();
            expectedListOfBoards.add(default2By2Board);

            when(mockParser.readFromConsole()).thenReturn(
                    "2 2", "*.", "..", "..", "0 0");
            List<MinesweeperBoard> actualListOfBoards = inputService.getValidInput();

            Assert.assertEquals(expectedListOfBoards, actualListOfBoards);
        }
    }
}