package common.exception;

public class TravelException extends RuntimeException {
    private final ErrorCode errorCode;

    public TravelException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
