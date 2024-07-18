package subFrame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;

public class HistoryRequest extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelSearch;
	private JScrollPane scrollPane;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryRequest frame = new HistoryRequest();
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
	public HistoryRequest() {
		setClosable(true);
		setResizable(true);
		setTitle("History Request");
		setBounds(100, 100, 682, 411);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelSearch = new JPanel();
		getContentPane().add(panelSearch, BorderLayout.SOUTH);
		panelSearch.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSearch = new JLabel("Search :");
		panelSearch.add(lblSearch);
		
		txtSearch = new JTextField();
		panelSearch.add(txtSearch);
		txtSearch.setColumns(50);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}
	
	

}
