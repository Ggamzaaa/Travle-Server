package itinerary.domain;

import common.domain.Repository;
import java.util.List;


public interface ItineraryRepository extends Repository<Itinerary, Integer> {
    Itinerary save(Itinerary itinerary);
    List<Itinerary> findItinerariesByTravelId(int travelId);
    List<Itinerary> findAll();
}
