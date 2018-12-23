package be.pxl.ja.ticketsystem;

public class EventBookedFullException extends Exception {
    public EventBookedFullException() {
        super();
    }

    public EventBookedFullException(String message) {
        super(message);
    }

    public EventBookedFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventBookedFullException(Throwable cause) {
        super(cause);
    }

    protected EventBookedFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
