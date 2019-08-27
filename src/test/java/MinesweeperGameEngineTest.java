import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MinesweeperGameEngineTest {

    static MinesweeperGameEngine gameEngine = new MinesweeperGameEngine();
    static MinesweeperBoard initialBoard;
    static MinesweeperBoard finalBoard;
    static int[] defaultBoardDimensions;

    public static class InitialiseBoardTest {

        // TODO: 2019-08-26 Duplicated setup, how to setup for all tests ?

        @BeforeClass
        public static void initialise() {
            defaultBoardDimensions = new int[]{3,5};

            initialBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] expectedInitialBoardContent = new String[][]{
                    {"*", "*", "0", "0", "0"},
                    {"0", "0", "0", "0", "0",},
                    {"0", "*", "0", "0", "0",},};

            initialBoard.setBoardContent(expectedInitialBoardContent);
        }

        @Test
        public void shouldReturnArrayOfStringWithDotsReplacedByZeros_WhenGivenANewBoardObject() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] inputBoardContent = new String[][]{
                    {"*", "*", ".", ".", "."},
                    {".", ".", ".", ".", "."},
                    {".", "*", ".", ".", "."}};
            inputBoard.setBoardContent(inputBoardContent);

            inputBoard = gameEngine.initialiseBoard(inputBoard);
            String[][] actualBoardContent = inputBoard.getBoardContent();
            String[][] expectedInitialBoardContent = initialBoard.getBoardContent();

            Assert.assertArrayEquals(expectedInitialBoardContent, actualBoardContent);
        }

        @Test
        public void shouldReturnSameArray_WhenGivenArrayWithAllAsterisks() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] inputBoardContent = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"}};
            inputBoard.setBoardContent(inputBoardContent);
            inputBoard = gameEngine.initialiseBoard(inputBoard);
            String[][] actualBoardContent = inputBoard.getBoardContent();

            String[][] expectedInitialBoardContent = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"}};

            Assert.assertArrayEquals(expectedInitialBoardContent, inputBoardContent);
        }

        @Test
        public void shoudlReturnArrayWithAllZeros_WhenGivenArrayWithAllDots() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] inputBoardContent = new String[][]{
                    {".", ".", ".", ".", "."},
                    {".", ".", ".", ".", "."},
                    {".", ".", ".", ".", "."}};
            inputBoard.setBoardContent(inputBoardContent);
            inputBoard = gameEngine.initialiseBoard(inputBoard);
            String[][] actualBoardContent = inputBoard.getBoardContent();

            String[][] expectedInitialBoardContent = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"}};

            Assert.assertArrayEquals(expectedInitialBoardContent, inputBoardContent);
        }
    }

    public static class ProcessAnswerTest {

        @BeforeClass
        public static void initialise() {
            defaultBoardDimensions = new int[]{3,5};
            finalBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] expectedFinalBoardContent = new String[][]{
                    {"*", "*", "1", "0", "0"},
                    {"3", "3", "2", "0", "0",},
                    {"1", "*", "1", "0", "0",},};

            finalBoard.setBoardContent(expectedFinalBoardContent);
        }

        // TODO: 2019-08-26 Can't think of better naming...
        @Test
        public void shouldReturnCorrectBoardContent_WhenGivenInitialisedBoardContent() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] initialisedBoardContent = new String[][]{
                    {"*", "*", "0", "0", "0"},
                    {"0", "0", "0", "0", "0",},
                    {"0", "*", "0", "0", "0",},};
            inputBoard.setBoardContent(initialisedBoardContent);

            inputBoard = gameEngine.processMineField(inputBoard);
            String[][] actualAnswer = inputBoard.getBoardContent();
            String[][] expectedAnswer = finalBoard.getBoardContent();

            Assert.assertArrayEquals(actualAnswer, expectedAnswer);
        }

        @Test
        public void shouldReturnSameBoardContent_WhenGivenInitialBoardWithAllAsterisks() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] initialisedBoardContent = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"}};
            inputBoard.setBoardContent(initialisedBoardContent);

            inputBoard = gameEngine.processMineField(inputBoard);
            String[][] actualAnswer = inputBoard.getBoardContent();
            String[][] expectedAnswer = new String[][]{
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"},
                    {"*", "*", "*", "*", "*"}};

            Assert.assertArrayEquals(actualAnswer, expectedAnswer);
        }

        @Test
        public void shouldReturnBoardContentWithAllZeros_WhenGivenIntialBoardWithAllZeros() {
            MinesweeperBoard inputBoard = new MinesweeperBoard(defaultBoardDimensions);

            String[][] initialisedBoardContent = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"}};
            inputBoard.setBoardContent(initialisedBoardContent);

            inputBoard = gameEngine.processMineField(inputBoard);
            String[][] actualAnswer = inputBoard.getBoardContent();
            String[][] expectedAnswer = new String[][]{
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"},
                    {"0", "0", "0", "0", "0"}};

            Assert.assertArrayEquals(actualAnswer, expectedAnswer);
        }
    }
}
