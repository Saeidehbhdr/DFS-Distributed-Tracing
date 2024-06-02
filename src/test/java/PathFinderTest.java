import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PathFinderTest {

    final Trace a = Trace.builder().id("A").build();
    final Trace b = Trace.builder().id("B").build();
    final Trace c = Trace.builder().id("C").build();
    final Trace d = Trace.builder().id("D").build();
    final Trace e = Trace.builder().id("E").build();

    // Create the graph
    final ValueGraph<Trace, Integer> graph = ValueGraphBuilder.directed()
            .<Trace, Integer>immutable()
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

        // Define the paths and call findPathLatency for each path
        List<List<Trace>> paths = Arrays.asList(
                Arrays.asList(a, b, c),
                Arrays.asList(a, d),
                Arrays.asList(a, d, c),
                Arrays.asList(a, e, b, c, d),
                Arrays.asList(a, e, d),
                Arrays.asList(c,e,b,c),
                Arrays.asList(a, c),
                Arrays.asList(b, b)
        );

        // Print the total latency for each path
        for (List<Trace> path : paths) {
            System.out.println(pathFinderDfs.findPathLatency(path));
        }
    }
}
