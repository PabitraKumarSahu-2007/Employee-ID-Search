import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Employee> loadEmployees(String fileName) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine && line.toLowerCase().startsWith("id,")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;
                String[] values = line.split(",", -1);
                if (values.length >= 3) {
                    employees.add(new Employee(
                        Integer.parseInt(values[0].trim()),
                        values[1].trim(),
                        values[2].trim()
                    ));
                }
            }
        }
        return employees;
    }

    private static void display(String label, SearchResult result) {
        System.out.println(label + " -> "
            + (result.getEmployee() == null ? "Not found" : result.getEmployee())
            + " | Comparisons: " + result.getComparisons());
    }

    public static void main(String[] args) {
        String dataFile = args.length > 0 ? args[0] : "data/employees.csv";

        try {
            List<Employee> employees = loadEmployees(dataFile);
            if (employees.size() < 30 || employees.size() > 50) {
                System.out.println("Warning: assignment dataset should contain 30–50 records.");
            }
            if (employees.isEmpty()) {
                System.out.println("No employee records found.");
                return;
            }

            BST bst = new BST();
            AVL avl = new AVL();
            // Capacity chosen above the number of records; linear probing handles collisions.
            LinearProbingHash hash = new LinearProbingHash(53);

            for (Employee employee : employees) {
                bst.insert(employee);
                avl.insert(employee);
                hash.insert(employee);
            }

            Scanner input = new Scanner(System.in);
            System.out.println("\\nEMPLOYEE ID SEARCH — ALGORITHM BATTLE");
            System.out.println("Dataset records loaded: " + employees.size());

            System.out.print("Enter an existing Employee ID (successful search): ");
            int existingId = input.nextInt();
            System.out.print("Enter a non-existing Employee ID (unsuccessful search): ");
            int missingId = input.nextInt();

            System.out.println("\\n--- SUCCESSFUL SEARCH ---");
            display("BST", bst.search(existingId));
            display("AVL", avl.search(existingId));
            display("Hashing", hash.search(existingId));

            System.out.println("\\n--- UNSUCCESSFUL SEARCH ---");
            display("BST", bst.search(missingId));
            display("AVL", avl.search(missingId));
            display("Hashing", hash.search(missingId));

            System.out.println("\\n--- HEIGHT AND COLLISIONS ---");
            System.out.println("BST height: " + bst.height());
            System.out.println("AVL height: " + avl.height());
            System.out.println("Hashing insertion collisions: " + hash.getCollisions());

            System.out.print("\\nEnter a new unique Employee ID for insertion: ");
            int newId = input.nextInt();
            if (bst.search(newId).getEmployee() != null) {
                System.out.println("That ID already exists. Run again with a new ID.");
            } else {
                Employee newEmployee = new Employee(newId, "New Employee", "New Department");
                bst.insert(newEmployee);
                avl.insert(newEmployee);
                hash.insert(newEmployee);
                System.out.println("New employee inserted into BST, AVL and Hashing.");
                System.out.println("Updated BST height: " + bst.height());
                System.out.println("Updated AVL height: " + avl.height());
                System.out.println("Updated hash collisions: " + hash.getCollisions());
            }

            System.out.println("\\n--- BST INORDER TRAVERSAL ---");
            bst.inorderTraversal();
            System.out.println("\\n--- AVL INORDER TRAVERSAL ---");
            avl.inorderTraversal();

        } catch (IOException e) {
            System.out.println("Unable to read " + dataFile
                + ". Run the program from the repository root folder.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Employee ID in CSV. IDs must be integers.");
        }
    }
}
