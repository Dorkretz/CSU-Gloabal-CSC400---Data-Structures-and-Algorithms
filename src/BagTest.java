public class BagTest {
    public static void main(String[] args) {
        //Instance of the Bag class
        Bag<String> bag = new Bag<>();

        // Adding elements to the bag
        bag.add("apple");
        bag.add("banana");
        bag.add("apple");
        bag.add("orange");
        bag.add("banana");
        bag.add("apple");

        // Print bag contents
        System.out.println("Bag contents: " + bag);

        // Testing the method.
        System.out.println("Bag contains 'apple': " + bag.contains("apple"));
        System.out.println("Bag contains 'grape': " + bag.contains("grape"));

        // Testing the method again
        System.out.println("Count of 'apple': " + bag.count("apple"));
        System.out.println("Count of 'banana': " + bag.count("banana"));

        // Removing an element from the bag
        bag.remove("apple");
        System.out.println("Remove one 'apple'.");

        // Printing the bag contents again
        System.out.println("Bag contents after removal: " + bag);

        // Testing the method for the removed element
        System.out.println("Bag contains 'apple': " + bag.contains("apple"));

        // Testing the method for the removed element again
        System.out.println("Count of 'apple': " + bag.count("apple"));
    }
}
