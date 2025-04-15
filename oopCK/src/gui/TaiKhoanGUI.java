package gui;

import javax.swing.*;

import dao.GiangVienDAO;
import dao.HocVienDAO;
import dao.TaiKhoanDAO;
import model.GiangVien;
import model.HocVien;
import model.TaiKhoan;
import model.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaiKhoanGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField tfUsername, tfPassword, tfMaTaiKhoan, tfMaNguoiDung, tfName, tfPhone, tfSalary;
    private JLabel lblSalary;
    private JComboBox<String> comboBoxRole;

    public TaiKhoanGUI() {
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(800, 500));
        setLocationRelativeTo(null); // Căn giữa màn hình
        setResizable(false);

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
        addRow(formPanel, gbc, y++, "Mã tài khoản:", tfMaTaiKhoan = new JTextField(20));
        addRow(formPanel, gbc, y++, "Mã người dùng:", tfMaNguoiDung = new JTextField(20));
        addRow(formPanel, gbc, y++, "Họ tên:", tfName = new JTextField(20));
        addRow(formPanel, gbc, y++, "Số điện thoại:", tfPhone = new JTextField(20));

        lblSalary = new JLabel("Lương:");
        tfSalary = new JTextField(20);
        addRow(formPanel, gbc, y++, lblSalary, tfSalary);
        lblSalary.setVisible(false);
        tfSalary.setVisible(false);

        // Nút lưu
        JButton btnSave = new JButton("Thêm tài khoản");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(btnSave);

        wrapperPanel.add(formPanel);
        wrapperPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        wrapperPanel.add(buttonPanel);
        
        JButton btnNewButton = new JButton("Quay lại");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminGUI().setVisible(true);
        		setVisible(false);
        	}
        });
        buttonPanel.add(btnNewButton);
        
        
        
        getContentPane().add(wrapperPanel);

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
            String maTaiKhoan = tfMaTaiKhoan.getText().trim();
            String maNguoiDung = tfMaNguoiDung.getText().trim();
            String name = tfName.getText().trim();
            String phone = tfPhone.getText().trim();
            String salary = tfSalary.getText().trim();

            if (username.isEmpty() || password.isEmpty() || maTaiKhoan.isEmpty() ||maNguoiDung.isEmpty() || name.isEmpty() || phone.isEmpty()) {
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
           
            
            
            if (role.equals("Giảng viên")){
            	role = "giangvien";
            	TaiKhoan tk = new TaiKhoan(username, password, role,maTaiKhoan);
                taoTaiKhoan(tk);
            	 GiangVien gv = new GiangVien(username, password, role, maTaiKhoan, phone, name, maNguoiDung, Double.parseDouble(salary));
            	 taoGiangVien(gv);
            	
            }else {
            	role = "hocvien";
            	TaiKhoan tk = new TaiKhoan(username, password, role,maTaiKhoan);
                taoTaiKhoan(tk);
            	HocVien hv = new HocVien(username, password, role, maTaiKhoan , maNguoiDung, phone, name);
            	taoHocVien(hv);
            }
            
            
        });
    }

    private void taoHocVien(HocVien hv) {
    	HocVienDAO dao = new HocVienDAO();
    	
        boolean success = dao.taoHocVien(hv);
        if (success) {
            System.out.println("Tạo học viên thành công!");
            JOptionPane.showMessageDialog(this, "Tạo học viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Tạo học viên thất bại.");
        }
		
	}

	private void taoGiangVien(GiangVien gv) {
		GiangVienDAO dao = new GiangVienDAO();
		    	
	        boolean success = dao.taoGiangVien(gv);
	        if (success) {
	            System.out.println("Tạo giảng viên thành công!");
	            JOptionPane.showMessageDialog(this, "Tạo giảng viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            System.out.println("Tạo giảng viên thất bại.");
	        }
		
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
    public void taoTaiKhoan(TaiKhoan tk) {
    	TaiKhoanDAO dao = new TaiKhoanDAO();
    	
        boolean success = dao.taoTaiKhoan(tk);
        if (success) {
            System.out.println("Tạo tài khoản thành công!");
            JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Tạo tài khoản thất bại.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaiKhoanGUI().setVisible(true));
    }
}
