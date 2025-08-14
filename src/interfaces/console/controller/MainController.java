package interfaces.console.controller;

public class MainController {
    private final TravelConsoleController travelConsoleController;

    public MainController(TravelConsoleController travelConsoleController) {
        this.travelConsoleController = travelConsoleController;
    }

    public void run() {
        travelConsoleController.recordTravel();
    }

    public void retry() {

    }
}
