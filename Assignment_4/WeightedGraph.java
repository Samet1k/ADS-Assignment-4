import java.util.*;

public class WeightedGraph<T> {
    private final boolean directed;
    private final Map<T, List<Edge<T>>> adjList = new HashMap<>();

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(T source, T dest, double weight) {
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(source).add(new Edge<>(source, dest, weight));
        if (!directed) {
            adjList.get(dest).add(new Edge<>(dest, source, weight));
        }
    }

    public List<Edge<T>> getEdges(T vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<T> getVertices() {
        return adjList.keySet();
    }

    public static class Edge<T> {
        public final T source;
        public final T dest;
        public final double weight;

        public Edge(T source, T dest, double weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
