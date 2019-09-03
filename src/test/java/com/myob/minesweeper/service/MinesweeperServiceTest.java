package com.myob.minesweeper.service;

import com.myob.minesweeper.service.input.*;
import com.myob.minesweeper.service.output.ConsoleOutputService;
import com.myob.minesweeper.service.output.IOutputService;
import com.myob.minesweeper.utils.Constants;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class MinesweeperServiceTest {

    private static IUserInputParser mockParser = mock(ConsoleUserInputParser.class);
    private static IUserInputValidator validator = new ConsoleUserInputValidator();
    private static IUserInputConverter converter = new ConsoleUserInputConverter();
    private static IOutputService outputService;
    private static IInputService inputService;
    private static PrintStream mockPrintStream;
    private static MinesweeperService minesweeperService;

    public static class TestStartGame {

        @BeforeClass
        public static void initialiseClassUnderTestBeforeAllTests() {
            outputService = new ConsoleOutputService();
            inputService = new ConsoleInputService(mockParser, validator, converter);
            minesweeperService = new MinesweeperService(inputService, outputService);
        }

        @Before
        public void initialiseMockPrintStreamBeforeEachTest() {
            mockPrintStream = mock(PrintStream.class);
            System.setOut(mockPrintStream);
        }

        @Test
        public void shouldPrintCalculatedFields_WhenReceive2InputFields() {
            when(mockParser.readUserInput())
                    .thenReturn("4 4", "*...", "....", ".*..", "....")
                    .thenReturn("3 5", "**...", ".....", ".*...")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream).println("Field #1:");
            verify(mockPrintStream).println("*100");
            verify(mockPrintStream).println("2210");
            verify(mockPrintStream).println("1*10");
            verify(mockPrintStream).println("1110");
            verify(mockPrintStream).println("Field #2:");
            verify(mockPrintStream).println("**100");
            verify(mockPrintStream).println("33200");
            verify(mockPrintStream).println("1*100");
        }

        @Test
        public void shouldPrintRulesOfInputDimensionTwice_WhenReceive1ValidDimensionAndThenEndOfInputPattern() {
            when(mockParser.readUserInput())
                    .thenReturn("1 1")
                    .thenReturn("*")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream, times(2)).println(Constants.INPUT_DIMENSION_PROMPT);
        }

        @Test
        public void shouldPrintRulesOfInputDimensionOnce_WhenReceiveEndOfInputPatternAtTheStartOfTheGame() {
            when(mockParser.readUserInput())
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream, times(1)).println(Constants.INPUT_DIMENSION_PROMPT);
        }

        @Test
        public void shouldPrintRulesOfInputRow_AfterEnteredValidDimension() {
            when(mockParser.readUserInput())
                    .thenReturn("1 1")
                    .thenReturn(Constants.PLANT_MINE_PROMPT)
                    .thenReturn("*")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream).println(Constants.PLANT_MINE_PROMPT);
        }

        @Test
        public void shouldPrintIncorrectDimensionFormatMessage_WhenReceiveIncorrectInputDimensionFormat() {
            when(mockParser.readUserInput())
                    .thenReturn("12,34")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream).println(Constants.INVALID_DIMENSION_FORMAT);
        }

        @Test
        public void shouldPrintDimensionOutOfRangeMessage_WhenInputDimensionIsOutOfRequiredRange() {
            when(mockParser.readUserInput())
                    .thenReturn("1200 900")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream).println(Constants.DIMENSION_OUT_OF_RANGE);
        }

        @Test
        public void shouldPrintInvalidRowFormatMessage_WhenReceiveIncorrectInputRowFormat() {
            when(mockParser.readUserInput())
                    .thenReturn("1 1")
                    .thenReturn("./;****")
                    .thenReturn(".")
                    .thenReturn(Constants.END_OF_INPUT_PATTERN);
            minesweeperService.startGame();
            verify(mockPrintStream).println(Constants.INVALID_ROW_FORMAT);
        }
    }
}