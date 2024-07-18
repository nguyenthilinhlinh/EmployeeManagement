package components;


//import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.LeaveRequestDao;
//import database.ConnectDB;

public class RequestToApprove extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	
	public RequestToApprove() {
		  table = new JTable();
		  loadLeaveRequest();
	        JScrollPane tableScrollPane = new JScrollPane(table);
	        tableScrollPane.setBounds(0, 0, 450, 300);
	        add(tableScrollPane);
	        
	        JButton approveButton = new JButton("Duyệt");
	      
	        JButton rejectButton = new JButton("Từ chối");
	       
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(approveButton);
	        buttonPanel.add(rejectButton);
	        
	        add(tableScrollPane, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);
	        setLayout(new BorderLayout(0, 0));
	        add(tableScrollPane, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);

	}
	public void loadLeaveRequest() {
		String[] columnNames = {"RequestID", "EmployeeID","EmployeeNAme", "LeaveType", "StartDate", "EndDate", "Reason", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        LeaveRequestDao dao = new LeaveRequestDao();
		dao.getListLeaveRequest()
		.stream()
		.forEach(request ->
			model.addRow(new Object[] {
					request.getRequestId(),request.getEmployeeId(),request.getLeaveType(),request.getEmployeeName(), request.getStartDate(), request.getEndDate(), request.getReason(), request.getStatusLR()
			})
			);
		table.setModel(model);
        
	}
//	public void InsertRequest(Student stu) {
//		try(
//				var con = ConnectDB.connect();
//				var cs = con.prepareCall("{call InsertLeaveRequest(?,?,?)}");
//				)
//		
//		{
//			cs.setString(1,stu.getFullName());
//			cs.setBoolean(2, stu.isGender());
//			cs.setDate(3, new java.sql.Date(stu.getDob().getTime()));
//			cs.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
