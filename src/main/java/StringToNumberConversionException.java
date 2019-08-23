public class StringToNumberConversionException extends RuntimeException {

    private String customMessage;
    private Exception rootException;

    StringToNumberConversionException(String msg, Exception rootException) {
        super(msg);
        customMessage = msg;
        rootException = rootException;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }

    public Exception getRootException() {
        return this.rootException;
    }
}
