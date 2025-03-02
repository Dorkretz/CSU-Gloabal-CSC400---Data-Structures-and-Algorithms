import java.util.ArrayList;
import java.util.List;

/**
 * A queue that stores Person objects and can sort them.
 */
public class PersonQueue {
    private List<Person> queue;

    /**
     * Constructor initializes an empty queue.
     */
    public PersonQueue() {
        queue = new ArrayList<>();
    }

    /**
     * Adds a person to the queue.
     *
     * @param person The person to add.
     */
    public void enqueue(Person person) {
        queue.add(person);
    }

    /**
     * Displays all people in the queue.
     */
    public void displayQueue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        for (Person person : queue) {
            System.out.println(person);
        }
    }

    /**
     * Quick Sort helper method.
     *
     * @param low  Starting index.
     * @param high Ending index.
     * @param sortBy Determines whether to sort by last name or age.
     */
    private void quickSort(int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(low, high, sortBy);
            quickSort(low, pi - 1, sortBy);
            quickSort(pi + 1, high, sortBy);
        }
    }

    /**
     * Partition method for Quick Sort.
     *
     * @param low  Starting index.
     * @param high Ending index.
     * @param sortBy Determines sorting key.
     * @return Partition index.
     */
    private int partition(int low, int high, String sortBy) {
        Person pivot = queue.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition;
            if (sortBy.equals("lastName")) {
                condition = queue.get(j).getLastName().compareToIgnoreCase(pivot.getLastName()) > 0;
            } else {
                condition = queue.get(j).getAge() > pivot.getAge();
            }

            if (condition) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the queue.
     *
     * @param i First index.
     * @param j Second index.
     */
    private void swap(int i, int j) {
        Person temp = queue.get(i);
        queue.set(i, queue.get(j));
        queue.set(j, temp);
    }

    /**
     * Sorts the queue in descending order based on last name.
     */
    public void sortByLastName() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot sort.");
        }
        quickSort(0, queue.size() - 1, "lastName");
    }

    /**
     * Sorts the queue in descending order based on age.
     */
    public void sortByAge() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot sort.");
        }
        quickSort(0, queue.size() - 1, "age");
    }
}
