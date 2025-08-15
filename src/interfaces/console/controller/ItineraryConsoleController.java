package interfaces.console.controller;

import interfaces.console.view.ItineraryView;
import itinerary.application.ItineraryService;
import itinerary.domain.Itinerary;
import java.util.List;

public class ItineraryConsoleController {
    private final ItineraryService itineraryService;
    private final ItineraryView itineraryView;

    public ItineraryConsoleController(ItineraryService itineraryService, ItineraryView itineraryView) {
        this.itineraryService = itineraryService;
        this.itineraryView = itineraryView;
    }

    public void recordItinerary() {
        // 여행 ID는 한 번만 입력받기
        int travelId = itineraryView.promptTravelIdForItinerary();

        do {
            Itinerary itinerary = itineraryView.readItineraryFromUser(travelId);
            Itinerary savedItinerary = itineraryService.save(itinerary);
            itineraryView.showItinerarySaved(savedItinerary);
        } while (itineraryView.askAddMoreItinerary());
    }

    public void listItineraries() {
        int travelId = itineraryView.promptTravelIdForQuery();
        List<Itinerary> itineraries = itineraryService.getItinerariesByTravelId(travelId);

        if (itineraries.isEmpty()) {
            itineraryView.showNoItinerariesMessage(travelId);
        } else {
            itineraryView.showItineraryListHeader(travelId);
            itineraryView.showItineraries(itineraries);
        }
    }
}