package test.common.infra;

import common.infra.JsonGenerator;
import test.util.TestUtil;
import travel.domain.Travel;

import java.io.File;
import java.time.LocalDate;

public class JsonGeneratorTest {
    public static void main(String[] args) {
        TestUtil.printHeader("JsonGeneratorTest");
        File dir = TestUtil.newTempDir("json-gen");
        String path = new File(dir, "travel_1.json").getPath();
        TestUtil.printPath("저장 파일", path);

        JsonGenerator gen = new JsonGenerator();
        Travel src = new Travel(1, "상하이 with \"깜자\"  테스트",
                LocalDate.parse("2025-08-13"), LocalDate.parse("2025-08-16"));

        System.out.println("저장할 객체:");
        TestUtil.printTravel(src);

        gen.saveTravelMeta(src, path);
        Travel loaded = gen.loadTravelMeta(path);

        System.out.println("로드된 객체:");
        TestUtil.printTravel(loaded);

        TestUtil.assertEquals(src.getId(), loaded.getId(), "id");
        TestUtil.assertEquals(src.getName(), loaded.getName(), "name");
        TestUtil.assertEquals(src.getStartDate(), loaded.getStartDate(), "start");
        TestUtil.assertEquals(src.getEndDate(), loaded.getEndDate(), "end");

        TestUtil.pass("JsonGeneratorTest");
    }
}
