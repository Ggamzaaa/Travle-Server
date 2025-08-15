package itinerary.infra;

import common.infra.JsonGenerator;
import itinerary.domain.Itinerary;
import itinerary.domain.ItineraryRepository;
import java.io.*;
import java.util.*;

public class JsonItineraryRepository implements ItineraryRepository {
    private final String dirPath;
    private final JsonGenerator json;

    public JsonItineraryRepository(String dirPath) {
        this.dirPath = dirPath;
        this.json = new JsonGenerator();
        ensureDir();
    }

    public Itinerary save(Itinerary itinerary) {
        String filePath = filePathFor(itinerary.getTravelId());
        json.saveItinerary(itinerary, filePath);
        return itinerary;  // 저장 후 객체 반환
    }

    public List<Itinerary> findItinerariesByTravelId(int travelId) {
        String filePath = filePathFor(travelId);
        return json.loadItineraries(filePath, travelId);
    }

    private String filePathFor(int id) {
        return dirPath + File.separator + "travel_" + id + ".json";
    }

    private void ensureDir() {
        File d = new File(dirPath);
        if (!d.exists()) {
            d.mkdirs();
        }
    }
}
