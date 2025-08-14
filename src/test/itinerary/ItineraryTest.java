package test.itinerary;

import itinerary.application.ItineraryService;
import itinerary.domain.Itinerary;

import java.io.File;
import java.util.List;

public class ItineraryTest {
    public static void main(String[] args) {
        System.out.println("=== ItineraryTest 시작 ===");

        File dir = new File("test-out/repo-1755151995591");
        ItineraryService itineraryService = new ItineraryService(dir.getPath());

//        // --- 여정 기록 ---
//        itineraryService.createItinerary("1", "0", "서울", "제주",
//                "2025-08-11 11:00", "2025-08-13 11:00",
//                "2025-08-14 11:00", "2025-08-15 11:00");
//
//        itineraryService.createItinerary("1", "1", "서울", "상하이",
//                "2025-08-11 11:00", "2025-08-13 11:00",
//                "2025-08-14 11:00", "2025-08-15 11:00");
//
//        itineraryService.createItinerary("1", "2", "서울", "부산",
//                "2025-08-12 09:00", "2025-08-12 12:00",
//                "2025-08-12 14:00", "2025-08-12 18:00");

        // --- 해당 여행의 여정 조회 및 출력 ---
        List<Itinerary> itineraries = itineraryService.getItinerariesByTravelId("1");
        System.out.println("저장된 여정 목록:");
        for (Itinerary i : itineraries) {
            System.out.println(i);
        }

        System.out.println("=== ItineraryTest 완료 ===");
    }
}
