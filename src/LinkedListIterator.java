import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator implementation for the CustomLinkedList.
 * Provides methods to traverse the list sequentially.
 */
class LinkedListIterator implements Iterator<Integer> {
    private Node current;

    LinkedListIterator(Node head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int data = current.data;
        current = current.next;
        return data;
    }
}
