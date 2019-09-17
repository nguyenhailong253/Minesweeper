package com.myob.minesweeper.integration;

import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IIOService;
import com.myob.minesweeper.service.application.MinesweeperService;
import com.myob.minesweeper.service.input.IInputService;
import com.myob.minesweeper.service.input.InputService;
import com.myob.minesweeper.service.result.IResultService;
import com.myob.minesweeper.service.result.ResultService;
import com.myob.minesweeper.utils.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MinesweeperServiceTest {

    private static IIOService mockConsoleIOService;
    private static IResultService resultService;
    private static IInputService inputService;
    private static MinesweeperService minesweeperService;

    @Before
    public void initialiseNewGame() {
        mockConsoleIOService = mock(ConsoleIOService.class);
        resultService = new ResultService(mockConsoleIOService);
        inputService = new InputService(mockConsoleIOService);
        minesweeperService = new MinesweeperService(inputService, resultService);
    }

    @Test
    public void shouldDisplayCalculatedFields_WhenReceive2InputFields() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("4 4", "*...", "....", ".*..", "....")
                .thenReturn("3 5", "**...", ".....", ".*...")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService).displayOutput("Field #1:");
        verify(mockConsoleIOService).displayOutput("*100");
        verify(mockConsoleIOService).displayOutput("2210");
        verify(mockConsoleIOService).displayOutput("1*10");
        verify(mockConsoleIOService).displayOutput("1110");
        verify(mockConsoleIOService).displayOutput("Field #2:");
        verify(mockConsoleIOService).displayOutput("**100");
        verify(mockConsoleIOService).displayOutput("33200");
        verify(mockConsoleIOService).displayOutput("1*100");
    }

    @Test
    public void shouldDisplayRulesOfInputDimensionTwice_WhenReceive1ValidDimensionAndThenEndOfInputPattern() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("1 1")
                .thenReturn("*")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService, times(2)).displayOutput(Constants.INPUT_DIMENSION_PROMPT);
    }

    @Test
    public void shouldDisplayRulesOfInputDimensionOnce_WhenReceiveEndOfInputPatternAtTheStartOfTheGame() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService, times(1)).displayOutput(Constants.INPUT_DIMENSION_PROMPT);
    }

    @Test
    public void shouldDisplayRulesOfInputRow_AfterEnteredValidDimension() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("1 1")
                .thenReturn(Constants.PLANT_MINE_PROMPT)
                .thenReturn("*")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService).displayOutput(Constants.PLANT_MINE_PROMPT);
    }

    @Test
    public void shouldDisplayIncorrectDimensionFormatMessage_WhenReceiveIncorrectInputDimensionFormat() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("12,34")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService).displayOutput(Constants.INVALID_INPUT_DIMENSION);
    }

    @Test
    public void shouldDisplayDimensionOutOfRangeMessage_WhenInputDimensionIsOutOfRequiredRange() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("1200 900")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService).displayOutput(Constants.DIMENSION_OUT_OF_RANGE);
    }

    @Test
    public void shouldDisplayInvalidRowFormatMessage_WhenReceiveIncorrectInputRowFormat() {
        when(mockConsoleIOService.readUserInput())
                .thenReturn("1 1")
                .thenReturn("./;****")
                .thenReturn(".")
                .thenReturn(Constants.END_OF_INPUT_STRING);
        minesweeperService.startGame();
        verify(mockConsoleIOService).displayOutput(Constants.INVALID_ROW_FORMAT);
    }
}