package common.config;

import common.domain.IdGenerator;
import common.domain.AtomicIdGenerator; // nextId() 형태
import travel.application.TravelService;
import travel.domain.Travel;
import travel.domain.TravelRepository;
import travel.infra.JsonTravelRepository;

public class AppConfig {
    private final TravelRepository travelRepository;
    private final IdGenerator idGenerator;

    public AppConfig() {
        this.travelRepository = new JsonTravelRepository("data/travels.json");
        int seed = travelRepository.findAll().stream().mapToInt(Travel::id).max().orElse(0);
        this.idGenerator = new AtomicIdGenerator(seed);
    }

    public TravelRepository travelRepository() { return travelRepository; }
    public TravelService travelService() { return new TravelService(travelRepository, idGenerator); }
}
