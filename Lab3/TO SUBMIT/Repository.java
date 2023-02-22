import java.util.List;

public interface Repository<T> {
    boolean add(T entity);
    T get(long id);
    List<T> getAll();
    boolean remove(long id);
    boolean remove(T entity);
    boolean update(T entity);
}