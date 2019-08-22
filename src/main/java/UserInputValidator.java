
public class UserInputValidator {

    public boolean validateMineFieldDimensions(int numRows, int numColumns) {
        if (numRows < Constants.MIN_FIED_SIZE
                || numRows > Constants.MAX_FIELD_SIZE
                || numColumns < Constants.MIN_FIED_SIZE
                || numColumns > Constants.MAX_FIELD_SIZE) {
            return false;
        }
        return true;
    }

    public boolean validateRowContent(String row) {
        String invalidSubString = row.replaceAll("[\\*\\.]+", "");
        if (!invalidSubString.isEmpty() || row.isEmpty()) {
            return false;
        }
        return true;
    }
}
