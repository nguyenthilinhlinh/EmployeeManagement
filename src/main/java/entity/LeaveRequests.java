package entity;

import java.util.Date;

public class LeaveRequests {
	private int requestId;
    private int employeeId;
    private String employeeName;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String statusLR;
    private int approverId;
    private Date submissionDate;
    private boolean status;
	
	public LeaveRequests() {}
	
	public LeaveRequests(int requestId, int employeeId, String employeeName, String leaveType, Date startDate, Date endDate, String reason, String statusLR, int approverId, Date submissionDate, boolean status) {
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.statusLR = statusLR;
		this.approverId = approverId;
		this.submissionDate = submissionDate;
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatusLR() {
		return statusLR;
	}

	public void setStatusLR(String statusLR) {
		this.statusLR = statusLR;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Override
	public String toString() {
		return "LeaveRequests [requestId=" + requestId + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", leaveType=" + leaveType + ", startDate=" + startDate + ", endDate=" + endDate + ", reason="
				+ reason + ", statusLR=" + statusLR + ", approverId=" + approverId + ", submissionDate="
				+ submissionDate + ", status=" + status + "]";
	}
	
	
	
}
