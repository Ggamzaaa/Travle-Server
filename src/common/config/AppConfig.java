package common.config;

import common.exception.GlobalExceptionHandler;
import interfaces.console.controller.MainConsoleController;
import interfaces.console.controller.TravelConsoleController;
import interfaces.console.util.InputHandler;
import interfaces.console.util.InputParser;
import interfaces.console.util.RetryHandler;
import interfaces.console.view.TravelView;
import common.domain.AtomicIdGenerator;
import common.domain.IdGenerator;
import itinerary.domain.ItineraryRepository;
import itinerary.infra.JsonItineraryRepository;
import travel.application.TravelFactory;
import travel.application.TravelService;
import travel.application.TravelServiceImpl;
import travel.domain.Travel;
import travel.domain.TravelRepository;
import travel.infra.JsonTravelRepository;

public class AppConfig {
    private final TravelRepository travelRepository;
    private final IdGenerator idGenerator;
    private final TravelFactory travelFactory;
    private final TravelService travelService;
    private final ItineraryRepository itineraryRepository;
    private final InputParser inputParser;
    private final InputHandler inputHandler;
    private final TravelView travelView;
    private final TravelConsoleController travelConsoleController;
    private final MainConsoleController mainController;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final RetryHandler retryHandler;

    public AppConfig() {
        this.travelRepository = new JsonTravelRepository("data");
        this.itineraryRepository = new JsonItineraryRepository("data");

        int seed = travelRepository.findAll().stream()
                .mapToInt(Travel::getId)
                .max()
                .orElse(0);
        this.idGenerator = new AtomicIdGenerator(seed);

        this.travelFactory = new TravelFactory(idGenerator);
        this.travelService = new TravelServiceImpl(travelRepository);

        this.inputParser = new InputParser();
        this.inputHandler = new InputHandler(inputParser);
        this.globalExceptionHandler = new GlobalExceptionHandler();
        this.retryHandler = new RetryHandler(globalExceptionHandler);
        this.travelView = new TravelView(inputHandler, travelFactory, retryHandler);
        this.travelConsoleController = new TravelConsoleController(travelService, travelView);
        this.mainController = new MainConsoleController(travelConsoleController);
    }

    public MainConsoleController mainConsoleController() {
        return mainController;
    }
}
