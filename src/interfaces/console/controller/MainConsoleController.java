package interfaces.console.controller;

//public class MainConsoleController {
//    private final TravelConsoleController travelConsoleController;
//
//    public MainConsoleController(TravelConsoleController travelConsoleController) {
//        this.travelConsoleController = travelConsoleController;
//    }
//
//    public void run() {
//        travelConsoleController.recordTravel();
//    }
//
//    public void retry() {
//
//    }
//}

import interfaces.console.util.InputHandler;
import interfaces.console.view.ExitView;
import interfaces.console.view.MainView;

public class MainConsoleController {

    private final MainView mainView;
    private final ExitView exitView;
    private final InputHandler input;

    private final TravelConsoleController travelController;
    private final ItineraryConsoleController itineraryController;

    public MainConsoleController(MainView mainView,
                          ExitView exitView,
                          InputHandler input,
                          TravelConsoleController travelController,
                          ItineraryConsoleController itineraryController) {
        this.mainView = mainView;
        this.exitView = exitView;
        this.input = input;
        this.travelController = travelController;
        this.itineraryController = itineraryController;
    }

    public void run() {
        mainView.printBanner();
        while (true) {
            mainView.printMenu();
            int select = input.readMenuSelection();

            switch (select) {
                // case 1 -> travelController.recordTravel();
                // case 2 -> itineraryController.recordItinerary();
                // case 3 -> travelController.listTravels();
                // case 4 -> itineraryController.listItineraries();
                // case 5 -> { exitView.printExit(); return; }
                // default -> mainView.printInvalidSelection();

                case 1 -> {
                    travelController.recordTravel();
                    if (!input.askContinue()) {
                        exitView.printExit();
                        return;
                    }
                }
                case 2 -> {
                    itineraryController.recordItinerary();
                    if (!input.askContinue()) {
                        exitView.printExit();
                        return;
                    }
                }
                case 3 -> {
                    travelController.listTravels();
                    if (!input.askContinue()) {
                        exitView.printExit();
                        return;
                    }
                }
                case 4 -> {
                    itineraryController.listItineraries();
                    if (!input.askContinue()) {
                        exitView.printExit();
                        return;
                    }
                }
                case 5 -> {
                    exitView.printExit();
                    return;
                }
                default -> {
                    mainView.printInvalidSelection();
                    if (!input.askContinue()) {
                        exitView.printExit();
                        return;
                    }
                }
            }
        }
    }
}
