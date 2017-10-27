package regalloc;

import java.util.LinkedList;
import java.util.List;

public class Vertex {
    private final int id;
    private final List<Vertex> neighbours;

    public Vertex(int id) {
        this.id = id;
        this.neighbours = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Vertex v) {
        if (!neighbours.contains(v))
            neighbours.add(v);
    }

    public void removeNeighbour(Vertex v) {
        neighbours.remove(v);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }
}
