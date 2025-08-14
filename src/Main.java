import common.config.AppConfig;
import interfaces.console.controller.MainConsoleController;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MainConsoleController mainConsoleController = appConfig.mainController();
        System.out.println("[server] server is running");
        mainConsoleController.run();
    }
}
