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

//    public int getNextItineraryNumber(int travelId) {
//        List<Itinerary> existingItineraries = findItinerariesByTravelId(travelId);
//        return existingItineraries.size() + 1;
//    }

    @Override
    public List<Itinerary> findAll() {
        // 모든 여행 ID에 대해 여정을 수집
        List<Itinerary> allItineraries = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.startsWith("travel_") && name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    String travelIdStr = fileName.substring(7, fileName.length() - 5); // "travel_" 제거하고 ".json" 제거
                    try {
                        int travelId = Integer.parseInt(travelIdStr);
                        List<Itinerary> itineraries = findItinerariesByTravelId(travelId);
                        allItineraries.addAll(itineraries);
                    } catch (NumberFormatException e) {
                        // 파일명 파싱 실패 시 건너뛰기
                    }
                }
            }
        }
        return allItineraries;
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
