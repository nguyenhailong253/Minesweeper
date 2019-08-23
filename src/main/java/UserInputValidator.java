
public class UserInputValidator {

    public boolean validateInputDimensionsAreNumbers(String inputDimension) {
        boolean inputOnlyHasNumericalCharacters = inputDimension.matches("[(0-9){1,}]+");
        return inputOnlyHasNumericalCharacters;
    }

    public boolean validateMineFieldDimensions(int numRows, int numColumns) {
        if (numRows < Constants.MIN_FIED_SIZE
                || numRows > Constants.MAX_FIELD_SIZE
                || numColumns < Constants.MIN_FIED_SIZE
                || numColumns > Constants.MAX_FIELD_SIZE) {
            return false;
        }
        return true;
    }

    public boolean validateRowContent(String rowContent) {
        String invalidSubString = rowContent.replaceAll("[\\*\\.]+", "");
        if (!invalidSubString.isEmpty() || rowContent.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateLengthOfRowInput(String rowInput, int rowDimension) {
        return rowInput.length() == rowDimension;
    }

    public boolean validateEndOfInput(String userInput) {
        return userInput == Constants.END_OF_INPUT;
    }
}
