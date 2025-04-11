package project;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class login extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    public static HashMap<String, String> userDatabase = new HashMap<>();

    public login() {
        setTitle("Đăng nhập hệ thống");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tài khoản mặc định
        userDatabase.put("admin", "1");
        userDatabase.put("hv_1", "1");
        userDatabase.put("gv_1", "1");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Tài khoản:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("Mật khẩu:"));
        txtPass = new JPasswordField();
        panel.add(txtPass);

        JButton btnLogin = new JButton("Đăng nhập");
        panel.add(new JLabel());
        panel.add(btnLogin);

        getContentPane().add(panel);

        btnLogin.addActionListener(e -> checkLogin());
    }

    private void checkLogin() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (!userDatabase.containsKey(user) || !userDatabase.get(user).equals(pass)) {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
            return;
        }

        switch (user) {
            case "admin" -> new AdminGUI().setVisible(true);
            default -> {
                if (user.startsWith("gv")) new GiangVienGUI().setVisible(true);
                else if (user.startsWith("hv")) new HocVienGUI().setVisible(true);
            }
        }
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login().setVisible(true));
    }
}
