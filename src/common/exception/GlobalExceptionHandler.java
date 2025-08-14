package common.exception;

public class GlobalExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof TravelException travelException) {
            System.err.println("[Error] " + travelException.getMessage());
        }
    }
}
