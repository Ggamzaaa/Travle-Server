package itinerary.application;

import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;

import java.util.List;
import java.util.Objects;

public class ItineraryService {
    private final ItineraryRepository repo;

    public ItineraryService(ItineraryRepository repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    public Itinerary save(Itinerary itinerary) {
        return repo.save(itinerary);
    }

    public List<Itinerary> getItinerariesByTravelId(int travelId) {
        return repo.findItinerariesByTravelId(travelId);
    }
}
