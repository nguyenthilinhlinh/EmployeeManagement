package subFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JButton;

public class ApproveAnRequest extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelApprove;
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	private JTable table;
	private JButton btnApprove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApproveAnRequest frame = new ApproveAnRequest();
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
	public ApproveAnRequest() {
		setTitle("Approve An Request");
		setBounds(100, 100, 637, 417);
		
		panelApprove = new JPanel();
		getContentPane().add(panelApprove, BorderLayout.SOUTH);
		
		txtSearch = new JTextField();
		txtSearch.setBorder(new TitledBorder(null, "Search :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelApprove.add(txtSearch);
		txtSearch.setColumns(20);
		
		btnApprove = new JButton("Approve");
		panelApprove.add(btnApprove);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

}
