package interfaces.console.util;

import common.exception.GlobalExceptionHandler;
import java.util.function.Supplier;

public class RetryHandler {
    private final GlobalExceptionHandler globalExceptionHandler;

    public RetryHandler(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    public <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                globalExceptionHandler.handle(e);
            }
        }
    }
}
