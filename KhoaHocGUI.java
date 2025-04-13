package gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;



public class KhoaHocGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btn_showDS;
	private JButton btn_TimKiem;
	private JButton btn_Xoa;
	private JButton btn_Them;
	private JButton btn_fixInfo;
	private JButton btn_Back;

	/**
	/**
	 * Create the frame.
	 */
	public KhoaHocGUI() {
		setTitle("Khóa học");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1000, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26, 10, 686, 536);
        contentPane.add(scrollPane);
        
        btn_showDS = new JButton("Hiển thị danh sách khóa học");
        btn_showDS.setBounds(727, 20, 195, 40);
        contentPane.add(btn_showDS);
        
        btn_TimKiem = new JButton("Tìm kiếm khóa học");
        btn_TimKiem.setBounds(727, 70, 195, 40);
        contentPane.add(btn_TimKiem);
        
        btn_Xoa = new JButton("Xóa khóa học");
        btn_Xoa.setBounds(727, 120, 195, 40);
        contentPane.add(btn_Xoa);
       
        btn_Them = new JButton("Thêm khóa học");
        btn_Them.setBounds(727, 170, 195, 40);
        contentPane.add(btn_Them);
        
        btn_fixInfo = new JButton("Sửa khóa học");
        btn_fixInfo.setBounds(727, 220, 195, 40);
        contentPane.add(btn_fixInfo);
        
        btn_Back = new JButton("Quay lại");
        btn_Back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminGUI().setVisible(true);
        		setVisible(false);
        	}
        });
        
        btn_Back.setBounds(727, 491, 195, 40);
        contentPane.add(btn_Back);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new KhoaHocGUI());
	}
}
