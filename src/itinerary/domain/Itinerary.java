package itinerary.domain;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Itinerary {
    private final int itineraryId;
     private final int travelId;
    private final String departurePlace;
    private final String destination;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;
    private final LocalDateTime checkIn;
    private final LocalDateTime checkOut;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public Itinerary(int itineraryId, int travelId, String departurePlace, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.itineraryId = itineraryId;
        this.travelId = travelId;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public String getFormattedItineraryId() {
        return travelId + "-" + itineraryId;
    }

    public int getTravelId() {
        return travelId;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Itinerary ID: " + itineraryId +
                ", Travel ID: " + travelId +
                ", 출발: " + departurePlace +
                ", 도착: " + destination +
                ", 출발시간: " + (departureTime == null ? "null" : departureTime.format(formatter)) +
                ", 도착시간: " + (arrivalTime == null ? "null" : arrivalTime.format(formatter)) +
                ", 체크인: " + (checkIn == null ? "null" : checkIn.format(formatter)) +
                ", 체크아웃: " + (checkOut == null ? "null" : checkOut.format(formatter));
    }
}
