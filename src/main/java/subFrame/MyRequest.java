package subFrame;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LeaveHRDao;
import dao.LeaveRequestDao;
import entity.Employees;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MyRequest extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private static MyRequest instance;
	private JScrollPane scrollPane;
	private JTable table;
	private List<Employees> list = null;
	
	
	public List<Employees> getList() {
		return list;
	}
	public void setList(List<Employees> list) {
		this.list = list;
	}
	//	private MyRequest myRequest;
	public static MyRequest getInstance()
	{
	    if (instance == null) {
	        instance = new MyRequest();
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
					MyRequest frame = new MyRequest();
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
	public MyRequest() {
		getContentPane().setBackground(new Color(255, 128, 0));
		getContentPane().setLayout(new BorderLayout());
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		
//		JButton addButton = new JButton("<html>\\r\\n\\r\\n<body>\\r\\n\\r\\n&#43;Add Request\\r\\n\\r\\n</body>\\r\\n</html>");
		JButton addButton = new JButton("add Request");
		addButton.addActionListener(e -> showAddRequest());
		JButton editButton = new JButton("edit Request");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editButtonActionPerformed(e);
			}
		});
//		editButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				editButtonActionPerformed(e);
//			}
//		});
//		editButton.addActionListener(e -> showEditRequestDialog());
		JButton deleteButton = new JButton("delete Request");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRequests(e);
			}
		});
//		JButton deleteButton = new JButton("<html>\\r\\n\\r\\n<body>\\r\\n\\r\\n&#10060; Delete Request\\r\\n\\r\\n</body>\\r\\n</html>");
		
		buttonPane.add(addButton);
		buttonPane.add(editButton);
		buttonPane.add(deleteButton);
		
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		loadLeaveRequest();
		scrollPane.setViewportView(table);
		scrollPane.setViewportView(table);
		setBounds(100, 100, 450, 300);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("My Request");
		
	}
	public void loadLeaveRequest() {
		var model = new DefaultTableModel();
		model.addColumn("requestId");
		model.addColumn("EmployeeName");
		model.addColumn("startData");
		model.addColumn("startEnd");
		model.addColumn("reason");
		model.addColumn("approvalStatus");
		
		LeaveRequestDao dao = new LeaveRequestDao();
		dao.getListLeaveRequest()
		.stream()
		.forEach(request ->
			model.addRow(new Object[] {
					request.getRequestId(),request.getLeaveType(),
					request.getStartDate(), request.getEndDate(), 
					request.getReason(), request.getStatusLR()
			})
			);
		table.setModel(model);
	}

	private void showAddRequest() {
		MakeAnRequest myRequest = new MakeAnRequest();
		if (!myRequest.isVisible()) {
			 getParent().add(myRequest);
			myRequest.setVisible(true);
			myRequest.setMakeAnRequest(this);
			this.setVisible(false);
		}
	}

	


	
// delete requests
	protected void deleteRequests(ActionEvent e) {
		if(!table.isRowSelected(table.getSelectedRow())) {
			JOptionPane.showMessageDialog(null, "vui lòng chọn row để xóa");
			return;
		}
			int i = JOptionPane.showConfirmDialog(null, "có chắc xóa không", "thông báo",JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				
			int id = (int) table.getValueAt(table.getSelectedRow(), 0);
//			kiểm tra trạng thái của đơn xin phép
			if((String) table.getValueAt(table.getSelectedRow(), 5) == "Pending") {
				JOptionPane.showMessageDialog(null, "Không thể xóa");
//					JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(), 5));
				return;
			} 
					
//			JOptionPane.showMessageDialog(null, id);
			var dao = new LeaveHRDao();
			dao.UpdateStatus(id);
			JOptionPane.showMessageDialog(null, "Đã xóa");
			loadLeaveRequest();
		}
	}
	
	protected void editButtonActionPerformed(ActionEvent e) {
		if(!table.isRowSelected(table.getSelectedRow())) {
			JOptionPane.showMessageDialog(null, "vui lòng chọn row để thây đôi");
			return;
		}
		
		int i = JOptionPane.showConfirmDialog(null, "có chắc muốn thay đổi không", "thông báo",JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			
		int id = (int) table.getValueAt(table.getSelectedRow(), 0);
//		kiểm tra trạng thái của đơn xin phép
		if((String) table.getValueAt(table.getSelectedRow(), 5) == "Pending") {
			JOptionPane.showMessageDialog(null, "Không thể Thay đổi");
//				JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(), 5));
			return;
		} 
		
		
		MakeAnRequest myRequest = new MakeAnRequest();
		if (!myRequest.isVisible()) {
			 getParent().add(myRequest);
			myRequest.setVisible(true);
			
//			myRequest.setList();
			myRequest.setMakeAnRequest(this);
			this.setVisible(false);
			}
		}	
		
//		stu.setFullname(textFullname.getText());
//		stu.setGender(ckhGender.isSelected());
//		stu.setDob(dateChooser.getDate());
//		stu.setId(Integer.parseInt(txtId.getText()));
	
//		StudentDao dao = new StudentDao();
//		dao.Update(stu);
//		loadStudent();
////		JOptionPane.showMessageDialog(null, id);
//		var dao = new LeaveHRDao();
//		dao.UpdateStatus(id);
//		JOptionPane.showMessageDialog(null, "Đã xóa");
//		loadLeaveRequest();
//		var dao = new LeaveHRDao();
//	}
	}

}