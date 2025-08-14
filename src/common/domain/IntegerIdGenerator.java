package common.domain;

public class IntegerIdGenerator implements IdGenerator<Integer> {
    @Override
    public Integer generateId(Integer prevId) {
        return prevId + 1;
    }
}
