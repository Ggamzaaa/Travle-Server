package travel.domain;

import common.domain.Repository;
import java.util.List;
import java.util.Optional;

public interface TravelRepository extends Repository<Travel, Integer> {
    List<Travel> findAll();
    Optional<Travel> findById(Integer id);
}
