package gui;

import javax.swing.*;

import dao.HocVienDAO;

import java.awt.*;

public class SuaHocVienGUI extends JFrame {
	private JTextField tfMaHV, tfTenHV, tfSdt;
	private JButton btnXacNhan, btnHuy;

	public SuaHocVienGUI(String maHV, String tenHV, String sdt) {
		setTitle("Chỉnh sửa học viên");
		setLayout(new GridLayout(4, 2, 5, 5));
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // chỉ đóng cửa sổ này

		add(new JLabel("Mã học viên:"));
		tfMaHV = new JTextField(maHV != null ? maHV : "");
		tfMaHV.setEditable(false);
		add(tfMaHV);

		add(new JLabel("Tên học viên:"));
		tfTenHV = new JTextField(tenHV != null ? tenHV : "");
		add(tfTenHV);

		add(new JLabel("Số điện thoại:"));
		tfSdt = new JTextField(sdt != null ? sdt : "");
		add(tfSdt);

		btnXacNhan = new JButton("Cập nhật");
		btnHuy = new JButton("Hủy");

		add(btnXacNhan);
		add(btnHuy);

		btnXacNhan.addActionListener(e -> {
			String newTen = tfTenHV.getText().trim();
			String newSdt = tfSdt.getText().trim();
			
			HocVienDAO dao = new HocVienDAO();
		    boolean success = dao.capNhat(tfMaHV.getText(), newTen, newSdt);

		    if (success) {
		        JOptionPane.showMessageDialog(this, "Cập nhật thành công. Vui lòng Reload lại để cập nhật!");
		        dispose();
		    } else {
		        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
		    }
			dispose(); // đóng cửa sổ
		});

		btnHuy.addActionListener(e -> dispose());
	}
}
