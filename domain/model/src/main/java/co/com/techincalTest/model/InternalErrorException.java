package co.com.techincalTest.model;
import java.net.HttpURLConnection;

public class InternalErrorException extends Exception{
    private static final long serialVersionUID = 1L;
    private final int httpStatusCode;
    private final Exception exception;

    public InternalErrorException(String message) {
        super(message);
        this.exception = null;
        this.httpStatusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public InternalErrorException(int httpStatusCode, String message) {
        super(message);
        this.exception = null;
        this.httpStatusCode = httpStatusCode;
    }
}
