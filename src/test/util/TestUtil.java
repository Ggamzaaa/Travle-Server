package test.util;

import travel.domain.Travel;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestUtil {
    private static final DateTimeFormatter D = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void assertTrue(boolean cond, String msg) {
        if (!cond) throw new AssertionError("ASSERT TRUE FAIL: " + msg);
    }
    public static void assertEquals(Object expected, Object actual, String msg) {
        if (expected == null && actual == null) return;
        if (expected != null && expected.equals(actual)) return;
        throw new AssertionError("ASSERT EQUALS FAIL: " + msg + " (expected=" + expected + ", actual=" + actual + ")");
    }

    public static File newTempDir(String prefix) {
        File d = new File("test-out/" + prefix + "-" + System.currentTimeMillis());
        if (!d.exists()) d.mkdirs();
        return d;
    }

    public static void pass(String name) {
        System.out.println("[PASS] " + name);
        System.out.println("-------------------------------------------");
    }

    // 보기 좋은 출력 도우미
    public static void printHeader(String title) {
        System.out.println();
        System.out.println("========== " + title + " ==========");
    }
    public static void printPath(String label, String path) {
        System.out.println(label + ": " + new File(path).getAbsolutePath());
    }
    public static void printTravel(Travel t) {
        System.out.printf("여행 id : %d%n여행 이름 : %s%n시작 : %s%n종료 : %s%n",
                t.id(), t.name(), t.startDate().format(D), t.endDate().format(D));
        System.out.println("--------------------");
    }
    public static void printList(List<Travel> list) {
        if (list.isEmpty()) {
            System.out.println("(비어있음)");
            return;
        }
        list.forEach(TestUtil::printTravel);
    }
}