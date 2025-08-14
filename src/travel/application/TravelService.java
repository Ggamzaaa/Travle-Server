package travel.application;

import common.domain.IdGenerator;
import travel.domain.Travel;
import travel.domain.TravelRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TravelService {
    private final TravelRepository repo;

    public TravelService(TravelRepository repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    public Travel save(Travel travel) {
        return repo.save(travel);
    }

    public List<Travel> listAll() {
        return repo.findAll();
    }
}
