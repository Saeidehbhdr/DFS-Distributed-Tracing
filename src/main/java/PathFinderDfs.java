import com.google.common.graph.ValueGraph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PathFinderDfs {
    private final Set<Node> visitedNodes;
    private final ValueGraph<Node, Integer> graph;

    public PathFinderDfs(ValueGraph<Node, Integer> valueGraph, Node node) {
        visitedNodes = new HashSet<>();
        this.graph = valueGraph;
        dfs(valueGraph, node);
    }

    private void dfs(ValueGraph<Node, Integer> valueGraph, Node node) {
        visitedNodes.add(node);
        for (Node graphNode : valueGraph.adjacentNodes(node)) {
            if (!visitedNodes.contains(graphNode)) {
                dfs(valueGraph, graphNode);
            }
        }
    }

    public String findPathLatency(List<Node> path) {
        int totalLatency = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Node currentNode = path.get(i);
            Node nextNode = path.get(i + 1);
            if (graph.hasEdgeConnecting(currentNode, nextNode)) {
                totalLatency += graph.edgeValue(currentNode, nextNode).get();
            } else {
                return NodeStateEnum.INVALID.getMessage();
            }
        }
        return String.valueOf(totalLatency);
    }
}
