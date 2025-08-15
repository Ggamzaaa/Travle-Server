package interfaces.console.view;

import interfaces.console.util.InputHandler;
import java.time.LocalDate;
import java.util.List;
import travel.application.TravelFactory;
import travel.domain.Travel;

public class TravelView {
    private final InputHandler inputHandler;
    private final TravelFactory travelFactory;

    public TravelView(InputHandler inputHandler, TravelFactory travelFactory) {
        this.inputHandler = inputHandler;
        this.travelFactory = travelFactory;
    }

    public String promptTravelName() {
        return "여행 이름을 입력하세요 * : ";
    }

    public String promptStartDate() {
        return "시작 날짜를 입력하세요 * (ex. 2025-12-25) : ";
    }

    public String promptEndDate() {
        return "종료 날짜를 입력하세요 * (ex. 2025-12-31) : ";
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public Travel readTravelFromUser() {
        printMessage(promptTravelName());
        String travelName = inputHandler.getTravelName();

        printMessage(promptStartDate());
        LocalDate startDate = inputHandler.getStartDate();

        printMessage(promptEndDate());
        LocalDate endDate = inputHandler.getEndDate(startDate);

        return travelFactory.newTravel(travelName, startDate, endDate);
    }

    private String formatTravelInfo(Travel travel) {
        return "------------------------------------\n"
                + String.format("여행 ID : %d%n", travel.getId())
                + String.format("여행 이름 : %s%n", travel.getName())
                + String.format("시작 날짜 : %s%n", travel.getStartDate())
                + String.format("종료 날짜 : %s%n", travel.getEndDate())
                + "------------------------------------\n";
    }

    public void displayTravelSaved(Travel travel) {
        String message = "여행 정보가 저장되었습니다!\n" + formatTravelInfo(travel);
        printMessage(message);
    }

    public void displayTravelList(List<Travel> travelList) {
        if (travelList == null || travelList.isEmpty()) {
            printMessage("여행을 먼저 기록해주세요.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Travel travel : travelList) {
            sb.append(formatTravelInfo(travel));
        }
        System.out.print(sb.toString());
    }
}
