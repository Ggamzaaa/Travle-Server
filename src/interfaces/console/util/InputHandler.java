package interfaces.console.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputHandler {
    private final InputParser inputParser;

    public InputHandler(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public String getTravelName() {
        Scanner sc = new Scanner(System.in);
        String travelNameValue = sc.nextLine();
        inputParser.validateTravelName(travelNameValue);
        return travelNameValue;
    }

    public LocalDate getStartDate() {
        Scanner sc = new Scanner(System.in);
        String travelStartDate = sc.nextLine();
        return inputParser.validateStartDate(travelStartDate);
    }

    public LocalDate getEndDate(LocalDate startDate) {
        Scanner sc = new Scanner(System.in);
        String travelEndDate = sc.nextLine();
        return inputParser.validateEndDate(travelEndDate, startDate);
    }

    public Integer getTravelId() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }

    public String getDeparturePlace() {
        Scanner sc = new Scanner(System.in);
        String departurePlaceValue = sc.next();
        inputParser.validateDeparturePlace(departurePlaceValue);
        return departurePlaceValue;
    }

    public String getDeparturePlaceString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public String getDestination() {
        Scanner sc = new Scanner(System.in);
        String destinationValue = sc.next();
        inputParser.validateDestination(destinationValue);
        return destinationValue;
    }

    public String getDestinationString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public LocalDateTime getDepartureTime() {
        Scanner sc = new Scanner(System.in);
        String departureTimeValue = sc.nextLine();
        inputParser.validateDepartureTime(departureTimeValue);
        return inputParser.parseLocalDateTime(departureTimeValue);
    }

    public String getDepartureTimeString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public LocalDateTime getArrivalTime() {
        Scanner sc = new Scanner(System.in);
        String arrivalDateValue = sc.nextLine();
        inputParser.validateArrivalTime(arrivalDateValue);
        return inputParser.parseLocalDateTime(arrivalDateValue);
    }

    public String getArrivalTimeString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public LocalDateTime getCheckIn() {
        Scanner sc = new Scanner(System.in);
        String checkInValue = sc.nextLine().trim();
        if (checkInValue.isEmpty()) {
            return null;
        }
        return inputParser.parseLocalDateTime(checkInValue);
    }

    public LocalDateTime getCheckOut() {
        Scanner sc = new Scanner(System.in);
        String checkOutValue = sc.nextLine().trim();
        if (checkOutValue.isEmpty()) {
            return null;
        }
        return inputParser.parseLocalDateTime(checkOutValue);
    }

    public int readMenuSelection() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }

    public boolean askContinue() {
        System.out.print("계속 하시겠습니까? (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim().toUpperCase();
        return input.equals("Y") || input.equals("YES");
    }
}