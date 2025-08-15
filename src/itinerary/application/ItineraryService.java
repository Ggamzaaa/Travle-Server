package itinerary.application;

import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;
import itinerary.infra.JsonItineraryRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // JSON 저장할 디렉토리 경로를 생성자에서 받음
    public ItineraryService(String dirPath) {
        this.itineraryRepository = new JsonItineraryRepository(dirPath);
    }

    public void createItinerary(String inputTravelId, String inputItineraryId, String inputDeparturePlace,
                                String inputDestination,
                                String inputDepartureTime, String inputArrivalTime, String inputCheckIn,
                                String inputCheckOut) {

        // 1. ID 형식 체크
        int travelId;
        int itineraryId;
        try {
            travelId = Integer.parseInt(inputTravelId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 형식의 id입니다");
        }
        try {
            itineraryId = Integer.parseInt(inputItineraryId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 형식의 id입니다");
        }

        // 2. 필수 값 체크
        if (inputDeparturePlace == null || inputDeparturePlace.isBlank()) {
            throw new IllegalArgumentException("출발지는 필수 입력로 입력해주세요");
        }
        if (inputDestination == null || inputDestination.isBlank()) {
            throw new IllegalArgumentException("도착지는 필수 값입니다");
        }

        // 3. 시간 파싱
        LocalDateTime departureTime = parseDateTime(inputDepartureTime);
        LocalDateTime arrivalTime = parseDateTime(inputArrivalTime);
        LocalDateTime checkIn = parseDateTime(inputCheckIn);
        LocalDateTime checkOut = parseDateTime(inputCheckOut);

        // 4. 시간 논리 체크
        if (departureTime != null && arrivalTime != null && departureTime.isAfter(arrivalTime)) {
            throw new IllegalArgumentException("출발이 도착보다 느립니다");
        }
        if (checkIn != null && checkOut != null && checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("checkin이 checkout 보다 느립니다");
        }

        // 5. 여정 객체 생성 후 저장
        Itinerary itinerary = new Itinerary(itineraryId, travelId, inputDeparturePlace, inputDestination,
                departureTime, arrivalTime, checkIn, checkOut);

        itineraryRepository.save(itinerary);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isBlank()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, formatter).withSecond(0);
    }

    public List<Itinerary> getItinerariesByTravelId(String travelId) {
        int id;
        try {
            id = Integer.parseInt(travelId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바르지 않은 형식의 id입니다");
        }
        return itineraryRepository.findItinerariesByTravelId(id);
    }
}
