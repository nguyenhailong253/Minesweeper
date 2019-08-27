public class StringToNumberConversionException extends RuntimeException {

    private String customMessage;
    private Exception rootException;

    StringToNumberConversionException(String msg, Exception exception) {
        super(msg);
        customMessage = msg;
        rootException = exception;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }

    public Exception getRootException() {
        return this.rootException;
    }
}
