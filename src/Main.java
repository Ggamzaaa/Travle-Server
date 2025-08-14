import common.config.AppConfig;
import interfaces.console.controller.MainController;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MainController mainController = appConfig.mainController();
        System.out.println("[server] server is running");
        mainController.run();
    }
}
