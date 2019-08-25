import java.util.ArrayList;

public class UserInputConverter {
    public String[] splitStringWithWhiteSpaceDelimeter(String input) {
        return input.split("\\s");
    }

    public ArrayList<Integer> convertToNumericalDimensions(String[] userInput) {
        try {
            ArrayList<Integer> dimensionList = new ArrayList<>();
            for (String input: userInput) {
                int dimensionValue = Integer.parseInt(input);
                dimensionList.add(dimensionValue);
            }
            return dimensionList;
        } catch (Exception e) {
            throw new StringToNumberConversionException(Messages.StringToNumberException, e);
        }
    }
}
