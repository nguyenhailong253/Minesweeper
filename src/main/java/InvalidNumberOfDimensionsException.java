public class InvalidNumberOfDimensionsException extends RuntimeException {
    private String customMessage;

    InvalidNumberOfDimensionsException(String msg) {
        super(msg);
        customMessage = msg;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }
}
