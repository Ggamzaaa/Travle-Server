package travel.application;

import common.domain.IdGenerator;
import travel.domain.Travel;

import java.time.LocalDate;
import java.util.Objects;

public class TravelFactory {
    private final IdGenerator idGen;

    public TravelFactory(IdGenerator idGen) {
        this.idGen = Objects.requireNonNull(idGen);
    }

    /** ID를 생성해서 완성된 Travel을 만들어 반환 */
    public Travel newTravel(String name, LocalDate start, LocalDate end) {
        int id = idGen.nextId();
        return new Travel(id, name, start, end);
    }
}
