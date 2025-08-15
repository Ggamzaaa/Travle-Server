package interfaces.console.view;

public class MainView {

    public void printBanner() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("          깜자팀 여행 일정 작성 서비스         ");
        System.out.println("========================================");
    }

    public void printMenu() {
        System.out.println("메뉴 리스트");
        System.out.println(" - 여행 기록 (1)");
        System.out.println(" - 여정 기록 (2)");
        System.out.println(" - 여행 조회 (3)");
        System.out.println(" - 여정 조회 (4)");
        System.out.println(" - 종료     (5)");
        System.out.println();
        System.out.print("원하는 메뉴 번호를 선택하세요: ");
    }

    public void printInvalidSelection() {
        System.out.println("메뉴 번호를 다시 한 번 확인해주세요.");
    }
}