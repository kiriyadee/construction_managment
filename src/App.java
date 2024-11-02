
import java.util.Scanner;

public class App {

    private static void displayMenu() {
        System.out.println("\nConstruction Project Management System");
        System.out.println("1. Create New Project");
        System.out.println("2. Check Project Status");
        System.out.println("3. Mark Project as Finished");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                displayMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> {
                    }
                    case 2 -> {
                        checkProjectStatus(scanner);
                    }
                    case 3 -> {
                    }
                    case 4 ->
                        System.out.println("Exiting program...");
                    default ->
                        System.out.println("Invalid choice! Please try again.");
                }
                // Create new project
                // Check project status
                // Mark project as finished
            } while (choice != 4);
        }
        displayMenu();
    }

    @SuppressWarnings("unused")
    private static void checkProjectStatus(Scanner scanner) {
        System.out.println("\n=== 2. Check Project Status ===");
        System.out.println("1. View Current Status");
        System.out.println("2. Create Work Request");
        System.out.print("Enter your choice (1-2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("\nCurrent Project Status:");
                // Add code to display project status
                System.out.println("Status: In Progress");
                System.out.println("Completion: 65%");
                System.out.println("Days Remaining: 45");
            }
            case 2 -> {
                System.out.println("\nCreate Work Request");
                System.out.print("Enter work request description: ");
                String description = scanner.nextLine();
                System.out.print("Enter priority (High/Medium/Low): ");
                String priority = scanner.nextLine();

                // Add code to save work request
                System.out.println("Work request created successfully!");
            }
            default ->
                System.out.println("Invalid choice! Please try again.");
        }
    }
}
