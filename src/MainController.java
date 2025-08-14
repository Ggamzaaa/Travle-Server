public class MainController {
    public void run() {
    }

    private void retry() {
        // user가 나가고 싶다고 하면
        // retry 로직 종료
        // 그렇지 않으면...
        run();
    }
}
