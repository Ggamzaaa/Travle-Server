package interfaces.console.view;

import interfaces.console.util.InputHandler;
import java.time.LocalDate;
import travel.domain.Travel;

public class TravelView {
    private final InputHandler inputHandler;

    public TravelView(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public String askTravelName() {
        return "여행 이름을 입력하세요 * : ";
    }

    public String askStartDate() {
        return "시작 날짜를 입력하세요 * (ex. 2025-12-25) : ";
    }

    public String askEndDate() {
        return "종료 날짜를 입력하세요 * (ex. 2025-12-31) : ";
    }

    public void print(String message) {
        System.out.println(message);
    }

    public Travel getTravelRecord() {
        print(askTravelName());
        String travelName = inputHandler.getTravelName();

        print(askStartDate());
        LocalDate startDate = inputHandler.getStartDate();

        print(askEndDate());
        LocalDate endDate = inputHandler.getEndDate(startDate);

        // 임시 id 값
        return new Travel(1, travelName, startDate, endDate);
    }
}
