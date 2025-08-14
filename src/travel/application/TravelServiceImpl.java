package travel.application;

import travel.domain.Travel;
import travel.domain.TravelRepository;

import java.util.List;
import java.util.Objects;

public class TravelServiceImpl implements TravelService {
    private final TravelRepository repo;

    public TravelServiceImpl(TravelRepository repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    public Travel save(Travel travel) {
        return repo.save(travel);
    }

    public List<Travel> listAll() {
        return repo.findAll();
    }
}
