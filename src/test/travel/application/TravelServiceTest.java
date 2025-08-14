package test.travel.application;

import common.domain.AtomicIdGenerator;
import test.util.TestUtil;
import travel.application.TravelFactory;
import travel.application.TravelServiceImpl;
import travel.domain.Travel;
import travel.domain.TravelRepository;
import travel.infra.JsonTravelRepository;

import java.io.File;
import java.time.LocalDate;

public class TravelServiceTest {
    public static void main(String[] args) {
        TestUtil.printHeader("TravelServiceTest");
        File dir = TestUtil.newTempDir("svc");
        TestUtil.printPath("레포 디렉토리", dir.getPath());

        TravelRepository repo = new JsonTravelRepository(dir.getPath());
        int seed = repo.findAll().stream().mapToInt(Travel::getId).max().orElse(0);
        var factory = new TravelFactory(new AtomicIdGenerator(seed));
        var svc = new TravelServiceImpl(repo);

        System.out.println("생성 2건:");
        Travel t1 = svc.save(factory.newTravel("여행 A", LocalDate.parse("2025-08-19"), LocalDate.parse("2025-08-31")));
        Travel t2 = svc.save(factory.newTravel("여행 B", LocalDate.parse("2025-09-01"), LocalDate.parse("2025-09-05")));
        TestUtil.printTravel(t1);
        TestUtil.printTravel(t2);
        TestUtil.assertEquals(1, t1.getId(), "first id");
        TestUtil.assertEquals(2, t2.getId(), "second id");

        System.out.println("현재 전체 목록:");
        TestUtil.printList(repo.findAll());

        // 재시작 시나리오
        int seed2 = repo.findAll().stream().mapToInt(Travel::getId).max().orElse(0); // 2
        var factory2 = new TravelFactory(new AtomicIdGenerator(seed2));
        Travel t3 = svc.save(factory2.newTravel("여행 C", LocalDate.parse("2025-10-01"), LocalDate.parse("2025-10-03")));
        TestUtil.printTravel(t3);
        TestUtil.assertEquals(3, t3.getId(), "restart seed next id");

        System.out.println("최종 전체 목록:");
        TestUtil.printList(repo.findAll());
        TestUtil.pass("TravelServiceTest");
    }
}
