import java.sql.*;
import java.time.LocalDate;

public class SalaryDAO {
    private Connection connection;

    public SalaryDAO(Connection connection) {
        this.connection = connection;
    }

    public Salary getSalaryByEmployeeId(int employeeId) throws SQLException {
        String sql = "SELECT * FROM Salaries WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int baseSalary = resultSet.getInt("BaseSalary");
                    int bonus = resultSet.getInt("Bonus");
                    LocalDate effectiveDate = resultSet.getDate("EffectiveDate").toLocalDate();
                    return new Salary(employeeId, baseSalary, bonus, effectiveDate);
                } else {
                    return null;
                }
            }
        }
    }

    public void addSalary(Salary salary) throws SQLException {
        String sql = "INSERT INTO Salaries (EmployeeID, BaseSalary, Bonus, EffectiveDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salary.getEmployeeId());
            statement.setInt(2, salary.getBaseSalary());
            statement.setInt(3, salary.getBonus());
            statement.setDate(4, Date.valueOf(salary.getEffectiveDate()));
            statement.executeUpdate();
        }
    }

    public void updateSalary(Salary salary) throws SQLException {
        String sql = "UPDATE Salaries SET BaseSalary = ?, Bonus = ?, EffectiveDate = ? WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salary.getBaseSalary());
            statement.setInt(2, salary.getBonus());
            statement.setDate(3, Date.valueOf(salary.getEffectiveDate()));
            statement.setInt(4, salary.getEmployeeId());
            statement.executeUpdate();
        }
    }

    public void deleteSalary(int employeeId) throws SQLException {
        String sql = "DELETE FROM Salaries WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        }
    }
}
