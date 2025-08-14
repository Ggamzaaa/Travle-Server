package interfaces.console.controller;

import interfaces.console.view.TravelView;
import travel.application.TravelService;
import travel.domain.Travel;

public class TravelConsoleController {
    private final TravelService travelService;
    private final TravelView travelView;

    public TravelConsoleController(TravelService travelService, TravelView travelView) {
        this.travelService = travelService;
        this.travelView = travelView;
    }

    public void recordTravel() {
        Travel travel = travelView.getTravelRecord();
    }
}
