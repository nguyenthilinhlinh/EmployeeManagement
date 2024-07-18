package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import subFrame.MakeAnRequest;
import subFrame.HistoryRequest;
import subFrame.MyRequest;

import javax.swing.JDesktopPane;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import components.RequestToApprove;

public class MainJframe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JDesktopPane desktopPane;
    private JPanel cardPanel;
    private JMenu mnApprove;
    private JMenuItem HistoryRequest;
    private JMenuItem approveAnRequest;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainJframe frame = new MainJframe();
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
    public MainJframe() {
        setTitle("Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setResizable(false); // Tắt tính năng phóng to

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnLeaveRequest = new JMenu("Leave Request");
        mnLeaveRequest.setMnemonic('L');
        menuBar.add(mnLeaveRequest);

        JMenuItem requestLeave = new JMenuItem("Make An Request");
        requestLeave.setMnemonic('M');
        requestLeave.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        requestLeave.addActionListener(e -> showMakeAnRequest());
        mnLeaveRequest.add(requestLeave);
        
        JMenuItem myRequest = new JMenuItem("My Request");
        myRequest.setMnemonic('M');
        myRequest.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        myRequest.addActionListener(e -> showMyRequest());
        mnLeaveRequest.add(myRequest);
        
        

        JMenu mnApprove = new JMenu("Request to approve");
        mnApprove.setMnemonic('R');
        menuBar.add(mnApprove);

        JMenuItem historyRequest = new JMenuItem("History Request");
        historyRequest.setMnemonic('H');
        historyRequest.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        mnApprove.add(historyRequest);
        

        JMenuItem approveAnRequest = new JMenuItem("Approve An Request");
        approveAnRequest.setMnemonic('A');
        approveAnRequest.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        mnApprove.add(approveAnRequest);

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

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.GREEN);

        RequestToApprove requestToApprove = new RequestToApprove();
        requestToApprove.setBounds(46, 91, 785, 460);

        JLabel lblSearch = new JLabel("approve an request");
        lblSearch.setBounds(321, 36, 219, 22);
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);
        panel2.add(new JLabel("Panel 2"));

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.BLUE);
        panel3.add(new JLabel("Panel 3"));

        cardPanel.add(panel_1, "panel1");
        panel_1.setLayout(null);

    }

    private void showMakeAnRequest() {
        MakeAnRequest makeAnRequest = new MakeAnRequest().getInstance();
        if (!makeAnRequest.isVisible()) {
            desktopPane.add(makeAnRequest);
            makeAnRequest.setVisible(true);
        }
    }

    private void showMyRequest() {
        MyRequest myRequest = new MyRequest().getInstance();
        if (!myRequest.isVisible()) {
            desktopPane.add(myRequest);
            myRequest.setVisible(true);
        }
    }
    
}
