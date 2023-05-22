import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                LocalDate hireDate = resultSet.getDate("HireDate").toLocalDate();
                String jobTitle = resultSet.getString("JobTitle");
                int departmentId = resultSet.getInt("Department");
                Employee employee = new Employee(id, firstName, lastName, email, phoneNumber, hireDate, jobTitle, departmentId);
                employees.add(employee);
            }
        }
        return employees;
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employees (FirstName, LastName, Email, PhoneNumber, HireDate, JobTitle, Department) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhoneNumber());
            statement.setDate(5, Date.valueOf(employee.getHireDate()));
            statement.setString(6, employee.getJobTitle());
            statement.setInt(7, employee.getDepartmentId());
            statement.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, HireDate = ?, JobTitle = ?, Department = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhoneNumber());
            statement.setDate(5, Date.valueOf(employee.getHireDate()));
            statement.setString(6, employee.getJobTitle());
            statement.setInt(7, employee.getDepartmentId());
            statement.setInt(8, employee.getId());
            statement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM Employees WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
