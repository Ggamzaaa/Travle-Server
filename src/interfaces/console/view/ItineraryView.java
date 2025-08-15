package interfaces.console.view;

import interfaces.console.util.InputHandler;
import interfaces.console.util.InputParser;
import interfaces.console.util.RetryHandler;
import itinerary.domain.Itinerary;
import itinerary.application.ItineraryFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ItineraryView {
    private final InputHandler inputHandler;
    private final InputParser inputParser;
    private final RetryHandler retryHandler;
    private final ItineraryFactory itineraryFactory;

    public ItineraryView(InputHandler inputHandler, InputParser inputParser, RetryHandler retryHandler, ItineraryFactory itineraryFactory) {
        this.inputHandler = inputHandler;
        this.inputParser = inputParser;
        this.retryHandler = retryHandler;
        this.itineraryFactory = itineraryFactory;
    }

    public String askTravelId()          { return "여정 기록을 원하는 여행 ID를 입력하세요 *: "; }
    public String askDeparturePlace()    { return "출발지를 입력하세요 * : "; }
    public String askDestination()       { return "도착지를 입력하세요 * : "; }
    public String askDepartureTime()     { return "출발 시간을 입력하세요 (ex. 2025-12-25 08:00) : "; }
    public String askArrivalTime()       { return "도착 시간을 입력하세요 (ex. 2025-12-31 23:00) : "; }
    public String askCheckIn()           { return "체크인 시간을 입력하세요 (없으면 Enter를 입력하세요) : "; }
    public String askCheckOut()          { return "체크아웃 시간을 입력하세요 (없으면 Enter를 입력하세요) : "; }

    public void print(String message) {
        System.out.println(message);
    }

    public boolean askAddMoreItinerary() {
        System.out.print("여정을 더 입력하시겠습니까? (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim().toUpperCase();
        return input.equals("Y") || input.equals("YES");
    }

    public int promptTravelIdForItinerary() {
        System.out.print("여정 기록을 원하는 여행 ID를 입력하세요 *: ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    public int promptTravelIdForQuery() {
        System.out.print("조회할 여행 ID를 입력하세요: ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    public void showNoItinerariesMessage(int travelId) {
        System.out.println("해당 여행 ID(" + travelId + ")에 등록된 여정이 없습니다.");
    }

    public void showItineraryListHeader(int travelId) {
        System.out.println("여행 ID " + travelId + "의 여정 목록:");
    }

    public String promptDeparturePlace() {
        print(askDeparturePlace());
        return inputHandler.getDeparturePlaceString();
    }

    public String promptDestination() {
        print(askDestination());
        return inputHandler.getDestinationString();
    }

    public LocalDateTime promptDepartureTime() {
        print(askDepartureTime());
        return inputParser.parseLocalDateTime(inputHandler.getDepartureTimeString());
    }

    public LocalDateTime promptArrivalTime() {
        print(askArrivalTime());
        return inputParser.parseLocalDateTime(inputHandler.getArrivalTimeString());
    }

    public Itinerary readItineraryFromUser(int travelId) {
        String departurePlace = retryHandler.handle(this::promptDeparturePlace);
        String destination = retryHandler.handle(this::promptDestination);
        LocalDateTime departureTime = retryHandler.handle(this::promptDepartureTime);
        LocalDateTime arrivalTime = retryHandler.handle(this::promptArrivalTime);

        print(askCheckIn());
        LocalDateTime checkIn = inputHandler.getCheckIn();

        print(askCheckOut());
        LocalDateTime checkOut = inputHandler.getCheckOut();

        return itineraryFactory.newItinerary(travelId, departurePlace, destination,
                departureTime, arrivalTime, checkIn, checkOut);
    }

    public void showItinerarySaved(Itinerary i) {
        System.out.println("여정이 저장되었습니다!");
        System.out.println("------------------------------------");
        System.out.printf("여정 ID : %s%n", i.getFormattedItineraryId());
        System.out.printf("출발지 : %s%n", i.getDeparturePlace());
        System.out.printf("도착지 : %s%n", i.getDestination());
        System.out.printf("출발 시간 : %s%n", i.getDepartureTime());
        System.out.printf("도착 시간 : %s%n", i.getArrivalTime());
        System.out.printf("체크인 : %s%n", i.getCheckIn() == null ? "-" : i.getCheckIn());
        System.out.printf("체크아웃 : %s%n", i.getCheckOut() == null ? "-" : i.getCheckOut());
        System.out.println("------------------------------------");
    }

    public void showItineraries(List<Itinerary> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("여정이 없습니다.");
            return;
        }

        for (Itinerary i : list) {
            System.out.println("------------------------------------");
            System.out.printf("여정 ID : %s%n", i.getFormattedItineraryId());
            System.out.printf("출발지 : %s%n", i.getDeparturePlace());
            System.out.printf("도착지 : %s%n", i.getDestination());
            System.out.printf("출발 시간 : %s%n", i.getDepartureTime() == null ? "-" : i.getDepartureTime());
            System.out.printf("도착 시간 : %s%n", i.getArrivalTime() == null ? "-" : i.getArrivalTime());
            System.out.printf("체크인 : %s%n", i.getCheckIn() == null ? "-" : i.getCheckIn());
            System.out.printf("체크아웃 : %s%n", i.getCheckOut() == null ? "-" : i.getCheckOut());
            System.out.println("------------------------------------");
        }
    }
}