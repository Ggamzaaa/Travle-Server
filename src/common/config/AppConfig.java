package common.config;

import interfaces.console.controller.MainController;
import common.domain.IdGenerator;
import common.domain.AtomicIdGenerator; // nextId() 형태
import interfaces.console.controller.TravelConsoleController;
import interfaces.console.util.InputHandler;
import interfaces.console.util.InputParser;
import interfaces.console.view.TravelView;
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
        int seed = travelRepository.findAll().stream().mapToInt(Travel::getId).max().orElse(0);
        this.idGenerator = new AtomicIdGenerator(seed);
        this.travelFactory = new TravelFactory(idGenerator);
        this.travelService = new TravelService(travelRepository);
    }

    public TravelRepository travelRepository() {
        return travelRepository;
    }

    /**
     * 의존성 주입 코드
     */
    public TravelService travelService() {
        return new TravelService(travelRepository);
    }

    public MainController mainController() {
        return new MainController(travelConsoleController());
    }

    public TravelConsoleController travelConsoleController() {
        return new TravelConsoleController(travelService, travelView());
    }

    public TravelView travelView() {
        return new TravelView(inputHandler());
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputParser());
    }

    public InputParser inputParser() {
        return new InputParser();
    }
}
