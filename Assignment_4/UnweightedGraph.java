import java.util.*;

public class UnweightedGraph<T> {
    private final boolean directed;
    private final Map<T, List<T>> adjList = new HashMap<>();

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(T source, T dest) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(source).add(dest);
        if (!directed) {
            adjList.get(dest).add(source);
        }
    }

    public List<T> getNeighbors(T vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<T> getVertices() {
        return adjList.keySet();
    }
}
