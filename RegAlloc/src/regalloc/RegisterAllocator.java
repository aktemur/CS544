package regalloc;

import java.util.List;
import java.util.Map;

/**
 * Register allocator based on Chaitin-Briggs algorithm.
 *
 * To be deterministic, follow the heuristic below:
 *
 * For Step 1 and 2 of the algorithm, when picking a vertex
 * to push into the stack, out of the viable candidates,
 * choose the one that has the maximum number of neighbours.
 * In case of a tie, pick the vertex that has the lowest id.
 *
 */
public class RegisterAllocator {
    public RegisterAllocator(int numRegisters) {

    }

    public void runOn(Graph graph) {

    }

    // Returns a mapping from vertex ids to register ids
    public Map<Integer, Integer> getRegisterMap() {
        return null;
    }

    // Returns the ids of the vertices that had to be spilled
    public List<Integer> getSpilledValues() {
        return null;
    }
}
