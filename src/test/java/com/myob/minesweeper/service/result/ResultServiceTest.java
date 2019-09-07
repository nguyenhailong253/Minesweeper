package com.myob.minesweeper.service.result;

import com.myob.minesweeper.model.MineField;
import com.myob.minesweeper.infrastructure.io.ConsoleIOService;
import com.myob.minesweeper.infrastructure.io.IIOService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ResultServiceTest {

    private static IIOService consoleIOService = new ConsoleIOService();
    private static IResultService resultService = new ResultService(consoleIOService);
    private static int[] default2By2Dimension = new int[]{2,2};
    private static List<MineField> listOfTestFields = new ArrayList<>();
    private static PrintStream mockPrintStream = mock(PrintStream.class);

    public static class TestPrintFields {

        @BeforeClass
        public static void initialiseSampleListBeforeAllTests() {
            System.setOut(mockPrintStream);

            MineField test2By2Field = new MineField(default2By2Dimension);
            String[][] fieldValue = new String[][]{{"*","1"}, {"1", "1"}};
            test2By2Field.setFieldValue(fieldValue);

            listOfTestFields.add(test2By2Field);
        }

        @Test
        public void shouldPrint1Field_WhenReceiveListWithOnlyHas1Field() {
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
}