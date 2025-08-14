package test.itinerary;

import common.domain.AtomicIdGenerator;
import itinerary.application.ItineraryFactory;
import itinerary.application.ItineraryService;
import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class ItineraryTest {
    public static void main(String[] args) {
        System.out.println("=== ItineraryTest 시작 ===");

        // 기존 파일 기반 Repository 사용
        File dir = new File("test-out/repo-1755154292693");
        ItineraryRepository repo = new ItineraryRepository(dir.getPath());
        int travelId = 1;
        int seed = repo.findItinerariesByTravelId(travelId).stream().mapToInt(Itinerary::getItineraryId).max().orElse(0);

        ItineraryFactory factory = new ItineraryFactory(new AtomicIdGenerator(seed));
        ItineraryService itineraryService = new ItineraryService(repo);

        // --- 여정 기록 ---
//        Itinerary i1 = factory.newItinerary(1, "서울", "제주",
//                LocalDateTime.parse("2025-08-11T11:00:00"),
//                LocalDateTime.parse("2025-08-13T11:00:00"),
//                LocalDateTime.parse("2025-08-14T11:00:00"),
//                LocalDateTime.parse("2025-08-15T11:00:00")
//        );
//        itineraryService.save(i1);

        Itinerary i2 = factory.newItinerary(1, "서울", "상하이",
                LocalDateTime.parse("2025-08-11T11:00:00"),
                LocalDateTime.parse("2025-08-13T11:00:00"),
                LocalDateTime.parse("2025-08-14T11:00:00"),
                LocalDateTime.parse("2025-08-15T11:00:00")
        );
        itineraryService.save(i2);
//
//        Itinerary i3 = factory.newItinerary(1, "서울", "부산",
//                LocalDateTime.parse("2025-08-12T09:00:00"),
//                LocalDateTime.parse("2025-08-12T12:00:00"),
//                LocalDateTime.parse("2025-08-12T14:00:00"),
//                LocalDateTime.parse("2025-08-12T18:00:00")
//        );
//        itineraryService.save(i3);

        // --- 해당 여행의 여정 조회 및 출력 ---
        List<Itinerary> itineraries = itineraryService.getItinerariesByTravelId(1);
        System.out.println("저장된 여정 목록:");
        for (Itinerary i : itineraries) {
            System.out.println(i);
        }

        System.out.println("=== ItineraryTest 완료 ===");
    }
}
