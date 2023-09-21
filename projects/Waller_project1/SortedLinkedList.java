import java.util.Arrays;
import java.util.Scanner;
/**
 * This class represents a Sorted Linked List, and handles operations involved with them.
 */
public class SortedLinkedList {
    // Attributes
    private NodeType head;
    private NodeType currentPos;
    private NodeType previousPos;
    private NodeType newest;

    // Methods
    /**
     * Initialize a SortedLinkedList object.
     * I would assume right off that this method would sort a list and take away duplicates.
     */
    public SortedLinkedList(String fileContents) {
        if (fileContents.trim() == "") {
            head = null;
        } else {
            //sort the array
            //store each element into nodes and have head point to the first NodeType.
            int[] array = stringToArray(fileContents);

            // create a linked List
            for (int i = 0; i < array.length; i++) {
                ItemType item = new ItemType(array[i]); // create a new ItemType
                //item.initializeValue(array[i]); // initialize its value
                insertItem(item); // insert it into the linkedList
            } // for

        } // else

    } // SortedLinkedList

    /**
     * Returns the length of the linked List.
     * @return length
     */
    public int getLength() {
        int length = 0;
        currentPos = head;
        while (currentPos != null) {
            currentPos = currentPos.next;
            length++;
        } // while
        return length;
    } // getLength


    /**
     * inserts an item maintaining the ascending order.
     * @returns 1 if successful, -1 if item is a duplicate.
     */
    // if you cant find a way to get it to return the fact that something when wrong, you can always make it throw an error
    public int insertItem(ItemType item) {

        if (getLength() == 0) { // or if head == null
            head = new NodeType(item);
            return 1;
        } else {
            currentPos = head;
            if (item.getValue() < currentPos.getValue()) { // if the item being inserted is already smaller than the first one
                head = new NodeType(item, currentPos);
                return 1;
            } else if (currentPos.getValue() == item.getValue()) {
                return -1;
            } else { // if the list already contains elements, and we moved past the first

                currentPos = head.next;
                previousPos = head;
                boolean loopRunning = true;
                for (int i = 0; loopRunning; i++) { // for every element in the list
                    if (currentPos == null) { // You're at the end of the list
                        previousPos.next = new NodeType(item, currentPos);
                        loopRunning = false;
                        return 1;
                    } else if (currentPos.getValue() == item.getValue()) { // item is duplicate
                        loopRunning = false;
                        return -1;
                    } else if (item.getValue() < currentPos.getValue()) { // if you just passed it
                        previousPos.next = new NodeType(item, currentPos);
                        return 1;
                    } else { // you arent there yet
                        currentPos = currentPos.next;
                        previousPos = previousPos.next;
                    }
                } // for every element in the list

            } // if (the first element is larget than the one being inserted or not

        } // if (list is empty or not)
        System.out.println("Somehow the code made it to the end of insertItem");
        return 1;
    } // insertItem

    /**
     * Deletes the requested node in the list.
     *
     * @param item The node in the list that contains an item equal to
     * the item parameter should be removed.
     */
    public void deleteItem(ItemType item) {
        // General Cases: seleting last element or a middle element.
        // Special Cases: Deleting the only element, a nonexexting element, "Item not found", deleting from empty list, "You cannot delete from an empty list".
        String original = toString();
        if (head == null) {
            System.out.println("You cannot delete from an empty list");
        } else if (searchItem(item) == -1) {
            //System.out.println("item not found");
        } else if (head != null && head.next == null) {
            head = null;
            System.out.println("Original list : " + original);
            System.out.println("New list : " + toString());
        } else if (searchItem(item) == 0) { // you need to delete the first item
            head = head.next;
            System.out.println("Original list : " + original);
            System.out.println("New list : " + toString());
        } else {
            currentPos = head.next;
            previousPos = head;
            while (currentPos.getValue() != item.getValue()) {
                previousPos = currentPos;
                currentPos = currentPos.next;
            } // while
            previousPos.next = currentPos.next;
            System.out.println("Original list : " + original);
            System.out.println("New list : " + toString());
        } // if else

    } // deleteItem

    /**
     *  Search the linked list that contains an item equal to
     *  the parameter item and return its index. Print “Item not found” if the item is not present
     *  in the list.
     *
     * @param item  the ItemType to find the index of
     * @return the index of item
     */
    public int searchItem(ItemType item) {
        int returnValue = -1;
        int index = 0;
        currentPos = head;
        while (currentPos != null) {
            if (currentPos.getValue() == item.getValue()) {
                return index;
            } else {
                index++;
                currentPos = currentPos.next;
            } // if else
        } // while
        System.out.println("Item not found");
        return -1;
    } // searchItem

    /**
     * This function returns the next item in the list pointed by the
     * currentPos.
     * @param item
     * @return
     */
    public ItemType getNextItem() {
        if (head == null) { // list is empty
            System.out.println("The list is empty");
        } else if (currentPos == null) { // end of list reached
            currentPos = head;
            return currentPos.info;
        } else {
            currentPos = currentPos.next;

            if (currentPos == null) {
                currentPos = head;
            } // if
            return currentPos.info;
        } // if
        ItemType didntWork = new ItemType(-1);
        return didntWork;
    } // getNextItem

    /**
     * Initializes the currentPos to null.
     */
    public void resetList() {
        currentPos = null;
    } // resetList

    /**
     * merges the origional list and a second list.
     * It should return another linked list which is a merged version of the other two.
     */
    public void mergeList() {
        // Get user input
        Scanner scanner = new Scanner(System.in);
        String original = toString();
        System.out.print("Enter the length of the new list: ");
        int newLength = scanner.nextInt();
        System.out.print("Enter the numbers: ");
        int[] array = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            array[i] = scanner.nextInt();
        } // for

        Arrays.sort(array);

        // Save the new list of numbers to a string
        String str = "";


        // add the new list of numbers to the old one
        for (int i = 0; i < array.length; i++) {
            str = str + " " + array[i]; // go ahead and make a string representation of the new list
            insertItem(new ItemType(array[i])); // insert the new list items
        } // for

        System.out.println("The list 1: " + original);
        System.out.println("The list 2: " + str.trim());
        System.out.println("Merged list: " + toString());
    } // mergeList

    /**
     * Deletes every other node from the list starting with the second one.
     */
    public void deleteAlternateNodes() {
        String original = toString();
        currentPos = head;
        while (currentPos != null && currentPos.next != null) {
            currentPos.next = currentPos.next.next;
            currentPos = currentPos.next;
        } // while

        System.out.println("Original list: " + original);
        if (head == null) {
            System.out.println("The list is empty");
        } // if
        System.out.println("Modified list: " + toString());
    } // deleteAlternateNodes

    /**
     * Returns the commmon elements of two input lists.
     */
    public void intersection() {
        Scanner scanner = new Scanner(System.in);
        String original = toString();
        System.out.print("Enter the length of the new list: ");
        int newLength = scanner.nextInt();
        System.out.print("Enter the numbers: ");
        int[] array = new int[newLength];

        // create an array out of the new elements
        for (int i = 0; i < newLength; i++) {
            int newNumber = scanner.nextInt();
            array[i] = newNumber;
        } // for
        Arrays.sort(array);

        // save the new list as a string
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = str + " " + array[i];
        } // for

        //get a String representation of this list to print later
        SortedLinkedList strAtLinkedList = new SortedLinkedList(str);
        String list2 = strAtLinkedList.toString();

        // search for same elements
        currentPos = head;
        while ( currentPos != null) {
            for (int i = 0; i < array.length; i++) {

            } // for
        } // while

        String intersectionAsString = "";

        for (int i = 0; i < array.length; i++) {
            if (searchItem(new ItemType(array[i])) != -1) {
                intersectionAsString = intersectionAsString + " " + array[i];
            } // if
        } // for
        intersectionAsString.trim();

        SortedLinkedList newLinkedList = new SortedLinkedList(intersectionAsString);
        head = newLinkedList.head;

        System.out.println("The list 1:" + original);
        System.out.println("The list 2:" + list2);
        System.out.println("Intersection of lists: " + toString());



    } // intersection

    public String toString() {
        String str = "";
        int nextValue;
        currentPos = head;
        while (currentPos != null) {
            nextValue = currentPos.getValue();
            str = str + " " + nextValue;
            currentPos = currentPos.next;
        } // while
        return str.trim();
    } // toString

    //Extra Methods I might need

    /**
     * This method returns the number of spaces in a string + 1.
     *
     * @param fileContents is the string to parse
     * @return the number of spaces + 1
     */
    private int getArraySize(String fileContents) {
        int spaceCount = 0;
        for (char c : fileContents.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        if (fileContents.equals("")) {
            return spaceCount;
        } else {
            return spaceCount +1;
        } // if
    } // getArraySize

    private int[] stringToArray(String fileContents) {

        // prepare the String to be parsed
        fileContents.trim();
        int arraySize = getArraySize(fileContents);
        //System.out.println("Array Size = " + arraySize); // for debugging
        int[] array = new int[arraySize]; // initialize array

        // parse the string and turn it into an array
        for (int i = 0; i < arraySize - 1; i++) {
            if (fileContents.indexOf(' ') != -1) {
                String arrayElementAsString = fileContents.substring(0, fileContents.indexOf(' '));
                fileContents = fileContents.substring(fileContents.indexOf(' ') + 1);
                int arrayElement = Integer.parseInt(arrayElementAsString);
                array[i] = arrayElement;
                fileContents.trim();
            } // if
        } // for
        array[arraySize - 1] = Integer.parseInt(fileContents);

        return array;

    } // stringToArray

    // Used for debugging
    private void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("" + array[i]);
        } // for
    } // printArray

    public NodeType getCurrentPos() {
        return currentPos;
    } // getCurrentPosition

    public void setCurrentPos(NodeType currentPos) {
        this.currentPos = currentPos;
    } // setCurrentPos

    public NodeType getHead() {
        return head;
    } // getHead

} // SortedLinkedList
