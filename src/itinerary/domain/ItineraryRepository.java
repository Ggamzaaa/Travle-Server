package itinerary.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItineraryRepository {
    private final Map<Integer, Itinerary> itineraryMap = new HashMap<>();

    public void save(Itinerary itinerary) {
        itineraryMap.put(itinerary.getItineraryId(), itinerary);
    }

    public List<Itinerary> findItinerariesByTravelId(int travelId) {
        List<Itinerary> itineraryList = new ArrayList<>();
        for (Itinerary itinerary : itineraryMap.values()) {
            if (travelId == itinerary.getTravelId()) {
                itineraryList.add(itinerary);
            }
        }
        return itineraryList;
    }



}
