package travel.domain;

import java.time.LocalDate;
import java.util.Objects;

public final class Travel {
    private final int id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Travel(final int id, final String name, final LocalDate startDate, final LocalDate endDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("여행 이름은 필수입니다.");
        }
        final LocalDate s = Objects.requireNonNull(startDate, "시작 날짜는 필수입니다.");
        final LocalDate e = Objects.requireNonNull(endDate,   "종료 날짜는 필수입니다.");
        if (e.isBefore(s)) {
            throw new IllegalArgumentException("종료 날짜는 시작 날짜 이후여야 합니다.");
        }
        this.id = id;
        this.name = name.trim();
        this.startDate = s;
        this.endDate = e;
    }

    public int id(){
        return id;
    }
    public String name(){
        return name;
    }
    public LocalDate startDate(){
        return startDate;
    }
    public LocalDate endDate(){
        return endDate;
    }
}
