package project;

import javax.swing.*;
import java.awt.*;

public class AdminGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField tfUsername, tfPassword, tfId, tfName, tfPhone, tfSalary;
    private JLabel lblSalary;
    private JComboBox<String> comboBoxRole;

    public AdminGUI() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 430);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setResizable(true);

        // Panel chính dùng BoxLayout dọc để gói nội dung vào giữa
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Panel chứa các dòng nhập liệu, dùng GridBagLayout để cố định vị trí
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;

        addRow(formPanel, gbc, y++, "Chọn vai trò:", comboBoxRole = new JComboBox<>(new String[]{"Học viên", "Giảng viên"}));
        addRow(formPanel, gbc, y++, "Tên đăng nhập:", tfUsername = new JTextField(20));
        addRow(formPanel, gbc, y++, "Mật khẩu:", tfPassword = new JTextField(20));
        addRow(formPanel, gbc, y++, "ID:", tfId = new JTextField(20));
        addRow(formPanel, gbc, y++, "Họ tên:", tfName = new JTextField(20));
        addRow(formPanel, gbc, y++, "Số điện thoại:", tfPhone = new JTextField(20));

        lblSalary = new JLabel("Lương:");
        tfSalary = new JTextField(20);
        addRow(formPanel, gbc, y++, lblSalary, tfSalary);
        lblSalary.setVisible(false);
        tfSalary.setVisible(false);

        // Nút lưu
        JButton btnSave = new JButton("Lưu");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(btnSave);

        wrapperPanel.add(formPanel);
        wrapperPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        wrapperPanel.add(buttonPanel);

        add(wrapperPanel);

        // Xử lý sự kiện chọn vai trò
        comboBoxRole.addActionListener(e -> {
            boolean isTeacher = comboBoxRole.getSelectedItem().equals("Giảng viên");
            lblSalary.setVisible(isTeacher);
            tfSalary.setVisible(isTeacher);
        });

        // Xử lý nút lưu
        btnSave.addActionListener(e -> {
            String role = (String) comboBoxRole.getSelectedItem();
            String username = tfUsername.getText().trim();
            String password = tfPassword.getText().trim();
            String id = tfId.getText().trim();
            String name = tfName.getText().trim();
            String phone = tfPhone.getText().trim();
            String salary = tfSalary.getText().trim();

            if (username.isEmpty() || password.isEmpty() || id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số!", "Sai định dạng", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (role.equals("Giảng viên")) {
                if (salary.isEmpty() || !salary.matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "Lương phải là số nguyên dương!", "Sai định dạng", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int y, String labelText, JComponent inputField) {
        addRow(panel, gbc, y, new JLabel(labelText), inputField);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int y, JLabel label, JComponent inputField) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.NONE;
        label.setPreferredSize(new Dimension(120, 25));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputField.setPreferredSize(new Dimension(300, 25));
        panel.add(inputField, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminGUI().setVisible(true));
    }
}
