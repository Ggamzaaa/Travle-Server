package common.domain;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    T save(T t);
    List<T> findAll();
    Optional<T> findById(ID id);
}
