package travel.application;

import java.util.List;
import travel.domain.Travel;

public interface TravelService {
    Travel save(Travel travel);

    List<Travel> listAll();
}
