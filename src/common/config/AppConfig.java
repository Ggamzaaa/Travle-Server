package common.config;

import common.exception.GlobalExceptionHandler;
import interfaces.console.controller.MainConsoleController;
import interfaces.console.controller.ItineraryConsoleController;
import interfaces.console.controller.TravelConsoleController;
import interfaces.console.util.InputHandler;
import interfaces.console.util.InputParser;
import interfaces.console.util.RetryHandler;
import interfaces.console.view.TravelView;
import interfaces.console.view.ItineraryView;
import interfaces.console.view.MainView;
import interfaces.console.view.ExitView;
import common.domain.AtomicIdGenerator;
import common.domain.IdGenerator;
import itinerary.domain.ItineraryRepository;
import itinerary.infra.JsonItineraryRepository;
import itinerary.application.ItineraryService;
import itinerary.application.ItineraryServiceImpl;
import itinerary.application.ItineraryFactory;
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
    private final ItineraryService itineraryService;
    private final ItineraryFactory itineraryFactory;
    private final InputParser inputParser;
    private final InputHandler inputHandler;
    private final TravelView travelView;
    private final ItineraryView itineraryView;
    private final TravelConsoleController travelConsoleController;
    private final ItineraryConsoleController itineraryConsoleController;
    private final MainConsoleController mainConsoleController;
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
        this.itineraryFactory = new ItineraryFactory(idGenerator);
        this.travelService = new TravelServiceImpl(travelRepository);
        this.itineraryService = new ItineraryServiceImpl(itineraryRepository);

        this.inputParser = new InputParser();
        this.inputHandler = new InputHandler(inputParser);
        this.globalExceptionHandler = new GlobalExceptionHandler();
        this.retryHandler = new RetryHandler(globalExceptionHandler);

        this.travelView = new TravelView(inputHandler, travelFactory, retryHandler);
        this.itineraryView = new ItineraryView(inputHandler, inputParser, retryHandler, itineraryFactory);

        this.travelConsoleController = new TravelConsoleController(travelService, travelView);
        this.itineraryConsoleController = new ItineraryConsoleController(itineraryService, itineraryView);

        this.mainConsoleController = new MainConsoleController(
                new MainView(),
                new ExitView(),
                inputHandler,
                travelConsoleController,
                itineraryConsoleController
        );
    }

    public MainConsoleController mainConsoleController() {
        return mainConsoleController;
    }

    public ItineraryService itineraryService() {
        return itineraryService;
    }
}