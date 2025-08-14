package itinerary.application;

import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ItineraryService {
    private final ItineraryRepository itineraryRepository = new ItineraryRepository();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void createItinerary(String inputTravelId, String inputItineraryId, String inputDeparturePlace, String inputDestination,
                                String inputDepartureTime, String inputArrivalTime, String inputCheckIn, String inputCheckOut) {

        int travelId = Integer.parseInt(inputTravelId);
        int itineraryId = Integer.parseInt(inputItineraryId);

        LocalDateTime departureTime = parseDateTime(inputDepartureTime);
        LocalDateTime arrivalTime = parseDateTime(inputArrivalTime);
        LocalDateTime checkIn = parseDateTime(inputCheckIn);
        LocalDateTime checkOut = parseDateTime(inputCheckOut);

        Itinerary itinerary = new Itinerary(itineraryId, travelId, inputDeparturePlace, inputDestination, departureTime, arrivalTime, checkIn, checkOut);

        itineraryRepository.save(itinerary);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isBlank()) return null;
        return LocalDateTime.parse(dateTimeStr, formatter).withSecond(0);
    }


    public List<Itinerary> getItinerariesByTravelId(String travelId) {
        int id = Integer.parseInt(travelId);
        return itineraryRepository.findItinerariesByTravelId(id);
    }

}
