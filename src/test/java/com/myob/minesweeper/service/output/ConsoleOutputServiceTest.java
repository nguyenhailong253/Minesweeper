package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MinesweeperBoard;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleOutputServiceTest {

    private static IOutputService outputService = new ConsoleOutputService();
    private static int[] default2By2Dimension = new int[]{2,2};
    private static List<MinesweeperBoard> testBoards = new ArrayList<>();
    private static PrintStream mockPrintStream = mock(PrintStream.class);

    public static class TestPrintBoards {

        @BeforeClass
        public static void initialise() {
            System.setOut(mockPrintStream);
            MinesweeperBoard test2By2Board = new MinesweeperBoard(default2By2Dimension);
            test2By2Board.setCellValue("*", 0, 0);
            test2By2Board.setCellValue("1", 0, 1);
            test2By2Board.setCellValue("1", 1, 0);
            test2By2Board.setCellValue("1", 1, 1);
            testBoards.add(test2By2Board);
        }

        @Test
        public void shouldPrint1Board_WhenListOnlyHasOneBoard() {
            outputService.printBoards(testBoards);
            verify(mockPrintStream).println("Field #1:");
            verify(mockPrintStream).println("*1");
            verify(mockPrintStream).println("11");
            verify(mockPrintStream).println("");
        }

        @Test
        public void shouldPrintNothing_WhenListIsEmpty() {
            List<MinesweeperBoard> newEmptyList = new ArrayList<>();
            outputService.printBoards(newEmptyList);
            verify(mockPrintStream, times(0)).println();
        }
    }
}