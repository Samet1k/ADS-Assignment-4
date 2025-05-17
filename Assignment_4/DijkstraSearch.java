import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, Double> distTo = new HashMap<>();
    private final Map<T, T> edgeTo = new HashMap<>();
    private final T start;

    public DijkstraSearch(WeightedGraph<T> graph, T start) {
        this.start = start;
        dijkstra(graph, start);
    }

    private void dijkstra(WeightedGraph<T> graph, T start) {
        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        for (T vertex : graph.getVertices()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
        pq.add(start);

        while (!pq.isEmpty()) {
            T current = pq.poll();
            for (WeightedGraph.Edge<T> edge : graph.getEdges(current)) {
                T neighbor = edge.dest;
                double newDist = distTo.get(current) + edge.weight;
                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return distTo.containsKey(key) && distTo.get(key) < Double.POSITIVE_INFINITY;
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
