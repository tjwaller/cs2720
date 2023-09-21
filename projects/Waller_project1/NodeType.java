
/**
 * Node object that stores an ItemType object as well
 * as the memory location of the next node.
 */
public class NodeType {

    //The item stored in this node object.
    public ItemType info;
    //A refrence to the next node.
    public NodeType next;


    // only here for convention since next is public
    public NodeType(ItemType info, NodeType next) {
        this.info = info;
        this.next = next;
    } // Node

    public NodeType(ItemType info) {
        this.info = info;
        next = null;
    } // Node

    public void setNext(NodeType next) {
        this.next = next;
    } // setNext

    public NodeType getNext() {
        return next;
    } // getNext

    public int getValue() {
        return info.getValue();
    } // getElement



} // NodeType
