package common.domain;

public interface Repository<T, ID> {
    T save(T t);
}
