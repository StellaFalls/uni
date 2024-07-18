package code.core;

import java.util.List;

public interface Service<T> {
    
    void save(T objet);

    T find(int id);

    List<T> show();

    void update(T objet);

    void update1(T objet);

    void affect(T objet);

    List<T> selectBy(int x);

}