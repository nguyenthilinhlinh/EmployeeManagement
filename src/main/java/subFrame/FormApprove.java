package subFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class FormApprove extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JButton btnApproved;
	private JButton btnRejected;
	private JTextField txtStatus;
	private JPanel panel;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblTypeLeave;
	private JTextField txtTypeLeave;
	private JLabel lblEffectiveTime;
	private JTextField txtStartDate;
	private JLabel lblTo;
	private JTextField txtEndDate;
	private JTextPane textPaneReSon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormApprove frame = new FormApprove();
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
	public FormApprove() {
		setTitle("Form Approve");
		setClosable(true);
		setBounds(100, 100, 695, 385);
		getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Pending");
		btnNewButton.setBounds(10, 11, 89, 23);
		getContentPane().add(btnNewButton);
		
		btnApproved = new JButton("Approved");
		btnApproved.setBounds(109, 11, 89, 23);
		getContentPane().add(btnApproved);
		
		btnRejected = new JButton("Rejected");
		btnRejected.setBounds(208, 11, 89, 23);
		getContentPane().add(btnRejected);
		
		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setBounds(542, 12, 127, 20);
		getContentPane().add(txtStatus);
		txtStatus.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 67, 659, 258);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblName = new JLabel("Name :");
		lblName.setBounds(10, 97, 46, 14);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(102, 94, 254, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		lblTypeLeave = new JLabel("Type Leave :");
		lblTypeLeave.setBounds(10, 136, 71, 14);
		panel.add(lblTypeLeave);
		
		txtTypeLeave = new JTextField();
		txtTypeLeave.setHorizontalAlignment(SwingConstants.CENTER);
		txtTypeLeave.setColumns(10);
		txtTypeLeave.setBounds(102, 133, 223, 20);
		panel.add(txtTypeLeave);
		
		lblEffectiveTime = new JLabel("Effective time :");
		lblEffectiveTime.setBounds(10, 172, 82, 14);
		panel.add(lblEffectiveTime);
		
		txtStartDate = new JTextField();
		txtStartDate.setBounds(102, 169, 158, 20);
		panel.add(txtStartDate);
		txtStartDate.setColumns(10);
		
		lblTo = new JLabel("To");
		lblTo.setBounds(270, 172, 18, 14);
		panel.add(lblTo);
		
		txtEndDate = new JTextField();
		txtEndDate.setBounds(298, 169, 158, 20);
		panel.add(txtEndDate);
		txtEndDate.setColumns(10);
		
		textPaneReSon = new JTextPane();
		textPaneReSon.setBounds(10, 11, 639, 75);
		panel.add(textPaneReSon);

	}
}
