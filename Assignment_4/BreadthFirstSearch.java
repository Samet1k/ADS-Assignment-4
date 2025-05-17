import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private final Set<T> visited = new HashSet<>();
    private final Map<T, T> edgeTo = new HashMap<>();
    private final T start;

    public BreadthFirstSearch(UnweightedGraph<T> graph, T start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<T> graph, T start) {
        Queue<T> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            for (T neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.offer(neighbor);
                }
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
