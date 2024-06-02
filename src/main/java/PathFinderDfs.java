import com.google.common.graph.EndpointPair;
import com.google.common.graph.ValueGraph;

import java.util.*;


public class PathFinderDfs {
    private final Set<Node> visitedNodes;
    private final ValueGraph<Node, Integer> graph;

    public PathFinderDfs(ValueGraph<Node, Integer> valueGraph, Node node) {
        visitedNodes = new HashSet<>();
        this.graph = valueGraph;
        buildPath(valueGraph, node);
    }

    private void buildPath(ValueGraph<Node, Integer> valueGraph, Node node) {
        visitedNodes.add(node);
        for (Node graphNode : valueGraph.adjacentNodes(node)) {
            if (!visitedNodes.contains(graphNode)) {
                buildPath(valueGraph, graphNode);
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

    public int findAllPaths(Node startNode, Node endNode) {
        List<List<Node>> paths = new ArrayList<>();
        List<Node> path = new ArrayList<>();
        path.add(startNode);
        findAllPathsUtil(startNode, endNode, path, paths);
        // return paths;
        // TODO- from C to C there are negative weight edges
        return paths.size();
    }

    private void findAllPathsUtil(Node current, Node end, List<Node> path, List<List<Node>> paths) {
        if (current.equals(end)) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (Node node : graph.adjacentNodes(current)) {
            if (!path.contains(node)) {
                path.add(node);
                findAllPathsUtil(node, end, path, paths);
                path.remove(node);
            }
        }
    }

    /*
    The Bellman-Ford algorithm is a graph search algorithm that finds the shortest path between a given source vertex and all other vertices in the graph.
    It can handle graphs with negative weight edges, unlike Dijkstra's algorithm.
     */
    public int findShortestPathBellmanFord(Node startNode, Node endNode) {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> predecessor = new HashMap<>();

        for (Node node : graph.nodes()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(startNode, 0);

        for (int i = 1; i < graph.nodes().size(); i++) {
            for (EndpointPair<Node> edge : graph.edges()) {
                Node currentNode = edge.nodeU();
                Node nextNode = edge.nodeV();
                int weight = graph.edgeValueOrDefault(currentNode, nextNode, 0);
                if (distance.get(currentNode) != Integer.MAX_VALUE && distance.get(currentNode) + weight < distance.get(nextNode)) {
                    distance.put(nextNode, distance.get(currentNode) + weight);
                    predecessor.put(nextNode, currentNode);
                }
            }
        }
        for (EndpointPair<Node> edge : graph.edges()) {
            Node currentNode = edge.nodeU();
            Node nextNode = edge.nodeV();
            int weight = graph.edgeValueOrDefault(currentNode, nextNode, 0);
            if (distance.get(currentNode) != Integer.MAX_VALUE && distance.get(currentNode) + weight < distance.get(nextNode)) {
                throw new IllegalArgumentException("Graph contains a negative-weight cycle");
            }
        }
        return distance.get(endNode);
    }
}
