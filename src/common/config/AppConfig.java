package common.config;

import interfaces.MainController;
import common.domain.IdGenerator;
import common.domain.AtomicIdGenerator; // nextId() 형태
import travel.application.TravelFactory;
import travel.application.TravelService;
import travel.domain.Travel;
import travel.domain.TravelRepository;
import travel.infra.JsonTravelRepository;

public class AppConfig {
    private final TravelRepository travelRepository;
    private final IdGenerator idGenerator;
    private final TravelFactory travelFactory;
    private final TravelService travelService;

    public AppConfig() {
        this.travelRepository = new JsonTravelRepository("data");
        int seed = travelRepository.findAll().stream().mapToInt(Travel::id).max().orElse(0);
        this.idGenerator = new AtomicIdGenerator(seed);
        this.travelFactory = new TravelFactory(idGenerator);
        this.travelService = new TravelService(travelRepository);
    }
    public static MainController mainController() {
        return new MainController();
    }

    public TravelRepository travelRepository() { return travelRepository; }
    public TravelService travelService() { return new TravelService(travelRepository); }
}
