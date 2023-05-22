import java.sql.*;
import java.time.LocalDate;

public class TimeOffRequestDAO {
    private Connection connection;

    public TimeOffRequestDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTimeOffRequest(TimeOffRequest timeOffRequest) throws SQLException {
        String sql = "INSERT INTO TimeOffRequests (EmployeeID, StartDate, EndDate, RequestStatus) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, timeOffRequest.getEmployeeId());
            statement.setDate(2, Date.valueOf(timeOffRequest.getStartDate()));
            statement.setDate(3, Date.valueOf(timeOffRequest.getEndDate()));
            statement.setString(4, timeOffRequest.getRequestStatus());
            statement.executeUpdate();
        }
    }

    public void updateTimeOffRequest(TimeOffRequest timeOffRequest) throws SQLException {
        String sql = "UPDATE TimeOffRequests SET StartDate = ?, EndDate = ?, RequestStatus = ? WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(timeOffRequest.getStartDate()));
            statement.setDate(2, Date.valueOf(timeOffRequest.getEndDate()));
            statement.setString(3, timeOffRequest.getRequestStatus());
            statement.setInt(4, timeOffRequest.getEmployeeId());
            statement.executeUpdate();
        }
    }

    public void deleteTimeOffRequest(int employeeId) throws SQLException {
        String sql = "DELETE FROM TimeOffRequests WHERE EmployeeID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        }
    }
}

