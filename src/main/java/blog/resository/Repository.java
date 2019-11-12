package blog.resository;

import java.util.List;

public interface Repository<E> {
    List<E> findAll();
    E findById(int id);
    void save (E model);
    void delete(int id);
}
