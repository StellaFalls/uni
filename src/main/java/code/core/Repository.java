package code.core;

import java.util.List;

public interface Repository<T> {
    boolean insert(T objet);

    T getById(int id);

    List<T> selectAll();

    void update(T objet);

    void update1(T objet);

    void affect(T objet);

    List<T> selectBy(int x);

}