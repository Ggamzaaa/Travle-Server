package travel.application;

import common.domain.IdGenerator;
import travel.domain.Travel;
import travel.domain.TravelRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TravelService {
    private final TravelRepository repo;
    private final IdGenerator idGen;

    public TravelService(TravelRepository repo, IdGenerator idGen) {
        this.repo = Objects.requireNonNull(repo);
        this.idGen = Objects.requireNonNull(idGen);
    }

    public Travel create(String name, LocalDate start, LocalDate end) {
        int newId = idGen.nextId();
        Travel t  = new Travel(newId, name, start, end);
        return repo.save(t);
    }

    public List<Travel> listAll() {
        return repo.findAll();
    }
}
