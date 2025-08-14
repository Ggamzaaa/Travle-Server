package interfaces.console.util;

import common.exception.ErrorCode;
import common.exception.TravelException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputParser {
    public void validateTravelName(String travelName) {
        if (travelName.isEmpty()) {
            throw new TravelException(ErrorCode.TRAVEL_NAME_IS_EMPTY);
        }
    }

    public LocalDate validateStartDate(String inputStartDate) {
        if (inputStartDate.isEmpty()) {
            throw new TravelException(ErrorCode.TRAVEL_START_DATE_IS_EMPTY);
        }
        return LocalDate.parse(inputStartDate);
    }

    public LocalDate validateEndDate(String inputEndDate, LocalDate startDate) {
        if (inputEndDate.isEmpty()) {
            throw new TravelException(ErrorCode.TRAVEL_END_DATE_IS_EMPTY);
        }
        LocalDate endDate = stringToLocalDate(inputEndDate);

        if (endDate.isBefore(startDate)) {
            throw new TravelException(ErrorCode.TRAVEL_START_DATE_AFTER_END_DATE);
        }
        return endDate;
    }

    public LocalDate stringToLocalDate(String localDate) {
        try {
            return LocalDate.parse(localDate);
        } catch (DateTimeParseException e) {
            throw new TravelException(ErrorCode.TRAVEL_DATE_FORMAT_EXCEPTION);
        }
    }
}
