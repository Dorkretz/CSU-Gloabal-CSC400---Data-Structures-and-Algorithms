import java.util.HashMap;

public class Bag<T> {
    // Internal map to store elements
    private HashMap<T, Integer> bag;

    // Constructor to initialize the bag.
    public Bag() {
        bag = new HashMap<>();
    }

    public void add(T item) {
        bag.put(item, bag.getOrDefault(item, 0) + 1);
    }

    public void remove(T item) {
        if (bag.containsKey(item)) {
            int count = bag.get(item);
            if (count > 1) {
                bag.put(item, count - 1);
            } else {
                bag.remove(item);
            }
        }
    }

    public boolean contains(T item) {
        return bag.containsKey(item);
    }

    public int count(T item) {
        return bag.getOrDefault(item, 0);
    }

    @Override
    public String toString() {
        return bag.toString();
    }
}