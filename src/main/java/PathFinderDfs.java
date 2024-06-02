import com.google.common.graph.ValueGraph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class will contain a boolean array to keep track of visited nodes and a method to perform the DFS.
 */
public class PathFinderDfs {
    private final Set<Trace> visited;
    private final ValueGraph<Trace, Integer> graph;

    public PathFinderDfs(ValueGraph<Trace, Integer> G, Trace s) {
        visited = new HashSet<>();
        this.graph = G;
        dfs(G, s);
    }

    private void dfs(ValueGraph<Trace, Integer> G, Trace v) {
        visited.add(v);
        for (Trace w : G.adjacentNodes(v)) {
            if (!visited.contains(w)) {
                dfs(G, w);
            }
        }
    }

    public String findPathLatency(List<Trace> path) {
        int totalLatency = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Trace current = path.get(i);
            Trace next = path.get(i + 1);
            if (graph.hasEdgeConnecting(current, next)) {
                totalLatency += graph.edgeValue(current, next).get();
            } else {
                return TraceStateEnum.INVALID.getMessage();
            }
        }
        return String.valueOf(totalLatency);
    }
}
