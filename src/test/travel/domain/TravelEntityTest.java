package test.travel.domain;

import test.util.TestUtil;
import travel.domain.Travel;

import java.time.LocalDate;

public class TravelEntityTest {
    public static void main(String[] args) {
        TestUtil.printHeader("TravelEntityTest");

        // 정상 생성
        Travel t = new Travel(1, "  대전 여행  ",
                LocalDate.parse("2025-08-19"), LocalDate.parse("2025-08-31"));
        System.out.println("정상 생성 객체:");
        TestUtil.printTravel(t);
        TestUtil.assertEquals("대전 여행", t.getName(), "trim name");

        // 이름 공백 예외
        try {
            new Travel(2, "   ", LocalDate.parse("2025-08-19"), LocalDate.parse("2025-08-31"));
            throw new AssertionError("예외가 발생해야 합니다: blank name");
        } catch (IllegalArgumentException e) {
            System.out.println("예상 예외(이름 공백): " + e.getMessage());
        }

        // 날짜 역전 예외
        try {
            new Travel(3, "역전", LocalDate.parse("2025-08-31"), LocalDate.parse("2025-08-19"));
            throw new AssertionError("예외가 발생해야 합니다: end before start");
        } catch (IllegalArgumentException e) {
            System.out.println("예상 예외(날짜 역전): " + e.getMessage());
        }

        TestUtil.pass("TravelEntityTest");
    }
}