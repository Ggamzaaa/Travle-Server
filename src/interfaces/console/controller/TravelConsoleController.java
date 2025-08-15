package interfaces.console.controller;

import interfaces.console.view.TravelView;
import travel.application.TravelService;
import travel.domain.Travel;

import java.util.List;

public class TravelConsoleController {
    private final TravelService travelService;
    private final TravelView travelView;

    public TravelConsoleController(TravelService travelService, TravelView travelView) {
        this.travelService = travelService;
        this.travelView = travelView;
    }

//    public void recordTravel() {
//        Travel travel = travelView.readTravelFromUser();
//        travelView.displayTravelSaved(travel);
//        travelService.save(travel);
//    }

    public void recordTravel() {
        Travel travel = travelView.readTravelFromUser();
        Travel savedTravel = travelService.save(travel);
        travelView.displayTravelSaved(savedTravel);
    }

    public void listTravels() {
        List<Travel> travels = travelService.listAll();
        travelView.displayTravelList(travels);
    }
}
