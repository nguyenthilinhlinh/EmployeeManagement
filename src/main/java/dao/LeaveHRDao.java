package dao;

import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import entity.LeaveHistory;

public class LeaveHRDao {
	public List<LeaveHistory> getListLH(int id) {
		List<LeaveHistory> list = new ArrayList<>(); 
		try (
				var con = ConnectDB.connect();
				var cs = con.prepareCall("{call selectLeaveHEmp(?)}");
				
		){
			cs.setInt(1, id);
			var result = cs.executeQuery();
			
			
			while (result.next()) {
				var lh = new LeaveHistory();
				lh.setHistoryId(result.getInt("HistoryID"));
				lh.setEmployeeId(result.getInt("EmployeeID"));
				lh.setLeaveType(result.getString("LeaveType"));
				lh.setApprovalStatus(result.getString("ApprovalStatus"));
				lh.setStartDate(result.getDate("StartDate"));
				lh.setEndDate(result.getDate("EndDate"));
				lh.setSubmissionDate(result.getDate("SubmissionDate"));
				lh.setApprovalDate(result.getDate("ApprovalDate"));
				lh.setReason(result.getString("Reason"));
				lh.setStatus(result.getBoolean("Status"));
				list.add(lh);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void UpdateStatus(int id) {
		try (
				var con = ConnectDB.connect();
				var cs = con.prepareCall("{call UpdateStatus(?)}");
			){
				cs.setInt(1,id);
				cs.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
//	public List<LeaveHistory> getListLH(String id){
//		
//	}
}
