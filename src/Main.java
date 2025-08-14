import common.config.AppConfig;
import interfaces.MainController;

public class Main {
    public static void main(String[] args) {
        MainController mainController = AppConfig.mainController();
        System.out.println("[server] server is running");
        mainController.run();
    }
}
