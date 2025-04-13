package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame{
	public AdminGUI() {
		  	setTitle("ADMIN");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chỉ đóng frame này
	        setSize(800, 500);
	        setResizable(false);
	        getContentPane().setLayout(null);

	        JLabel titleLabel = new JLabel("Chào mừng đến với hệ thống quản lý khóa học trực tuyến", SwingConstants.CENTER);
	        titleLabel.setBounds(0, 20, 774, 30);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        getContentPane().add(titleLabel);

	        JButton btnKhoaHoc = new JButton("Khóa học");
	        btnKhoaHoc.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		new KhoaHocGUI().setVisible(true);
	        		setVisible(false);
	        	}
	        });
	        btnKhoaHoc.setBounds(324, 115, 114, 40);
	        getContentPane().add(btnKhoaHoc);

	        JButton btnNewButton = new JButton("Tài khoản");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new TaiKhoanGUI().setVisible(true);
	        		setVisible(false);
	        	}
	        });
	        btnNewButton.setBounds(324, 165, 114, 40);      
	        getContentPane().add(btnNewButton);

	        JButton btnHocVIen = new JButton("Học viên");
	        btnHocVIen.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new HocVienGUI().setVisible(true);
        		setVisible(false);
	        	}
	        });
	        
	        btnHocVIen.setBounds(324, 215, 114, 40);
	        getContentPane().add(btnHocVIen);

	        JButton btnGiangVien = new JButton("Giảng viên");
	        btnGiangVien.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new GiangVienGUI().setVisible(true);
	        		 setVisible(false);
	        	}
	        });
	        btnGiangVien.setBounds(324, 265, 114, 40);
	        getContentPane().add(btnGiangVien);

	        JButton btnDangXuat = new JButton("Đăng xuất");
	        btnDangXuat.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		new LoginGUI().setVisible(true);
	        		setVisible(false);
	        	}
	        });
	        btnDangXuat.setBounds(660, 411, 114, 40);
	        getContentPane().add(btnDangXuat);

	        setLocationRelativeTo(null);
	        setVisible(true);
	}
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new AdminGUI());
    }
}
