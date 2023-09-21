// An Edge is an object that stores it's own label as well
// As the label of the verex it points to
public class Edge {
    public String destLabel;
    public String label;

    // Creates an edge using its label and the vertex label
    public Edge(String destLabel, String label) {
        this.destLabel = destLabel;
        this.label = label;
    }

    // Returns the destination label
    public String getDestLabel() {
        return destLabel;
    }

    // returns the edge label
    public String getLabel() {
        return label;
    }
}
