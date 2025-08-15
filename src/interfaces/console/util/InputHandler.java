package interfaces.console.util;

import java.time.LocalDate;
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
}
