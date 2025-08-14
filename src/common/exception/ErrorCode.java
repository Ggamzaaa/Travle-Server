package common.exception;

public enum ErrorCode {
    /**
     * travel
     */
    TRAVEL_NOT_FOUND("TRAVEL-ERROR-001", "존재하지 않는 여행입니다."),
    TRAVEL_NAME_IS_EMPTY("TRAVEL-ERROR-002", "여행 이름이 비어있습니다."),
    TRAVEL_START_DATE_IS_EMPTY("TRAVEL-ERROR-003", "여행 시작일이 비어있습니다."),
    TRAVEL_END_DATE_IS_EMPTY("TRAVEL-ERROR-004", "여행 종료일이 비어있습니다."),
    TRAVEL_START_DATE_AFTER_END_DATE("TRAVEL-ERROR-005", "여행 시작일이 종료일보다 늦습니다."),
    TRAVEL_DATE_FORMAT_EXCEPTION("TRAVEL-ERROR-006", "여행 날짜 형식이 맞지 않습니다.")

    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
}
