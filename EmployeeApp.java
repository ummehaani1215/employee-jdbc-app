import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "your_password_here";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private static void addEmployee(String name, String role, double salary) {
        String query = "INSERT INTO employee(name, role, salary) VALUES (?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println(" New employee added!");
        } catch (SQLException e) {
            System.err.println(" Error: " + e.getMessage());
        }
    }

    private static void viewEmployees() {
        String query = "SELECT * FROM employee";
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Role: %s | Salary: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void updateSalary(int id, double newSalary) {
        String query = "UPDATE employee SET salary = ? WHERE id = ?";
        try (Connection con = connect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, newSalary);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Salary updated.");
            else
                System.out.println("Employee not found.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void deleteEmployee(int id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (Connection con = connect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0)
                System.out.println("Employee removed.");
            else
                System.out.println(" No such employee.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    scanner.nextLine(); 
                    String name = scanner.nextLine();
                    System.out.print("Role: ");
                    String role = scanner.nextLine();
                    System.out.print("Salary: ");
                    double salary = scanner.nextDouble();
                    addEmployee(name, role, salary);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.print("Employee ID to update: ");
                    int uid = scanner.nextInt();
                    System.out.print("New Salary: ");
                    double newSalary = scanner.nextDouble();
                    updateSalary(uid, newSalary);
                    break;
                case 4:
                    System.out.print("Employee ID to delete: ");
                    int did = scanner.nextInt();
                    deleteEmployee(did);
                    break;
                case 5:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (choice != 5);
        scanner.close();
    }
}
