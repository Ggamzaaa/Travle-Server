package travel.domain;

import java.util.List;
import java.util.Optional;

public interface TravelRepository {
    Travel save(Travel travel);
    List<Travel> findAll();
    Optional<Travel> findById(int id);
}
