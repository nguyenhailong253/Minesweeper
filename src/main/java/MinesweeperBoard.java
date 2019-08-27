import java.util.ArrayList;

public class MinesweeperBoard {

    // TODO: 2019-08-26 Should change naming to like 2Dimensions or something to be more specific?
    private int[] dimensions;
    private String[][] boardContent;

    MinesweeperBoard(int[] inputDimensions) {
        dimensions = inputDimensions;
        int numRows = dimensions[0];
        int numColumns = dimensions[1];
        boardContent = new String[numRows][numColumns];
    }

    public String[][] getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String[][] boardContent) {
        this.boardContent = boardContent;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    public int getRowDimension() {
        return dimensions[0];
    }
    
    public int getColumnDimension() {
        return dimensions[1];
    }

    public String[] getRowContent(int rowIndex) {
        return this.boardContent[rowIndex];
    }

    public void setRowContent(String[] inputRow, int rowIndex) {
        this.boardContent[rowIndex] = inputRow;
    }

    public String getSquareContent(int rowIndex, int colIndex) {
        return this.boardContent[rowIndex][colIndex];
    }

    public void setSquareContent(String newSquareValue, int rowIndex, int colIndex) {
        this.boardContent[rowIndex][colIndex] = newSquareValue;
    }
}
