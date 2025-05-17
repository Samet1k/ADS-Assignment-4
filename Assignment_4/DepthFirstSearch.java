import java.util.*;

public class DepthFirstSearch<T> implements Search<T> {
    private final Set<T> visited = new HashSet<>();
    private final Map<T, T> edgeTo = new HashMap<>();
    private final T start;

    public DepthFirstSearch(UnweightedGraph<T> graph, T start) {
        this.start = start;
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<T> graph, T vertex) {
        visited.add(vertex);
        for (T neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                edgeTo.put(neighbor, vertex);
                dfs(graph, neighbor);
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return visited.contains(key);
    }

    @Override
    public List<T> pathTo(T key) {
        if (!hasPathTo(key)) return null;
        LinkedList<T> path = new LinkedList<>();
        for (T x = key; x != null && !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
