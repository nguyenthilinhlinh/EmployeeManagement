package dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.LeaveRequests;

public class LeaveRequestDao {
	public static CallableStatement createCS(Connection con, int id) throws Exception {
		var cs = con.prepareCall("{call selectLeaveRequests(?)}");
		cs.setInt(1, id);
		return cs;
	}
	
	public List<LeaveRequests> getListLeaveRequest() {
		List<LeaveRequests> list = new ArrayList<>();
		try(
				
				var conection = ConnectDB.connect();
				var cs = conection.prepareCall("{call selectLeaveRequests}");
				
				var result = cs.executeQuery();
			
				){
			
			while(result.next()) {
				var leaveRequest = new LeaveRequests();
				leaveRequest.setRequestId(result.getInt("RequestID"));
				leaveRequest.setEmployeeId(result.getInt("EmployeeID"));
//				leaveRequest.setName(result.getString("Name"));
				leaveRequest.setLeaveType(result.getString("LeaveType"));
				leaveRequest.setStartDate(result.getDate("startDate"));
				leaveRequest.setEndDate(result.getDate("endDate"));
				leaveRequest.setReason(result.getString("reason"));
				leaveRequest.setStatusLR(result.getString("StatusLR"));
				leaveRequest.setStatus(result.getBoolean("Status"));
			
				leaveRequest.setApproverId(result.getInt("ApproverID"));
//				leaveRequest.setSubmissionDatel(result.getDate("SubmissionDatel"));
//				System.out.println("Employee Name: " + result.getString("Name"));
				list.add(leaveRequest);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<LeaveRequests> getListLeaveRequest(int id){
		List<LeaveRequests> list = new ArrayList<>();
		try(
				
				var conection = ConnectDB.connect();
				var cs = createCS(conection, id);
				
				var result = cs.executeQuery();
			
				){
			
			while(result.next()) {
				var leaveRequest = new LeaveRequests();
				leaveRequest.setRequestId(result.getInt("RequestID"));
				leaveRequest.setEmployeeId(result.getInt("EmployeeID"));
				leaveRequest.setLeaveType(result.getString("LeaveType"));
				leaveRequest.setStartDate(result.getDate("startDate"));
				leaveRequest.setEndDate(result.getDate("endDate"));
				leaveRequest.setReason(result.getString("reason"));
				leaveRequest.setStatusLR(result.getString("StatusLR"));
				leaveRequest.setStatus(result.getBoolean("Status"));
				leaveRequest.setApproverId(result.getInt("ApproverID"));
				leaveRequest.setSubmissionDate(result.getDate("SubmissionDate"));
//				System.out.println("Employee Name: " + result.getString("Name"));
				list.add(leaveRequest);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
			
		

	
}
