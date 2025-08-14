package common.config;

import MainController;

// 의존성 주입
public class AppConfig {
    public static MainController mainController() {
        return new MainController();
    }
}
