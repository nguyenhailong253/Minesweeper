package com.myob.minesweeper.integration;

import com.myob.minesweeper.TestHelper;
import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IOService;
import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.model.MineFieldService;
import com.myob.minesweeper.model.MineFieldState;
import com.myob.minesweeper.service.result.ResultService;
import com.myob.minesweeper.service.result.ResultWriter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ResultWriterTest {

    private static IOService consoleIOService = new ConsoleIOService();
    private static ResultService resultService = new ResultWriter(consoleIOService);
    private static int defaultNumRows = 2;
    private static int defaultNumColumns = 2;
    private static List<MineField> listOfTestFields = new ArrayList<>();
    private static PrintStream mockPrintStream = mock(PrintStream.class);

    @BeforeClass
    public static void initialiseSampleList() {
        System.setOut(mockPrintStream);

        String[][] fieldValue = new String[][]{{"*","1"}, {"1", "1"}};
        MineField testMineField = MineFieldService.constructMineField(defaultNumRows, defaultNumColumns);
        MineFieldService.updateFieldState(testMineField, MineFieldState.CALCULATED);
        TestHelper.updateFieldValues(testMineField, fieldValue);

        listOfTestFields.add(testMineField);
    }

    @Test
    public void shouldPrint1Field_WhenReceiveListWithOnly1Field() {
        resultService.displayResultFields(listOfTestFields);
        verify(mockPrintStream).println("Field #1:");
        verify(mockPrintStream).println("*1");
        verify(mockPrintStream).println("11");
        verify(mockPrintStream).println("");
    }

    @Test
    public void shouldPrintNothing_WhenListIsEmpty() {
        List<MineField> newEmptyList = new ArrayList<>();
        resultService.displayResultFields(newEmptyList);
        verify(mockPrintStream, times(0)).println("Field #1:");
    }
}