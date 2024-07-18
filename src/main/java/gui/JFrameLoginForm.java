package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import database.ConnectDB;
import subFrame.MakeAnRequest;
import subFrame.MyRequest;

public class JFrameLoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JCheckBox chkRememberMe;
	private JSeparator separator_1;
	private JButton btnLogin;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					var frame = new JFrameLoginForm();
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
	public JFrameLoginForm() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 514, 297);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 160)));
		panel.setLayout(null);
		panel.setForeground(new Color(255, 0, 128));
		panel.setBackground(new Color(192, 192, 192));
		
		
		lblNewLabel = new JLabel("Login to System");
		lblNewLabel.setBackground(new Color(255, 255, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(170, 23, 181, 41);
		panel.add(lblNewLabel);
		
		separator = new JSeparator();
		separator.setBackground(new Color(255, 0, 128));
		separator.setBounds(0, 75, 506, 7);
		panel.add(separator);
		
		lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setBackground(new Color(255, 255, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(20, 100, 78, 17);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setBackground(new Color(255, 255, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(20, 148, 78, 17);
		panel.add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setColumns(10);
		txtUsername.setBounds(130, 98, 352, 20);
		panel.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setBounds(130, 146, 352, 20);
		panel.add(txtPassword);
		
		chkRememberMe = new JCheckBox("Remember Me ?");
		chkRememberMe.setBackground(new Color(192, 192, 192));
		chkRememberMe.setFont(new Font("Tahoma", Font.BOLD, 11));
		chkRememberMe.setBounds(130, 173, 129, 23);
		panel.add(chkRememberMe);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(255, 0, 128));
		separator_1.setBounds(-10, 203, 516, 7);
		panel.add(separator_1);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 255, 128));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(130, 221, 89, 23);
		panel.add(btnLogin);
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(255, 255, 128));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset();
			}
		});
		contentPane.setLayout(null);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReset.setBounds(337, 221, 97, 23);
		panel.add(btnReset);
		contentPane.add(panel);
		//setLocationRelativeTo(null);
	}
	
	public static CallableStatement createCS(Connection con, String Email, String password) throws Exception {
		var cs = con.prepareCall("{call selectLogin(?,?)}");
		cs.setString(1, Email);
		cs.setString(2, password);
		return cs;
	}
	protected void btnLoginActionPerformed(ActionEvent e) {
		String Email = txtUsername.getText();
		String password = new String (txtPassword.getPassword());
		
		
		StringBuilder sb = new StringBuilder();
		
		if (Email.equals("")) {
			sb.append("username cannot be blank !");
		}
		
		
		if (password.equals("")) {
			sb.append("password cannot be blank");
		}
		
		if (sb.length() > 0) {
			JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		try (
				var con = ConnectDB.connect();
				var cs = createCS(con, Email, password);
				var result = cs.executeQuery();
			){
				if(result != null && result.next()) {
						switch (result.getString("Role")) {
						case "Employee" -> {
							FrameEmployee JFEmp = new FrameEmployee();
							JFEmp.setVisible(true);
							MakeAnRequest MAR = new MakeAnRequest();
							MAR.setIdEmp(result.getInt("EmployeeID"));
//							MAR.setList(list);
							this.setVisible(false);
						}
						
						case "Manager" -> {
							MainJframe JFManage = new MainJframe();
							MyRequest MR = new MyRequest();
							JFManage.setVisible(true);
							this.setVisible(false);
						}
						
						}	 
				} else {
					JOptionPane.showMessageDialog(null, "The account does not exist, please re-enter" );
					Reset();
				}
				
					
				
			}catch (Exception e2) {
				e2.printStackTrace();
			}	
	}
//	protected void btnResetActionPerformed(ActionEvent e) {
//		txtUsername.setText("");
//		txtPassword.setText("");
//		
//		chkRememberMe.setSelected(false);
//	}
	public void Reset() {
		txtUsername.setText("");
		txtPassword.setText("");
		
		chkRememberMe.setSelected(false);
	}
	
}
