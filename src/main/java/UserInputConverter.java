import java.util.ArrayList;

public class UserInputConverter {
    public String[] splitStringToArrayOf2Elements(String input) {
        String[] splittedDimensions = input.split("\\s");

        if (splittedDimensions.length != Constants.NUM_DIMENSIONS) {
            throw new InvalidNumberOfDimensionsException(Messages.WrongNumberOfDimensions);
        }
        return splittedDimensions;
    }

    public int[] convertToNumericalDimensions(String[] userInput) {
        try {
            int[] dimensionList = new int[Constants.NUM_DIMENSIONS];
            // TODO: 2019-08-26 Should have nested try catch or not?
            for (int i = 0; i < userInput.length; i++) {
                int dimensionValue = Integer.parseInt(userInput[i]);
                dimensionList[i] = dimensionValue;
            }
            return dimensionList;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Messages.StringToNumberException, e);
        }
    }
}
