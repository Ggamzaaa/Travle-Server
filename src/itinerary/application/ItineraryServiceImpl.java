package itinerary.application;

import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;

import java.util.List;
import java.util.Objects;

public class ItineraryServiceImpl implements ItineraryService {
    private final ItineraryRepository repo;

    public ItineraryServiceImpl(ItineraryRepository repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    @Override
    public Itinerary save(Itinerary itinerary) {
        return repo.save(itinerary);
    }

    @Override
    public List<Itinerary> getItinerariesByTravelId(int travelId) {
        return repo.findItinerariesByTravelId(travelId);
    }

    @Override
    public List<Itinerary> getAllItineraries() {
        return repo.findAll();
    }
}