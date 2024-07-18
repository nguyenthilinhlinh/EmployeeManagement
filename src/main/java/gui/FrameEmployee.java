package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import subFrame.MakeAnRequest;
import utils.UserSession;
import subFrame.MyRequest;

//import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.event.KeyEvent;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import components.RequestToApprove;
import entity.Employees;

import java.awt.BorderLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JDesktopPane desktopPane;
	private JPanel cardPanel;
	private JPanel panel;
	private JMenu mnUser;
	private JMenuItem miProfile;
	private JMenuItem miLogout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEmployee frame = new FrameEmployee();
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
	public FrameEmployee() {
		setTitle("LEAVE REQUEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnUser = new JMenu("");
		menuBar.add(mnUser);
		miProfile = new JMenuItem("Profile");
		mnUser.add(miProfile);
		miLogout = new JMenuItem("Logout");
		mnUser.add(miLogout);

		JMenu mnLeaveRequest = new JMenu("LeaveRequest");
		mnLeaveRequest.setMnemonic('L');
		menuBar.add(mnLeaveRequest);

		JMenuItem requestLeave = new JMenuItem("Make An Request");
		requestLeave.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		requestLeave.addActionListener(e -> showMakeAnRequest());
		mnLeaveRequest.add(requestLeave);
		JMenuItem myRequest = new JMenuItem("My Request");
		myRequest.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		myRequest.addActionListener(e -> showMyRequest());
		mnLeaveRequest.add(myRequest);
		JMenuItem historyRequest = new JMenuItem("History Request");
		historyRequest.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
//		historyRequest.addActionListener(e -> showHistoryRequest() );

		mnLeaveRequest.add(historyRequest);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, "name_1171583838659900");

		cardPanel = new JPanel();
		cardPanel.setBounds(0, 0, 1174, 629);
		desktopPane.add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));

		panel = new JPanel();
		cardPanel.add(panel, "name_1276106924261200");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);

		RequestToApprove requestToApprove = new RequestToApprove();
		requestToApprove.setBounds(46, 91, 785, 460);

		JLabel lblNewLabel_1 = new JLabel("approve an request");
		lblNewLabel_1.setBounds(321, 36, 219, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.YELLOW);
		panel2.add(new JLabel("Panel 2"));

		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.BLUE);
		panel3.add(new JLabel("Panel 3"));
//        ==============================card panel=======================================
//        cardPanel.add(panel_1, "panel1");
//        panel_1.setLayout(null);
//        panel_1.add(requestToApprove);
//        panel_1.add(lblNewLabel_1);
//        cardPanel.add(panel2, "panel2");
//        cardPanel.add(panel3, "panel3");
		Employees loggedInUser = UserSession.getLoggedInUser();

		if (loggedInUser != null) {
			mnUser.setText(loggedInUser.getEmployeeName());
		}

	}
//	protected void showHistoryRequest() {
//		var cardLayout = (CardLayout) cardPanel.getLayout();
//		cardLayout.show(cardPanel, "panel2"); 
//
//	}

	private void showMyRequest() {
		MakeAnRequest makeAnRequest = new MakeAnRequest().getInstance();
		if (!makeAnRequest.isVisible()) {
			desktopPane.add(makeAnRequest);
			makeAnRequest.setVisible(true);
		}
	}

	private void showMakeAnRequest() {
		MyRequest myRequest = new MyRequest().getInstance();
		if (!myRequest.isVisible()) {
			desktopPane.add(myRequest);
			myRequest.setVisible(true);
		}
	}
}
