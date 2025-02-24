import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLinked List Operations:");
            System.out.println("1. Load from file");
            System.out.println("2. Load from user input");
            System.out.println("3. Insert a number");
            System.out.println("4. Delete a number");
            System.out.println("5. Display list");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter filename: ");
                    String filename = scanner.nextLine();
                    try {
                        list.loadFromFile(filename);
                        System.out.println("Data loaded from file successfully.");
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                    break;

                case 2:
                    list.loadFromUserInput(scanner);
                    System.out.println("Data loaded from user input successfully.");
                    break;

                case 3:
                    System.out.print("Enter number to insert: ");
                    int insertNum = scanner.nextInt();
                    list.insert(insertNum);
                    System.out.println("Number inserted successfully.");
                    break;

                case 4:
                    System.out.print("Enter number to delete: ");
                    int deleteNum = scanner.nextInt();
                    if (list.delete(deleteNum)) {
                        System.out.println("Number deleted successfully.");
                    } else {
                        System.out.println("Number not found in the list.");
                    }
                    break;

                case 5:
                    System.out.println("List contents:");
                    for (Integer num : list) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}