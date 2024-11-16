
import java.util.Arrays;
import java.util.Scanner;

public class App {

    private static final String[][] CORRECT_CREDENTIALS = {{"admin", "admin123"}};
    private static String[][] employees = new String[100][3]; // Array to store employee information
    private static int employeeCount = 0; // Counter for the number of employees

    /**
     * Authenticates user login with 3 attempts.
     *
     * @param scanner Scanner for user input
     * @return true if login is successful, false otherwise
     */
    private static boolean authenticate(Scanner scanner) {
        int attempts = 3;  // 3 login attempts

        while (attempts > 0) {
            System.out.println("\nLogin Authentication");
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (Arrays.stream(CORRECT_CREDENTIALS)
                    .anyMatch(credentials -> username.equals(credentials[0]) && password.equals(credentials[1]))) {
                System.out.println("Login successful!");
                return true;
            }

            attempts--;
            System.out.println("Invalid credentials. You have " + attempts + " attempts remaining.");
        }
        System.out.println("Login failed. No more attempts remaining.");
        return false;
    }

    private static void displayMenu() {
        // Displaying the main menu options
        System.out.println("\nWelcome to the Construction Engineering ERP Software!");
        System.out.println("1. Project Management");
        System.out.println("2. Resource Management");
        System.out.println("3. Procurement Management");
        System.out.println("4. Financial Management");
        System.out.println("5. Human Resource Management");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6) or press Enter to return: ");
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            // Authenticate user first
            if (!authenticate(scanner)) {
                System.out.println("Authentication failed. Program terminating.");
                return;
            }

            int choice = 0;
            do {
                displayMenu();
                String input = scanner.nextLine(); // Changed to directly read a line to handle Enter key press
                if (input.isEmpty()) { // Check if Enter key was pressed
                    continue; // Return to the current function without processing further
                }
                choice = Integer.parseInt(input); // Parse the input to an integer

                switch (choice) {
                    case 1 ->
                        manageProject(scanner);
                    case 2 ->
                        System.out.println("Resource management functionality is under development.");
                    case 3 ->
                        manageProcurement(scanner);
                    case 4 ->
                        System.out.println("Financial management functionality is under development.");
                    case 5 ->
                        manageHumanResources(scanner);
                    case 6 ->
                        System.out.println("Exiting program...");
                    default ->
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 6);
        }
        displayMenu();
    }

    /**
     * Manages project operations such as comprehensive scheduling and task
     * assignments.
     *
     * @param scanner Scanner object for user input
     */
    private static void manageProject(Scanner scanner) {
        // Displaying project management options
        System.out.println("\n=== 1. Project Management ===");
        System.out.println("1. Comprehensive Scheduling");
        System.out.println("2. Task Assignments");
        System.out.print("Enter your choice (1-2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                // Handling comprehensive scheduling
                System.out.println("\nComprehensive Scheduling:");
                System.out.print("Enter project id: ");
                String projectId = scanner.nextLine();
                System.out.print("Enter project start date (YYYY-MM-DD): ");
                String startDate = scanner.nextLine();
                System.out.print("Enter project end date (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();

                // Add code to save project schedule
                System.out.println("Project schedule created successfully for project id " + projectId + "!");
            }
            case 2 -> {
                // Handling task assignments
                System.out.println("\nTask Assignments:");
                System.out.print("Enter task name: ");
                String taskName = scanner.nextLine();
                System.out.print("Enter task start date (YYYY-MM-DD): ");
                String taskStartDate = scanner.nextLine();
                System.out.print("Enter task end date (YYYY-MM-DD): ");
                String taskEndDate = scanner.nextLine();
                System.out.print("Enter task priority (High/Medium/Low): ");
                String taskPriority = scanner.nextLine();
                System.out.print("Enter team/individual name: ");
                String teamName = scanner.nextLine();

                // Add code to assign task
                System.out.println("Task " + taskName + " assigned to " + teamName + " successfully!");
            }
            default ->
                System.out.println("Invalid choice! Please try again.");
        }
    }

    @SuppressWarnings("unused")
    /**
     * Manages procurement processes such as supplier and vendor database
     * management, purchase order creation, and supply chain integration.
     *
     * @param scanner Scanner object for user input
     */
    private static void manageProcurement(Scanner scanner) {
        System.out.println("\n=== 3. Procurement Management ===");
        System.out.println("1. Supplier and Vendor Database");
        System.out.println("2. Purchase Order Creation");
        System.out.println("3. Supply Chain Integration");
        System.out.print("Enter your choice (1-3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("\nSupplier and Vendor Database:");
                // Add code to display supplier and vendor database
                System.out.println("Supplier 1: XYZ Corporation");
                System.out.println("Supplier 2: ABC Inc.");
                System.out.println("Vendor 1: DEF Services");
            }
            case 2 -> {
                System.out.println("\nPurchase Order Creation");
                System.out.print("Enter purchase order details: ");
                String details = scanner.nextLine();

                // Add code to save purchase order
                System.out.println("Purchase order created successfully!");
            }
            case 3 -> {
                System.out.println("\nSupply Chain Integration");
                System.out.print("Enter supplier name for integration: ");
                String supplierName = scanner.nextLine();

                // Add code to integrate with supplier
                System.out.println("Supply chain integration successful for " + supplierName + "!");
            }
            default ->
                System.out.println("Invalid choice! Please try again.");
        }
    }

    /**
     * Manages employee data, including recording and displaying employee
     * information.
     *
     * @param scanner Scanner object for user input
     */
    private static void manageHumanResources(Scanner scanner) {
        String[] options = {"Record Employee Information", "Show Employees Information"};
        String[] employeeInfo = new String[3];

        System.out.println("\n=== 5. Human Resource Management ===");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Enter your choice (1-" + options.length + "): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("\nRecord Employee Information:");
                System.out.print("Enter employee ID: ");
                employeeInfo[0] = scanner.nextLine();
                System.out.print("Enter employee name: ");
                employeeInfo[1] = scanner.nextLine();
                System.out.print("Enter employee role: ");
                employeeInfo[2] = scanner.nextLine();

                // Save employee information to the array
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i][0] == null) {
                        employees[i] = employeeInfo;
                        employeeCount++; // Increment the employee count
                        break;
                    }
                }

                System.out.println("Employee information saved successfully for " + employeeInfo[1] + "!");
            }
            case 2 -> {
                System.out.println("\nShow Employees Information:");
                for (int i = 0; i < employeeCount; i++) {
                    System.out.println("Employee ID: " + employees[i][0] + ", Name: " + employees[i][1] + ", Role: " + employees[i][2]);
                }
            }
            default -> {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
