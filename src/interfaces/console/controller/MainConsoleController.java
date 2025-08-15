package interfaces.console.controller;

public class MainConsoleController {
    private final TravelConsoleController travelConsoleController;

    public MainConsoleController(TravelConsoleController travelConsoleController) {
        this.travelConsoleController = travelConsoleController;
    }

    public void run() {
        travelConsoleController.recordTravel();
    }

    public void retry() {

    }
}
