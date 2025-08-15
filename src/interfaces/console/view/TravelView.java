package interfaces.console.view;

import interfaces.console.util.InputHandler;
import interfaces.console.util.RetryHandler;
import java.time.LocalDate;
import java.util.List;
import travel.application.TravelFactory;
import travel.domain.Travel;

public class TravelView {
    private final InputHandler inputHandler;
    private final TravelFactory travelFactory;
    private final RetryHandler retryHandler;

    public TravelView(InputHandler inputHandler, TravelFactory travelFactory, RetryHandler retryHandler) {
        this.inputHandler = inputHandler;
        this.travelFactory = travelFactory;
        this.retryHandler = retryHandler;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String promptTravelName() {
        printMessage("여행 이름을 입력하세요 * : ");
        return inputHandler.getTravelName();
    }

    public LocalDate promptStartDate() {
        printMessage("시작 날짜를 입력하세요 * (ex. 2025-12-25) : ");;
        return inputHandler.getStartDate();
    }

    public LocalDate promptEndDate(LocalDate startDate) {
        printMessage("종료 날짜를 입력하세요 * (ex. 2025-12-31) : ");
        return inputHandler.getEndDate(startDate);
    }

    public Travel readTravelFromUser() {
        String travelName = retryHandler.handle(this::promptTravelName);
        LocalDate startDate = retryHandler.handle(this::promptStartDate);
        LocalDate endDate = retryHandler.handle(() -> promptEndDate(startDate));

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
