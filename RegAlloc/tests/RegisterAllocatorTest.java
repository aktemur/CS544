import org.junit.Test;
import regalloc.*;
import java.util.*;

import static junit.framework.TestCase.*;

public class RegisterAllocatorTest {
    private static final int COLOR_0 = 0;
    private static final int COLOR_1 = 1;
    private static final int COLOR_2 = 2;

    private static Graph graph1() {
        // This is the graph in lecture notes, slide #7
        Graph graph = new Graph(5);
        graph.connectVertices(0, 1);
        graph.connectVertices(0, 2);
        graph.connectVertices(1, 3);
        graph.connectVertices(1, 4);
        graph.connectVertices(2, 3);
        graph.connectVertices(2, 4);
        graph.connectVertices(3, 4);
        return graph;
    }

    private static Graph graph2() {
        // This is the graph in lecture notes, slide #20
        Graph graph = new Graph(4);
        graph.connectVertices(0, 1);
        graph.connectVertices(0, 2);
        graph.connectVertices(1, 3);
        graph.connectVertices(2, 3);
        return graph;
    }

    private static Graph graph3() {
        // This is similar to the graph in lecture notes, slide #20,
        // with an additional edge
        Graph graph = graph2();
        graph.connectVertices(1, 2);
        return graph;
    }

    private static Graph graph4() {
        Graph graph = new Graph(6);
        graph.connectVertices(0, 1);
        graph.connectVertices(0, 2);
        graph.connectVertices(0, 4);
        graph.connectVertices(1, 2);
        graph.connectVertices(1, 3);
        graph.connectVertices(1, 5);
        graph.connectVertices(2, 3);
        graph.connectVertices(2, 4);
        graph.connectVertices(3, 4);
        graph.connectVertices(3, 5);
        return graph;
    }

    private static Graph graph5() {
        Graph graph = graph4();
        graph.connectVertices(2, 5);
        return graph;
    }

    private static Graph graph6() {
        Graph graph = graph5();
        graph.connectVertices(3, 0);
        return graph;
    }


    @Test
    public void testGraph1() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(3);
        allocator.runOn(graph1());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        // This is NOT the exact coloring on slide #16
        // because due to the heuristic, we should push vertex #2
        // into the stack before #3.
        assertEquals(COLOR_0, map.get(0).intValue());
        assertEquals(COLOR_2, map.get(1).intValue());
        assertEquals(COLOR_2, map.get(2).intValue());
        assertEquals(COLOR_1, map.get(3).intValue());
        assertEquals(COLOR_0, map.get(4).intValue());

        assertTrue(spills.isEmpty());
    }

    @Test
    public void testGraph2() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(2);
        allocator.runOn(graph2());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        // This is the coloring in lecture notes, slide #30
        assertEquals(COLOR_0, map.get(0).intValue());
        assertEquals(COLOR_1, map.get(1).intValue());
        assertEquals(COLOR_1, map.get(2).intValue());
        assertEquals(COLOR_0, map.get(3).intValue());

        assertTrue(spills.isEmpty());
    }


    @Test
    public void testGraph3() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(2);
        allocator.runOn(graph3());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        assertEquals(COLOR_0, map.get(0).intValue());
        assertEquals(COLOR_1, map.get(2).intValue());
        assertEquals(COLOR_0, map.get(3).intValue());

        assertEquals(1, spills.size());
        assertEquals(1, spills.get(0).intValue());
    }

    @Test
    public void testGraph41() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(3);
        allocator.runOn(graph4());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        assertEquals(COLOR_1, map.get(0).intValue());
        assertEquals(COLOR_0, map.get(1).intValue());
        assertEquals(COLOR_2, map.get(2).intValue());
        assertEquals(COLOR_1, map.get(3).intValue());
        assertEquals(COLOR_0, map.get(4).intValue());
        assertEquals(COLOR_2, map.get(5).intValue());

        assertEquals(0, spills.size());
    }

    @Test
    public void testGraph5() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(3);
        allocator.runOn(graph5());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        assertEquals(COLOR_1, map.get(0).intValue());
        assertEquals(COLOR_2, map.get(1).intValue());
        assertEquals(COLOR_1, map.get(3).intValue());
        assertEquals(COLOR_0, map.get(4).intValue());
        assertEquals(COLOR_0, map.get(5).intValue());

        assertEquals(1, spills.size());
        assertEquals(2, spills.get(0).intValue());
    }

    @Test
    public void testGraph6() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(3);
        allocator.runOn(graph6());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        assertEquals(COLOR_0, map.get(0).intValue());
        assertEquals(COLOR_2, map.get(1).intValue());
        assertEquals(COLOR_1, map.get(3).intValue());
        assertEquals(COLOR_2, map.get(4).intValue());
        assertEquals(COLOR_0, map.get(5).intValue());

        assertEquals(1, spills.size());
        assertEquals(2, spills.get(0).intValue());
    }

    @Test
    public void testGraph42() throws Exception {
        RegisterAllocator allocator = new RegisterAllocator(2);
        allocator.runOn(graph4());
        Map<Integer, Integer> map = allocator.getRegisterMap();
        List<Integer> spills = allocator.getSpilledValues();

        assertEquals(COLOR_1, map.get(0).intValue());
        assertEquals(COLOR_1, map.get(3).intValue());
        assertEquals(COLOR_0, map.get(4).intValue());
        assertEquals(COLOR_0, map.get(5).intValue());

        assertEquals(2, spills.size());
        assertTrue(spills.contains(1));
        assertTrue(spills.contains(2));
    }
}