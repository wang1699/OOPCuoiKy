package gui;

import java.awt.Font;

import javax.swing.*;

import dao.TaiKhoanDAO;
import giangVienGUI.LoginGiangVienGUI;
import hocVienGUI.LoginHocVienGUI;
import model.TaiKhoan;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {
	public LoginGUI() {
		// Tạo frame
		setTitle("Đăng nhập");
        
        setSize(800, 500);
         setDefaultCloseOperation( EXIT_ON_CLOSE);
         getContentPane().setLayout(null); // Dùng null layout

        // Label tiêu đề
        JLabel titleLabel = new JLabel("Đăng nhập hệ thống", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(10, 54, 764, 25);
         getContentPane().add(titleLabel);

        // Label User
        JLabel userLabel = new JLabel("Tài khoản:");
        userLabel.setBounds(207, 121, 80, 25);
         getContentPane().add(userLabel);

        // TextField User
        JTextField userText = new JTextField();
        userText.setBounds(297, 121, 200, 25);
         getContentPane().add(userText);

        // Label Password
        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setBounds(207, 161, 80, 25);
         getContentPane().add(passLabel);

        // PasswordField
        JPasswordField passText = new JPasswordField();
        passText.setBounds(297, 161, 200, 25);
         getContentPane().add(passText);

        // Button Đăng nhập
        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String username = userText.getText().trim();
                String password = new String(passText.getPassword()).trim();

                TaiKhoanDAO dao = new TaiKhoanDAO();
                TaiKhoan tk = dao.kiemTraDangNhap(username, password);

                if (tk != null) {
//                    JOptionPane.showMessageDialog(LoginGUI.this,
//                            "Đăng nhập thành công!\nVai trò: " + tk.getRole());

                    // Điều hướng tới màn hình tương ứng
                    switch (tk.getRole()) {
                        case "admin":
                            // Mở giao diện admin
                        	new AdminGUI().setVisible(true);
                   		 	dispose();
                            break;
                        case "giangvien":
                            // Mở giao diện giảng viên
                        	new LoginGiangVienGUI(tk.getMaTaiKhoan()).setVisible(true);
                   		 	dispose();
                            break;
                        case "hocvien":
                        	new LoginHocVienGUI(tk.getMaTaiKhoan()).setVisible(true);
                   		 	dispose();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this,
                            "Sai tên đăng nhập hoặc mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        		
        	}
        });
        btnLogin.setBounds(342, 196, 100, 30);
         getContentPane().add(btnLogin);

        // Hiển thị frame
         setLocationRelativeTo(null); // Căn giữa màn hình
         
	}
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
