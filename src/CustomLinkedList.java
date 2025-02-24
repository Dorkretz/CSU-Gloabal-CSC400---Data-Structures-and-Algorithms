import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A custom implementation of a singly linked list that supports basic operations
 * and iteration. This implementation stores integer values and provides methods
 * for insertion, deletion, and traversal.
 */
public class CustomLinkedList implements Iterable<Integer> {
    private Node head;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private class LinkedListIterator implements Iterator<Integer> {
        private Node current;

        LinkedListIterator() {
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

    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public boolean delete(int data) {
        if (head == null) {
            return false;
        }

        if (head.data == data) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }

        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Loads integer values from a text file into the linked list.
     *
     * @param filename the name of the file to read from
     * @throws IOException if there is an error reading the file
     */
    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    insert(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number: " + line);
                }
            }
        }
    }

    /**
     * Loads integer values from user input into the linked list.
     *
     * @param scanner Scanner object for reading user input
     */
    public void loadFromUserInput(Scanner scanner) {
        System.out.println("Enter integers (enter a non-integer to finish):");

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                insert(scanner.nextInt());
            } else {
                scanner.next(); // consume the non-integer input
                break;
            }
        }
    }
}