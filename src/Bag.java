import java.util.HashMap;

public class Bag<T> {
    private HashMap<T, Integer> items = new HashMap<>();

    // Add an item to the bag
    public void add(T item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    // Remove an item from the bag
    public void remove(T item) {
        if (items.containsKey(item)) {
            int count = items.get(item);
            if (count > 1) {
                items.put(item, count - 1);
            } else {
                items.remove(item);
            }
        }
    }

    // Calculate the number of elements
    public int size() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Merge a different bag into this bag
    public void merge(Bag<T> otherBag) {
        otherBag.items.forEach((item, count) ->
                this.items.put(item, this.items.getOrDefault(item, 0) + count)
        );
    }

    // Create a bag with distinct elements
    public Bag<T> distinct() {
        Bag<T> distinctBag = new Bag<>();
        items.keySet().forEach(distinctBag::add);
        return distinctBag;
    }

    // Check if the bag contains an item
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    // Count the occurrences of an item
    public int count(T item) {
        return items.getOrDefault(item, 0);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}