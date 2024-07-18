package subFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

//import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import entity.Employees;
//import entity.LeaveRequests;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
//import java.sql.ResultSet;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import dao.EmployeeDao;
//import dao.LeaveHRDao;
import dao.LeaveRequestDao;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

public class MakeAnRequest extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static MakeAnRequest instance;
	private JPanel panel;
	private JScrollPane scrollPane;
	private MyRequest myRequest;
	private JLabel lblName;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JTable tableLH;
	static int idEmp;
	
	static List<Employees> list=null;
	
	
	
	public static List<Employees> getList() {
		return list;
	}

	public static void setList(List<Employees> list) {
		MakeAnRequest.list = list;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public MyRequest getMakeAnRequest() {
		return myRequest;
	}

	public void setMakeAnRequest(MyRequest myRequest) {
		this.myRequest = myRequest;
	}

	public static MakeAnRequest getInstance()
	{
	    if (instance == null) {
	        instance = new MakeAnRequest();
	    }
	    return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeAnRequest frame = new MakeAnRequest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MakeAnRequest() {
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
        setTitle("Make An Request");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        
      
         lblName = new JLabel("Employee Name:");
        JTextField nameField = new JTextField(20);

        JLabel lblLeaveType = new JLabel("Leave Type:");
        JComboBox<String> leaveTypeComboBox = new JComboBox<>(new String[]{
                "Sick Leave", "Casual Leave", "Maternity Leave", "Other Leave"
        });

        JLabel lblFromDate = new JLabel("Start Date:");

        JLabel lblToDate = new JLabel("To Date:");

        JLabel lblReason = new JLabel("Reason:");
        JTextField reasonField = new JTextField(20);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		btnSubmitActionPerformed(e);
        	}
        });
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e ->btnBackActionPerformed());
        
        dateChooser = new JDateChooser();
        
        dateChooser_1 = new JDateChooser();

        GroupLayout layout = new GroupLayout(panel);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblName)
        				.addComponent(lblLeaveType)
        				.addComponent(lblFromDate)
        				.addComponent(lblToDate)
        				.addComponent(lblReason))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(nameField, 480, 480, Short.MAX_VALUE)
        				.addComponent(leaveTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(reasonField, 480, 480, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnSubmit)
        					.addComponent(btnCancel))
        				.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(dateChooser_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        			.addGap(80))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblName)
        				.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblLeaveType)
        				.addComponent(leaveTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblFromDate)
        				.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblToDate)
        				.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblReason)
        				.addComponent(reasonField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSubmit)
        				.addComponent(btnCancel)))
        );
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableLH = new JTable();
		showHistory();
		scrollPane.setViewportView(tableLH);
        
}
	 
	protected void btnBackActionPerformed() {
		this.setVisible(false);
		MyRequest makeAnRequest2 = new MyRequest();
		makeAnRequest2.setVisible(true);
	}
	
	public void showHistory() {
//		JOptionPane.showMessageDialog(null, idEmp);
		var emp = new Employees();
		
		var model = new DefaultTableModel();
		
		var dao = new LeaveRequestDao();
		var Emp = new EmployeeDao();
		
		Emp.getEmp(idEmp)
		.stream()
		.forEach(e -> {
			emp.setEmployeeID(e.getEmployeeID());
			emp.setEmployeeName(e.getEmployeeName());
			emp.setEmail(e.getEmail());
			emp.setPassword(e.getPassword());
			emp.setRole(e.getRole());
			
		});
		
//		tạo cột
		model.addColumn("FullName");
		model.addColumn("LeaveType");
		model.addColumn("StartDate");
		model.addColumn("EndDate");
		model.addColumn("Reason");
		model.addColumn("ApprovalStatus");
		model.addColumn("ApproverID");
		model.addColumn("SubmissionDate");
		
//		add hang vao	
	
		dao.getListLeaveRequest(idEmp)
		.stream()
		.forEach(
			lr -> {
				model.addRow(new Object[] {
					emp.getEmployeeName(),lr.getLeaveType()
					,lr.getStartDate(),lr.getEndDate(),
					lr.getReason(),lr.getStatusLR()
					,lr.getApproverId(),lr.getSubmissionDate()
					});
					});
//		});
		tableLH.setModel(model);
	}
//	protected void btnSubmitActionPerformed(ActionEvent e) {
//		LeaveRequests  request = new LeaveRequests();
//		request.setEmployeeName(lblName.getText());
//		request.setFullName(textFullname.getText());
//		stu.setGender(ckhGender.isSelected());
//		stu.setDob(dateChooser.getDate());
//		
//		StudentDao dao = new StudentDao();
//		dao.InsertStu(stu);
//		loadStudent();
//	}
}
