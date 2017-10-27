package regalloc;

import java.util.*;

public class Graph {
    private final List<Vertex> vertices;

    public Graph(int numVertices) {
        vertices = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertex(i));
        }
    }

    public void connectVertices(int vertexId1, int vertexId2) {
        Vertex v1 = vertices.get(vertexId1);
        Vertex v2 = vertices.get(vertexId2);
        v1.addNeighbour(v2);
        v2.addNeighbour(v1);
    }

    public void disconnectVertices(int vertexId1, int vertexId2) {
        Vertex v1 = vertices.get(vertexId1);
        Vertex v2 = vertices.get(vertexId2);
        v1.removeNeighbour(v2);
        v2.removeNeighbour(v1);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Graph clone() {
        Graph clone = new Graph(vertices.size());
        for (Vertex v1 : vertices) {
            for (Vertex v2 : v1.getNeighbours()) {
                clone.connectVertices(v1.getId(), v2.getId());
            }
        }
        return clone;
    }
}
