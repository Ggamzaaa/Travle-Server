package travel.infra;

import common.infra.JsonGenerator;
import travel.domain.Travel;
import travel.domain.TravelRepository;

import java.io.File;
import java.util.*;

public class JsonTravelRepository implements TravelRepository {
    private final String dirPath;
    private final JsonGenerator json;

    public JsonTravelRepository(String dirPath) {
        this.dirPath = dirPath;
        this.json = new JsonGenerator();
        ensureDir();
    }

    @Override
    public synchronized Travel save(Travel travel) {
        json.saveTravelMeta(travel, filePathFor(travel.getId()));
        return travel;
    }

    @Override
    public Optional<Travel> findById(int id) {
        return Optional.ofNullable(json.loadTravelMeta(filePathFor(id)));
    }

    @Override
    public synchronized List<Travel> findAll() {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, n) -> n.startsWith("travel_") && n.endsWith(".json"));
        List<Travel> out = new ArrayList<>();
        if (files != null) {
            for (File f : files) {
                Travel t = json.loadTravelMeta(f.getPath());
                if (t != null) out.add(t);
            }
        }
        out.sort(Comparator.comparingInt(Travel::getId));
        return out;
    }

    private String filePathFor(int id) { return dirPath + File.separator + "travel_" + id + ".json"; }
    private void ensureDir() { File d = new File(dirPath); if (!d.exists()) d.mkdirs(); }
}
