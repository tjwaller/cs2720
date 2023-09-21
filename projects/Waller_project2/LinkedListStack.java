public class LinkedListStack<E> {
    Node<E> top;
    int size;

    public static class Node<E> {
        public E element;
        public Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    } // Node

    public LinkedListStack() {
        top = null;
        size = 0;
    } // LinkedListStack

    public int size() {
        return size;
    } // size

    public boolean isEmpty() {
        return size == 0;
    } // isEmpty

    public void push(E element) {
        top = new Node<E>(element, top);
        size++;
    } // push

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = top.element;
        top = top.next;
        size--;
        return element;
    } // pop

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return top.element;
    } // top
}
