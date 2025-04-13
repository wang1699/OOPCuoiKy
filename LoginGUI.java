package gui;

import java.awt.Font;

import javax.swing.*;
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
        		new AdminGUI().setVisible(true);
       		 	setVisible(false);
        	}
        });
        btnLogin.setBounds(342, 196, 100, 30);
         getContentPane().add(btnLogin);

        // Hiển thị frame
         setLocationRelativeTo(null); // Căn giữa màn hình
         setVisible(true);
	}
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
