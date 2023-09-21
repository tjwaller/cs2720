import java.util.*;

// A vertex is an object that has a label, a next vertex, and
// Each vertex stores a linked list of every edge that it points to
public class Vertex {
    public String label;
    public LinkedList<Edge> edges;
    public Vertex next;

    // Creates a new vertex from a string
    public Vertex(String label) {
        this.label = label;
        edges = new LinkedList<>();
        next = null;
    }

    // Creates a new edge using it's own label as well as the label of the vertex it points to
    // Note that this method only has two arguments as apposed to the one in Graph.java
    public void addEdge(String destLabel, String edgeLabel) {
        edges.add(new Edge(destLabel, edgeLabel));
    }

    // Checks if the current vertex points to the specified vertex
    public boolean hasEdge(String destLabel) {
        for (Edge edge : edges) {
            if (edge.destLabel.equals(destLabel)) {
                return true;
            }
        }
        return false;
    }

        // gets an edge using the destination label for that edge
        public String getEdge(String destLabel) {
            for (Edge edge : edges) {
                if (edge.destLabel.equals(destLabel)) {
                    return edge.label;
                }
            }
            return "";
        }
}
