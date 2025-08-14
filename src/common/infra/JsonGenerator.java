package common.infra;

import itinerary.domain.Itinerary;
import travel.domain.Travel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonGenerator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public void saveTravelMeta(Travel t, String filePath) {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            out.println("{");
            out.printf("  \"trip_id\": %d,%n", t.getId());
            out.printf("  \"trip_name\": \"%s\",%n", escape(t.getName()));
            out.printf("  \"start_date\": \"%s\",%n", t.getStartDate()); // yyyy-MM-dd
            out.printf("  \"end_date\": \"%s\"%n", t.getEndDate());
            out.println("}");
        } catch (IOException e) {
            throw new RuntimeException("여행 저장 실패: " + filePath, e);
        }
    }

    public void saveItinerary(Itinerary itinerary, String filePath) {
        // 1. Travel 불러오기
        Travel travel = loadTravelMeta(filePath);
        if (travel == null) {
            throw new IllegalArgumentException("존재하지 않는 여행입니다");
        }

        // 2. 기존 itineraries 불러오기
        List<Itinerary> itineraries = loadItineraries(filePath, travel.getId());
        itineraries.add(itinerary);

        // 3. 전체 JSON 다시 쓰기
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {

            out.println("{");
            out.printf("  \"trip_id\": %d,%n", travel.getId());
            out.printf("  \"trip_name\": \"%s\",%n", escape(travel.getName()));
            out.printf("  \"start_date\": \"%s\",%n", travel.getStartDate());
            out.printf("  \"end_date\": \"%s\",%n", travel.getEndDate());
            out.println("  \"itineraries\": [");

            for (int i = 0; i < itineraries.size(); i++) {
                Itinerary it = itineraries.get(i);
                out.printf(
                        "    { \"itinerary_id\": %d, \"departure_place\": \"%s\", \"destination\": \"%s\", \"departure_time\": \"%s\", \"arrival_time\": \"%s\", \"check_in\": \"%s\", \"check_out\": \"%s\" }%s%n",
                        it.getItineraryId(),
                        it.getDeparturePlace(),
                        it.getDestination(),
                        it.getDepartureTime() == null ? "" : it.getDepartureTime().toString(),
                        it.getArrivalTime() == null ? "" : it.getArrivalTime().toString(),
                        it.getCheckIn() == null ? "" : it.getCheckIn().toString(),
                        it.getCheckOut() == null ? "" : it.getCheckOut().toString(),
                        i < itineraries.size() - 1 ? "," : ""
                );
            }

            out.println("  ]");
            out.println("}");
        } catch (IOException e) {
            throw new RuntimeException("Itinerary 저장 실패", e);
        }
    }

    public Travel loadTravelMeta(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            return null;
        }
        try {
            String s = new String(java.nio.file.Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8)
                    .replace("\n", "").replace("\r", "").trim();
            if (s.isEmpty()) {
                return null;
            }

            int id = intVal(s, "\"trip_id\":");
            String name = strVal(s, "\"trip_name\":"); // 이 함수가 이스케이프 해제까지 처리
            var start = java.time.LocalDate.parse(strVal(s, "\"start_date\":"));
            var end = java.time.LocalDate.parse(strVal(s, "\"end_date\":"));
            return new Travel(id, name, start, end);
        } catch (Exception e) {
            throw new RuntimeException("여행 로드 실패: " + filePath, e);
        }
    }

    public List<Itinerary> loadItineraries(String filePath, int travelId) {
        File f = new File(filePath);
        if (!f.exists()) {
            return new ArrayList<>();
        }

        try {
            String s = new String(java.nio.file.Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8)
                    .replace("\n", "").replace("\r", "").trim();
            if (s.isEmpty()) {
                return new ArrayList<>();
            }

            List<Itinerary> itineraries = new ArrayList<>();

            // itineraries 배열 추출
            int start = s.indexOf("\"itineraries\":");
            if (start < 0) {
                return itineraries; // 없으면 빈 리스트
            }
            start = s.indexOf("[", start);
            int end = s.indexOf("]", start);
            if (start < 0 || end < 0) {
                return itineraries;
            }

            String arrayContent = s.substring(start + 1, end);

            // 각 객체 분리
            String[] items = arrayContent.split("\\},\\s*\\{");
            for (String item : items) {
                item = item.replace("{", "").replace("}", "").trim();
                if (item.isEmpty()) {
                    continue;
                }

                int itineraryId = intVal(item, "\"itinerary_id\":");
                String departurePlace = strVal(item, "\"departure_place\":");
                String destination = strVal(item, "\"destination\":");
                LocalDateTime departureTime = parseDateTime(strVal(item, "\"departure_time\":"));
                LocalDateTime arrivalTime = parseDateTime(strVal(item, "\"arrival_time\":"));
                LocalDateTime checkIn = parseDateTime(strVal(item, "\"check_in\":"));
                LocalDateTime checkOut = parseDateTime(strVal(item, "\"check_out\":"));

                // travelId는 JSON에서 추출 가능하면 넣고, 없으면 0
                itineraries.add(new Itinerary(itineraryId, travelId, departurePlace, destination,
                        departureTime, arrivalTime, checkIn, checkOut));

            }

            return itineraries;

        } catch (Exception e) {
            throw new RuntimeException("Itinerary 로드 실패: " + filePath, e);
        }
    }

    /* ----------------- 내부 유틸 ----------------- */

    /**
     * 저장 시 이스케이프: 역슬래시와 큰따옴표만 처리
     */
    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }


    private LocalDateTime parseDateTime(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        return LocalDateTime.parse(s, formatter);
    }

    /**
     * 숫자 값 파싱(쉼표나 중괄호 전까지)
     */
    private static int intVal(String s, String key) {
        int i = s.indexOf(key);
        if (i < 0) {
            return 0;
        }
        i += key.length();
        int j = s.indexOf(",", i);
        if (j < 0) {
            j = s.indexOf("}", i);
        }
        return Integer.parseInt(s.substring(i, j).replaceAll("[^0-9-]", "").trim());
    }

    /**
     * 따옴표로 둘러싸인 문자열 값 파싱. - 이스케이프된 문자 처리: \", \\, \n, \r, \t (필요시 확장 가능) - 종료 따옴표는 "비-이스케이프" 따옴표여야 함.
     */
    private static String strVal(String s, String key) {
        int i = s.indexOf(key);
        if (i < 0) {
            return "";
        }
        i += key.length();

        int startQuote = s.indexOf('"', i);
        if (startQuote < 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean escape = false;

        for (int idx = startQuote + 1; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (escape) {
                switch (c) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    // 필요하면 'b','f','uXXXX' 등 추가
                    default -> sb.append(c);
                }
                escape = false;
            } else {
                if (c == '\\') {
                    escape = true;
                } else if (c == '"') {
                    // 비-이스케이프 종료 따옴표 → 파싱 종료
                    return sb.toString();
                } else {
                    sb.append(c);
                }
            }
        }
        // 비정상 종료 시 지금까지 누적값 반환
        return sb.toString();
    }
}
