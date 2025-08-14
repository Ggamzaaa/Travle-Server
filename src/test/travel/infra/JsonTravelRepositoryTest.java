package test.travel.infra;

import test.util.TestUtil;
import travel.domain.Travel;
import travel.domain.TravelRepository;
import travel.infra.JsonTravelRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class JsonTravelRepositoryTest {
    public static void main(String[] args) {
        TestUtil.printHeader("JsonTravelRepositoryTest");
        File dir = TestUtil.newTempDir("repo");
        TestUtil.printPath("레포 디렉토리", dir.getPath());

        TravelRepository repo = new JsonTravelRepository(dir.getPath());

        System.out.println("초기 전체 목록:");
        TestUtil.printList(repo.findAll());
        TestUtil.assertTrue(repo.findAll().isEmpty(), "initial empty");

        // 저장(2 먼저, 그 다음 1)
        Travel t2 = new Travel(2, "대전 여행", LocalDate.parse("2025-08-19"), LocalDate.parse("2025-08-31"));
        Travel t1 = new Travel(1, "상하이 여행", LocalDate.parse("2025-08-13"), LocalDate.parse("2025-08-16"));
        repo.save(t2);
        repo.save(t1);

        System.out.println("findById(1):");
        Travel found = repo.findById(1).orElseThrow();
        TestUtil.printTravel(found);
        TestUtil.assertEquals("상하이 여행", found.name(), "name");

        System.out.println("정렬된 전체 목록:");
        List<Travel> all = repo.findAll();
        TestUtil.printList(all);
        TestUtil.assertEquals(2, all.size(), "all size");
        TestUtil.assertEquals(1, all.get(0).id(), "sorted[0]");
        TestUtil.assertEquals(2, all.get(1).id(), "sorted[1]");

        TestUtil.pass("JsonTravelRepositoryTest");
    }
}
