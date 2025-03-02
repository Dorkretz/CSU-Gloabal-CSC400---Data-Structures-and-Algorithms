import java.util.Scanner;

/**
 * Class that runs the program.
 */
public class PersonOrganization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonQueue queue = new PersonQueue();

        System.out.println("Enter details of 5 people:");

        for (int i = 0; i < 5; i++) {
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            queue.enqueue(new Person(firstName, lastName, age));
        }

        System.out.println("\nOriginal Queue:");
        queue.displayQueue();

        System.out.println("\nSorted by Last Name (Descending):");
        queue.sortByLastName();
        queue.displayQueue();

        System.out.println("\nSorted by Age (Descending):");
        queue.sortByAge();
        queue.displayQueue();

        scanner.close();
    }
}
