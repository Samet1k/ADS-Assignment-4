import java.util.List;

public interface Search<T> {
    boolean hasPathTo(T key);
    List<T> pathTo(T key);
}
