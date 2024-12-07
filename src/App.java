import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static final String[][] CORRECT_CREDENTIALS = {
        { "admin", "admin" }
    };

    // Arrays to store project, supplier, purchase order, and employee data
    private static final String[][] projects = new String[100][7];
    private static final String[][] budgetData = new String[100][2];
    private static final String[][] employees = new String[100][3];
    private static int projectCount = 0, employeeCount = 0;

    /**
     * Authenticates the user with 3 attempts.
     *
     * @param scanner The scanner object to read user input.
     * @return true if login is successful, false if login fails.
     */
    private static boolean authenticate(Scanner scanner) {
        // Attempt to authenticate user with 3 attempts
        int attempts = 3;
        while (attempts > 0) {
            System.out.println("\nLogin Authentication");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            // Check if the entered credentials match any in CORRECT_CREDENTIALS
            if (
                Arrays.stream(CORRECT_CREDENTIALS).anyMatch(
                    credentials ->
                        username.equals(credentials[0]) &&
                        password.equals(credentials[1])
                )
            ) {
                System.out.println("Login successful!\n");
                return true;
            }
            // Decrement attempts and display the remaining attempts if credentials are invalid
            attempts--;
            System.out.println(
                "Invalid credentials. You have " +
                attempts +
                " attempts remaining."
            );
        }
        System.out.println("Login failed. No more attempts remaining.");
        return false;
    }

    /**
     * Displays the main menu of the ERP software.
     */
    private static void displayMenu() {
        System.out.print("");
        System.out.print(
            "-------------------------------------------------------------"
        );
        System.out.println(
            "\n【 Welcome to the Construction Engineering ERP Software! 】\n"
        );
        System.out.println("⌘ Please enter your choice. ⌘");
        System.out.println("1. Project Management");
        System.out.println("2. Supply Chain & Inventory Management");
        System.out.println("3. Financial Management");
        System.out.println("4. Human Resource Management");
        System.out.println("5. Generate Report");
        System.out.println("6. Exit");
        System.out.println(
            "-------------------------------------------------------------"
        );
        System.out.print("Enter your choice (1-6) or press Enter to return: ");
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            if (!authenticate(scanner)) {
                System.out.println(
                    "Authentication failed. Program terminating."
                );
                return;
            }
            int choice = 0;
            do {
                displayMenu();
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    continue;
                }
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        manageProject(scanner);
                        break;
                    case 2:
                        manageSupplyChainInventory(scanner);
                        break;
                    case 3:
                        manageFinancialManagement(scanner);
                        break;
                    case 4:
                        manageHumanResources(scanner);
                        break;
                    case 5:
                        generateReport();
                        break;
                    case 6:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            } while (choice != 6);
        }
        // Display the menu one more time after exiting the loop
        displayMenu();
    }

    /**
     * Manages projects.
     *
     * @param scanner The scanner object to read user input.
     */
    private static void manageProject(Scanner scanner) {
        System.out.println("\n=== 1. Project Management ===");
        System.out.println("(1) Add Project");
        System.out.println("(2) List Projects");
        System.out.println("(3) Update Project Status");
        System.out.println("(0) Return to Main Menu");
        System.out.print("Enter your choice (0-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                addProject(scanner);
                manageProject(scanner); // Return to this menu after successful operation
                break;
            case 2:
                listProjects(scanner);
                manageProject(scanner); // Return to this menu after successful operation
                break;
            case 3:
                updateProjectStatus(scanner);
                manageProject(scanner); // Return to this menu after successful operation
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
                manageProject(scanner); // Return to this menu after invalid operation
                break;
        }
    }

    /**
     * Adds a new project.
     * <p>
     * Prompts the user to input the project name, start date, end date, project ID, and project size in kWp.
     * Calculates the project cost by multiplying the project size by the cost per watt of 5 Thai Baht.
     * Increases the project cost by 20% and adds 7% VAT.
     * Stores the project information in the projects array and increments the project count.
     * Prints a success message after adding the project successfully.
     *
     * @param scanner The scanner object to read user input.
     */
    private static void addProject(Scanner scanner) {
        System.out.print("Enter project name (OWNER_(PPA,EPC)_kWp): ");
        String projectName = scanner.nextLine();
        System.out.print("Enter project start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter project end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.print("Enter project ID (0x): ");
        String projectId = scanner.nextLine();
        System.out.print("Enter project size in kWp: ");
        double projectSize = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        int costPerWatt = 5; // Assuming cost per watt is 5 Thai baht
        int projectCost = (int) (projectSize * costPerWatt * 1000);
        projectCost = (int) (projectCost * 1.2); // Increased by 20%
        int vat = (int) (projectCost * 0.07); // 7% VAT
        projectCost += vat; // Include VAT in project cost

        projects[projectCount][0] = projectName;
        projects[projectCount][1] = startDate;
        projects[projectCount][2] = endDate;
        projects[projectCount][3] = projectId;
        projects[projectCount][4] = String.valueOf(projectCost);
        projectCount++;
        System.out.println(
            "\n** Project : " + projectName + " | Project ID: " + projectId + " | , added successfully!"
        );
    }

    private static void listProjects(Scanner scanner) {
        if (projectCount == 0) {
            System.out.println("\n➜ No projects found.");
            manageProject(scanner);
        }
        System.out.println("\n++ Projects Record : ++");
        for (int i = 0; i < projectCount; i++) {
            System.out.println(
                "No." +
                (i + 1) +
                " Project Name: " +
                projects[i][0] +
                " | Project ID: " +
                projects[i][3] +
                ", Start Date: " +
                projects[i][1] +
                ", End Date: " +
                projects[i][2] +
                ", Project Cost: " +
                String.format("%,d", Integer.parseInt(projects[i][4])) +
                " THB, Status: " +
                projects[i][6]
            );
        }
    }

    private static void updateProjectStatus(Scanner scanner) {
        if (projectCount == 0) {
            System.out.println("\n➜ No projects available for status update.");
            manageProject(scanner);
        } else {
            System.out.println("\nUpdate Project Status :");
            System.out.println("Select Project :");
            for (int i = 0; i < projectCount; i++) {
                System.out.println(
                    (i + 1) +
                    ") Project ID: " +
                    projects[i][3] +
                    ", Project Name: " +
                    projects[i][0]
                );
            }
            System.out.print("Enter project ID: ");
            int projectId = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            System.out.print(
                "Enter new project status (e.g., 'Bidding', 'In Progress', 'Completed'): "
            );
            String updateStatus = scanner.nextLine();
            boolean projectStatusUpdated = false;
            for (int i = 0; i < projectCount; i++) {
                if (Integer.parseInt(projects[i][3]) == projectId) {
                    projects[i][6] = updateStatus; // Add projectStatus to project data array
                    projectStatusUpdated = true;
                    break;
                }
            }
            if (projectStatusUpdated) {
                System.out.println("\nProject status updated successfully!");
            } else {
                System.out.println(
                    "\nProject not found or ID mismatch. Status update failed."
                );
            }
        }
    }

    /**
     * Manages financial operations such as budgeting and cost control.
     *
     * @param scanner The scanner object to read user input.
     */
    private static void manageFinancialManagement(Scanner scanner) {
        System.out.println("\n=== 3. Financial Management ===");
        System.out.println("1. Budgeting");
        System.out.println("2. Cost Control");
        System.out.println("3. List Projects with Budget");
        System.out.println("0. Return to Main Menu");
        System.out.print("Enter your choice (1-3): ");
        int choice;
        // Attempt to read an integer from the user input
        try {
            choice = scanner.nextInt();
            // Consume the newline character left in the buffer after reading an integer
            scanner.nextLine();
        } catch (InputMismatchException e) {
            // If the input is not an integer, print an error message and exit the method
            System.out.println("Invalid input! Please try again.");
            return;
        }
        switch (choice) {
            case 1:
                setBudget(scanner);
                break;
            case 2:
                controlCosts(scanner);
                break;
            case 3:
                listProjectsWithBudget();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice! Please try again.");
                break;
        }
    }

    private static void setBudget(Scanner scanner) {
        System.out.println("\nBudgeting:");
        System.out.println("List of Projects:");
        for (int i = 0; i < projectCount; i++) {
            double projectCost = Double.parseDouble(projects[i][4]);
            int newBudget = (int) (projectCost * 0.6);
            budgetData[i][0] = String.valueOf(newBudget); // Auto-add budget with 60% of project cost
            System.out.println(
                (i + 1) +
                ". Project ID: " +
                projects[i][3] +
                ", Project Name: " +
                projects[i][0] +
                ", Budget: " +
                String.format("%,d", Integer.parseInt(budgetData[i][0])) +
                " THB"
            );
        }
        System.out.print("Enter project ID to update budget: ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        boolean projectFound = false;
        for (int i = 0; i < projectCount; i++) {
            if (Integer.parseInt(projects[i][3]) == projectId) {
                // Calculate the budget at 60% of the project cost
                double projectCost = Double.parseDouble(projects[i][4]);
                double newBudget = projectCost * 0.6;
                budgetData[i][0] = String.valueOf(newBudget); // Update budget in budget data array
                projectFound = true;
                System.out.println(
                    "\nBudget updated successfully for project ID " +
                    projectId +
                    "!"
                );
                break;
            }
        }
        if (!projectFound) {
            System.out.println("\nProject not found. Please try again.");
        }
    }

    // Calculate cost control at 80% of project cost
    private static void controlCosts(Scanner scanner) {
        System.out.println("\nCost Control:");
        System.out.print("Enter project ID: ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        boolean projectFound = false;
        for (int i = 0; i < projectCount; i++) {
            if (Integer.parseInt(projects[i][3]) == projectId) {
                double projectCost = Double.parseDouble(projects[i][4]);
                double costControl = projectCost * 0.8;
                budgetData[i][1] = String.valueOf(costControl); // Update cost control in budget data array
                projectFound = true;
                System.out.println(
                    "Cost control implemented successfully for project ID " +
                    projectId +
                    " (" +
                    projects[i][0] +
                    ") at 80% of project cost: " +
                    String.format("%,.2f", costControl) +
                    " THB"
                );
                break;
            }
        }
        if (!projectFound) {
            System.out.println("➜ Project not found. Please try again.");
        }
    }

    private static void listProjectsWithBudget() {
        boolean projectFound = false;
        System.out.println("\nList of Projects with Budget: ");
        if (!projectFound) {
            System.out.println("➜ Project not found. Please try again.");
        }
        for (int i = 0; i < projectCount; i++) {
            if (budgetData[i][0] != null) {
                System.out.println(
                    "Project ID: " +
                    projects[i][3] +
                    ", Project Name: " +
                    projects[i][0] +
                    ", Budget: " +
                    String.format(
                        "%,.2f",
                        Double.parseDouble(budgetData[i][0])
                    ) +
                    " THB"
                );
            } else {
                System.out.println(
                    "Project ID: " +
                    projects[i][3] +
                    ", Project Name: " +
                    projects[i][0] +
                    ", Budget: N/A"
                );
            }
        }
    }

    /**
     * @param scanner
     */
    private static void manageSupplyChainInventory(Scanner scanner) {
        System.out.println("\n=== 2. Supply Chain, Inventory Management ===");
        System.out.println("1. Source Solar Panels");
        System.out.println("2. Source Inverters");
        System.out.println("3. Source Other Components");
        System.out.println("4. Manage Inventory");
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                sourceSolarPanels(scanner);
                break;
            case 2:
                sourceInverters();
                break;
            case 3:
                sourceOtherComponents(scanner);
                break;
            case 4:
                manageInventory();
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
                break;
        }
    }

    private static void sourceSolarPanels(Scanner scanner) {
        System.out.println("\nSource Solar Panels:");
        System.out.println("Select supplier name for solar panels: ");

        String[] manufatureSolarPanel = { "LONGi", "ZNSHINE" };

        for (int i = 0; i < manufatureSolarPanel.length; i++) {
            System.out.println((i + 1) + ". " + manufatureSolarPanel[i]);
        }
        System.out.print(
            "Enter your choice (1-" + manufatureSolarPanel.length + "): "
        );
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice > 0 && choice <= manufatureSolarPanel.length) {
            System.out.println(
                "Solar panels sourced successfully from " +
                manufatureSolarPanel[choice - 1] +
                "!"
            );
        } else {
            System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void sourceInverters() {
        System.out.println("\nSource Inverters:");

        String[] inverterBrand = { "SMA", "Huawei", "Growatt" };
        System.out.println("Available inverter brands:");
        for (int i = 0; i < inverterBrand.length; i++) {
            System.out.println((i + 1) + "." + inverterBrand[i]);
        }
    }

    private static void sourceOtherComponents(Scanner scanner) {
        System.out.println("\nSource Other Components:");
        System.out.print("Enter supplier name for other components: ");
        String otherComponentsSupplierName = scanner.nextLine();
        System.out.println(
            "Other components sourced successfully from " +
            otherComponentsSupplierName +
            "!"
        );
    }

    private static void manageInventory() {
        System.out.println("\nManage Inventory:");

        System.out.println("\nAvailable Inventory:");
        System.out.println("Inverter Brands:");
        String[] inverterBrands = { "SMA", "Huawei", "Growatt" };
        for (String brand : inverterBrands) {
            System.out.println("- " + brand);
        }

        System.out.println("\nSolar Panel Manufacturers:");
        String[] solarPanelManufacturers = { "LONGi", "ZNSHINE" };
        for (String manufacturer : solarPanelManufacturers) {
            System.out.println("- " + manufacturer);
        }

        System.out.println("\nOther Components:");
        String[] otherComponents = {
            "Mounting Structures",
"Cables","Connectors",        };
        for (String component : otherComponents) {
            System.out.println("- " + component);
        }
        // System.out.println("Inventory management functionality is under development."); sehfy4-nopSor-zujdyc
    }

    private static void manageHumanResources(Scanner scanner) {
        String[] options = {
            "Record Employee Information",
            "Show Employees Information",
        };
        String[] employeeInfo = new String[3];
        System.out.println("\n=== 5. Human Resource Management ===");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Enter your choice (1-" + options.length + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                recordEmployeeInformation(scanner, employeeInfo);
                break;
            case 2:
                showEmployeesInformation();
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
                break;
        }
    }

    private static void recordEmployeeInformation(
        Scanner scanner,
        String[] employeeInfo
    ) {
        System.out.println("\nRecord Employee Information:");
        System.out.print("Enter employee ID: ");
        employeeInfo[0] = scanner.nextLine();
        System.out.print("Enter employee name: ");
        employeeInfo[1] = scanner.nextLine();
        System.out.print("Enter employee role: ");
        employeeInfo[2] = scanner.nextLine();
        employees[employeeCount] = employeeInfo;
        employeeCount++;
        System.out.println(
            "Employee information saved successfully for " +
            employeeInfo[1] +
            "!"
        );
    }

    private static void showEmployeesInformation() {
        System.out.println("\nShow Employees Information:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(
                "Employee ID: " +
                employees[i][0] +
                ", Name: " +
                employees[i][1] +
                ", Role: " +
                employees[i][2]
            );
        }
    }

    private static void generateReport() {
        System.out.println("*** ---------------------- ***\n");

        System.out.println("=== Project Report ===");
        for (int i = 0; i < projectCount; i++) {
            System.out.println(
                "Project Name: " +
                projects[i][0] +
                ", Start Date: " +
                projects[i][1] +
                ", End Date: " +
                projects[i][2] +
                ", Status: " +
                projects[i][6]
            );
        }

        System.out.println("");
        System.out.println("=== Budget Report ===");
        for (int i = 0; i < projectCount; i++) {
            double projectCost = Double.parseDouble(projects[i][4]);
            int newBudget = (int) (projectCost * 0.6);
            budgetData[i][0] = String.valueOf(newBudget); // Auto-add budget with 60% of project cost
            System.out.println(
                "Project ID: " +
                projects[i][3] +
                ", Project Name: " +
                projects[i][0] +
                ", Budget: " +
                String.format("%,d", Integer.parseInt(budgetData[i][0])) +
                " THB"
            );
        }

        System.out.println("");
        System.out.println("=== Inventory Report ===");
        System.out.println(
            "Inverter Brands: " + String.join(", ", "SMA", "Huawei", "Growatt")
        );
        System.out.println(
            "Solar Panel Manufacturers: " +
            String.join(", ", "LONGi", "ZNSHINE")
        );
        System.out.println(
            "Other Components: " +
            String.join(", ", "Mounting Structures", "Cables", "Connectors")
        );

        System.out.println("");
        System.out.println("=== Employee Report ===");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(
                "Employee ID: " +
                employees[i][0] +
                ", Name: " +
                employees[i][1] +
                ", Role: " +
                employees[i][2]
            );
        }
        System.out.println("*** ---------------------- ***\n");
    }
}
