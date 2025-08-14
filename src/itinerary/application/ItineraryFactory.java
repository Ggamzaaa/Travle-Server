package itinerary.application;

import common.domain.IdGenerator;
import itinerary.domain.Itinerary;

import java.time.LocalDateTime;
import java.util.Objects;

public class ItineraryFactory {
    private final IdGenerator idGen;

    public ItineraryFactory(IdGenerator idGen) {
        this.idGen = Objects.requireNonNull(idGen);
    }

    /** ID를 생성해서 완성된 Itinerary를 만들어 반환 */
    public Itinerary newItinerary(
            int travelId,
            String departurePlace,
            String destination,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime,
            LocalDateTime checkIn,
            LocalDateTime checkOut
    ) {
        int itineraryId = idGen.nextId();
        return new Itinerary(itineraryId, travelId, departurePlace, destination, departureTime, arrivalTime, checkIn, checkOut);
    }
}
