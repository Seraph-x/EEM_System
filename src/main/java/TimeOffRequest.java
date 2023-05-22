import java.time.LocalDate;
public class TimeOffRequest {
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String requestStatus;

    public TimeOffRequest(int employeeId, LocalDate startDate, LocalDate endDate, String requestStatus) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestStatus = requestStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }
}

