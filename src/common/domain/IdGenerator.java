package common.domain;

public interface IdGenerator<T> {
    T generateId(T prevId);
}
