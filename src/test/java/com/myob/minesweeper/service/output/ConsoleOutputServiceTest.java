package com.myob.minesweeper.service.output;

import com.myob.minesweeper.model.MineField;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleOutputServiceTest {

    private static IOutputService outputService = new ConsoleOutputService();
    private static int[] default2By2Dimension = new int[]{2,2};
    private static List<MineField> listOfTestFields = new ArrayList<>();
    private static PrintStream mockPrintStream = mock(PrintStream.class);

    public static class TestPrintFields {

        @BeforeClass
        public static void initialise() {
            System.setOut(mockPrintStream);

            MineField test2By2Field = new MineField(default2By2Dimension);
            String[][] fieldValue = new String[][]{{"*","1"}, {"1", "1"}};
            test2By2Field.setFieldValue(fieldValue);

            listOfTestFields.add(test2By2Field);
        }

        @Test
        public void shouldPrint1Field_WhenReceiveListWithOnlyHas1Field() {
            outputService.printResultFields(listOfTestFields);
            verify(mockPrintStream).println("Field #1:");
            verify(mockPrintStream).println("*1");
            verify(mockPrintStream).println("11");
            verify(mockPrintStream).println("");
        }

        @Test
        public void shouldPrintNothing_WhenListIsEmpty() {
            List<MineField> newEmptyList = new ArrayList<>();
            outputService.printResultFields(newEmptyList);
            verify(mockPrintStream, times(0)).println("Field #1:");
        }
    }
}