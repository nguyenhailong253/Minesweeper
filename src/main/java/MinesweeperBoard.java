import java.util.ArrayList;

public class MinesweeperBoard {

    private ArrayList<Integer> dimensions;
    private ArrayList<String> boardContent;

    MinesweeperBoard(ArrayList<Integer> inputDimensions) {
        dimensions = inputDimensions;
        // TODO: 2019-08-25 Change this to Map rather than List ?
        for (int i = 0; i < inputDimensions.get(0); i++) {
            boardContent.add(new String());
        }
    }

    public ArrayList<Integer> getDimensions() {
        return dimensions;
    }

    public ArrayList<String> getBoardContent() {
        return boardContent;
    }

    // TODO: 2019-08-25 Might need setRowContent, getRowContent??? 
    public void setBoardContent(ArrayList<String> boardContent) {
        this.boardContent = boardContent;
    }

    public void setDimensions(ArrayList<Integer> dimensions) {
        this.dimensions = dimensions;
    }
}
