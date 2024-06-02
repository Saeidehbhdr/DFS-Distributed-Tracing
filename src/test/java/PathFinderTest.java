import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PathFinderTest {

    final Node a = Node.builder().id("A").build();
    final Node b = Node.builder().id("B").build();
    final Node c = Node.builder().id("C").build();
    final Node d = Node.builder().id("D").build();
    final Node e = Node.builder().id("E").build();

    // Create the graph
    final ValueGraph<Node, Integer> graph = ValueGraphBuilder.directed()
            .<Node, Integer>immutable()
            .putEdgeValue(a, b, 5)
            .putEdgeValue(b, c, 4)
            .putEdgeValue(c, d, 8)
            .putEdgeValue(d, c, 8)
            .putEdgeValue(d, e, 6)
            .putEdgeValue(a, d, 5)
            .putEdgeValue(c, e, 2)
            .putEdgeValue(e, b, 3)
            .putEdgeValue(a, e, 7).build();

    @Test
    void testExercise1() {
        PathFinderDfs pathFinderDfs = new PathFinderDfs(graph, a);

        // test input 1. through 5.
        // The average latency
        List<List<Node>> averagePaths = Arrays.asList(
                Arrays.asList(a, b, c),
                Arrays.asList(a, d),
                Arrays.asList(a, d, c),
                Arrays.asList(a, e, b, c, d),
                Arrays.asList(a, e, d)
        );
        for (List<Node> path : averagePaths) {
            System.out.println(pathFinderDfs.findPathLatency(path));
        }
        // test input 6. through 7.
        /* averagePaths.forEach(path -> System.out.println(pathFinderDfs.findAverageLatency(path)));
        // Print the traces originating in service C with maximum of 3 hops
        System.out.println(pathFinderDfs.findPathsWithMaxHops(c, c, 3));
        // Print the traces originating in service C with maximum of 3 hops
        System.out.println(pathFinderDfs.findPathsWithMaxHops(a, c, 4)); */

        // test input 8. through 9.
        // The length of the shortest trace (in terms of latency) between A and C.
        System.out.println(pathFinderDfs.findShortestPathBellmanFord(a, c));
        // The length of the shortest trace (in terms of latency) between B and B.
        // TODO- from B to B there are negative weight edges
        System.out.println(pathFinderDfs.findShortestPathBellmanFord(b, b));
        // test input 10.
        // The number of different traces from C to C
        System.out.println(pathFinderDfs.findAllPaths(c , c));
    }
}
