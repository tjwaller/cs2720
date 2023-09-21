public class NodeType<T extends Comparable<T>> {

    public T info;

    public NodeType<T> left;

    public NodeType<T> right;

    public NodeType(T info) {
        this.info = info;
        left = null;
        right = null;
    }

    public NodeType() {
        info = null;
        left = null;
        right = null;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }
}
