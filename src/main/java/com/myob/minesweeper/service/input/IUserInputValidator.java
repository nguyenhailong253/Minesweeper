package com.myob.minesweeper.service.input;

public interface IUserInputValidator {
    public boolean validateInputDimensionFormat(String inputDimension);
    public boolean validateDimensionsInRange(int numRows, int numColumns);
    public boolean validateRowContent(String rowContent);
    public boolean validateLengthOfRowInput(String rowInput, int givenDimension);
    public boolean validateEndOfInputPattern(int[] convertedUserInputDimension);
}
