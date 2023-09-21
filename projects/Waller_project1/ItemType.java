
/**
 * This class represents the item stored in a linked list node and supports some operations dealing with it.
 */
public class ItemType {
    // Attributes
    //The number stored in the node.
    private int value;

    // Methods
    public ItemType(int num) {
        value = num;
    } // ItemType
    /**
     *  Compares the value of item with the current object's
     * value and return -1 if value of the current object is less than value in item,
     * 0 if equal and 1 if greater.
     * @param item the item to compate to the current value
     * @return -1 if the current object is less than
     * the value of {@code item}, 0 if equal, and 1 if greater.
     */
    public int compareTo(ItemType item) {
        if (this.value > item.value) {
            return 1;
        } else if (this.value == item.value) {
            return 0;
        } else {
            return -1;
        } // if

    } // compareTo

    /**
     * Returns the value stored in a Node variable.
     * @return the Node's value
     */
    public int getValue() {
        return value;
    } // getValue

    /**
     * Initializes the data member by the {@code num} variable. Like a constructor.
     * @param num the value to initialize.
     */
    public void initializeValue(int num) {
        this.value = num;
    } // initializeValue

} // ItemType
