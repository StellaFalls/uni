package code.core;

import java.util.List;

public interface View<T> {
     T instance();

     void show(List<T> list);

}