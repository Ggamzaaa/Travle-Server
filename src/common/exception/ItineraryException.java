package common.exception;

public class ItineraryException extends RuntimeException {
    private final ErrorCode errorCode;

    public ItineraryException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
