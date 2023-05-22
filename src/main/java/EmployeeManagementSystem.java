import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    static final String JDBC_DRIVER = "org.postgresql.Driver";          //驱动信息
    static final String url = "jdbc:postgresql://116.205.157.173:8000/db_2020_01";      //数据库信息
    static final String user = "db_user2020_77";
    static final String password = "db_user@123";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            SalaryDAO salaryDAO = new SalaryDAO(connection);
            TimeOffRequestDAO timeOffRequestDAO = new TimeOffRequestDAO(connection);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Employee Management System");
                System.out.println("1. Add employee");
                System.out.println("2. Edit employee");
                System.out.println("3. Delete employee");
                System.out.println("4. View all employees");
                System.out.println("5. Add salary");
                System.out.println("6. Edit salary");
                System.out.println("7. Delete salary");
                System.out.println("8. View salary");
                System.out.println("9. Add time off request");
                System.out.println("10. Edit time off request");
                System.out.println("11. Delete time off request");
                System.out.println("12. View time off requests");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter hire date (yyyy-mm-dd): ");
                        LocalDate hireDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter job title: ");
                        String jobTitle = scanner.nextLine();
                        System.out.print("Enter department ID: ");
                        int departmentId = scanner.nextInt();
                        scanner.nextLine();
                        Employee employee = new Employee(0, firstName, lastName, email, phoneNumber, hireDate, jobTitle, departmentId);
                        employeeDAO.addEmployee(employee);
                        System.out.println("Employee added successfully");
                        break;
                    case 2:
                        System.out.print("Enter employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Employee employeeToUpdate = employeeDAO.getAllEmployees().stream().filter(e -> e.getId() == id).findFirst().orElse(null);
                        if (employeeToUpdate == null) {
                            System.out.println("Employee not found");
                        } else {
                            System.out.print("Enter first name (" + employeeToUpdate.getFirstName() + "): ");
                            String updatedFirstName = scanner.nextLine();
                            if (!updatedFirstName.isEmpty()) {
                                employeeToUpdate.setFirstName(updatedFirstName);
                            }
                            System.out.print("Enter last name (" + employeeToUpdate.getLastName() + "): ");
                            String updatedLastName = scanner.nextLine();
                            if (!updatedLastName.isEmpty()) {
                                employeeToUpdate.setLastName(updatedLastName);
                            }
                            System.out.print("Enter email (" + employeeToUpdate.getEmail() + "): ");
                            String updatedEmail = scanner.nextLine();
                            if (!updatedEmail.isEmpty()) {
                                employeeToUpdate.setEmail(updatedEmail);
                            }
                            System.out.print("Enter phone number (" + employeeToUpdate.getPhoneNumber() + "): ");
                            String updatedPhoneNumber = scanner.nextLine();
                            if (!updatedPhoneNumber.isEmpty()) {
                                employeeToUpdate.setPhoneNumber(updatedPhoneNumber);
                            }
                            System.out.print("Enter hire date (" + employeeToUpdate.getHireDate() + "): ");
                            String updatedHireDate = scanner.nextLine();
                            if (!updatedHireDate.isEmpty()) {
                                employeeToUpdate.setHireDate(LocalDate.parse(updatedHireDate));
                            }
                            System.out.print("Enter job title (" + employeeToUpdate.getJobTitle() + "): ");
                            String updatedJobTitle = scanner.nextLine();
                            if (!updatedJobTitle.isEmpty()) {
                                employeeToUpdate.setJobTitle(updatedJobTitle);
                            }
                            System.out.print("Enter department ID (" + employeeToUpdate.getDepartmentId() + "): ");
                            String updatedDepartmentId = scanner.nextLine();
                            if (!updatedDepartmentId.isEmpty()) {
                                employeeToUpdate.setDepartmentId(Integer.parseInt(updatedDepartmentId));
                            }
                            employeeDAO.updateEmployee(employeeToUpdate);
                            System.out.println("Employee updated successfully");
                        }
                        break;
                    case 3:
                        System.out.print("Enter employee ID: ");
                        int idToDelete = scanner.nextInt();
                        scanner.nextLine();
                        employeeDAO.deleteEmployee(idToDelete);
                        System.out.println("Employee deleted successfully");
                        break;
                    case 4:
                        List<Employee> employees = employeeDAO.getAllEmployees();
                        for (Employee e : employees) {
                            System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName() + " " + e.getEmail() + " " + e.getPhoneNumber() + " " + e.getHireDate() + " " + e.getJobTitle() + " " + e.getDepartmentId());
                        }
                        break;
                    case 5:
                        System.out.print("Enter employee ID: ");
                        int employeeId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter base salary: ");
                        int baseSalary = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter bonus: ");
                        int bonus = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter effective date (yyyy-mm-dd): ");
                        LocalDate effectiveDate = LocalDate.parse(scanner.nextLine());
                        Salary salary = new Salary(employeeId, baseSalary, bonus, effectiveDate);
                        salaryDAO.addSalary(salary);
                        System.out.println("Salary added successfully");
                        break;
                    case 6:
                        System.out.print("Enter employee ID: ");
                        int employeeIdToUpdate = scanner.nextInt();
                        scanner.nextLine();
                        Salary salaryToUpdate = salaryDAO.getSalaryByEmployeeId(employeeIdToUpdate);
                        if (salaryToUpdate == null) {
                            System.out.println("Salary not found");
                        } else {
                            System.out.print("Enter base salary (" + salaryToUpdate.getBaseSalary() + "): ");
                            String updatedBaseSalary = scanner.nextLine();
                            if (!updatedBaseSalary.isEmpty()) {
                                salaryToUpdate.setBaseSalary(Integer.parseInt(updatedBaseSalary));
                            }
                            System.out.print("Enter bonus (" + salaryToUpdate.getBonus() + ")");


                        }
                }
            }
        }
    }
}
