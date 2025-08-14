package common.infra;

import travel.domain.Travel;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonGenerator {

    public void saveTravelMeta(Travel t, String filePath) {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            out.println("{");
            out.printf("  \"trip_id\": %d,%n", t.id());
            out.printf("  \"trip_name\": \"%s\",%n", escape(t.name()));
            out.printf("  \"start_date\": \"%s\",%n", t.startDate()); // yyyy-MM-dd
            out.printf("  \"end_date\": \"%s\"%n", t.endDate());
            out.println("}");
        } catch (IOException e) {
            throw new RuntimeException("여행 저장 실패: " + filePath, e);
        }
    }

    public Travel loadTravelMeta(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) return null;
        try {
            String s = new String(java.nio.file.Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8)
                    .replace("\n","").replace("\r","").trim();
            if (s.isEmpty()) return null;

            int id = intVal(s, "\"trip_id\":");
            String name = strVal(s, "\"trip_name\":"); // 이 함수가 이스케이프 해제까지 처리
            var start = java.time.LocalDate.parse(strVal(s, "\"start_date\":"));
            var end   = java.time.LocalDate.parse(strVal(s, "\"end_date\":"));
            return new Travel(id, name, start, end);
        } catch (Exception e) {
            throw new RuntimeException("여행 로드 실패: " + filePath, e);
        }
    }

    /* ----------------- 내부 유틸 ----------------- */

    /** 저장 시 이스케이프: 역슬래시와 큰따옴표만 처리 */
    private static String escape(String s){
        return s.replace("\\","\\\\").replace("\"","\\\"");
    }

    /** 숫자 값 파싱(쉼표나 중괄호 전까지) */
    private static int intVal(String s, String key){
        int i = s.indexOf(key); if (i < 0) return 0;
        i += key.length();
        int j = s.indexOf(",", i); if (j < 0) j = s.indexOf("}", i);
        return Integer.parseInt(s.substring(i, j).replaceAll("[^0-9-]","").trim());
    }

    /**
     * 따옴표로 둘러싸인 문자열 값 파싱.
     * - 이스케이프된 문자 처리: \", \\, \n, \r, \t (필요시 확장 가능)
     * - 종료 따옴표는 "비-이스케이프" 따옴표여야 함.
     */
    private static String strVal(String s, String key){
        int i = s.indexOf(key);
        if (i < 0) return "";
        i += key.length();

        int startQuote = s.indexOf('"', i);
        if (startQuote < 0) return "";

        StringBuilder sb = new StringBuilder();
        boolean escape = false;

        for (int idx = startQuote + 1; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (escape) {
                switch (c) {
                    case '"'  -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case 'n'  -> sb.append('\n');
                    case 'r'  -> sb.append('\r');
                    case 't'  -> sb.append('\t');
                    // 필요하면 'b','f','uXXXX' 등 추가
                    default   -> sb.append(c);
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
