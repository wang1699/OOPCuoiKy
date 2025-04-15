package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuaHocVienGUI extends JDialog {
	private JTextField tfMaHV, tfTenHV, tfNgaySinh, tfEmail;
	private JButton btnXacNhan, btnHuy;
	private boolean confirmed = false;

	public enum Mode {
		THEM, CHINH_SUA
	}

	public SuaHocVienGUI(JFrame parent, Mode mode, String maHV, String tenHV, Date ngaySinh, String email) {
		super(parent, mode == Mode.THEM ? "Thêm học viên" : "Chỉnh sửa học viên", true);
		setLayout(new GridLayout(5, 2, 5, 5));
		setSize(400, 250);
		setLocationRelativeTo(parent);

		add(new JLabel("Mã học viên:"));
		tfMaHV = new JTextField(maHV != null ? maHV : "");
		if (mode == Mode.CHINH_SUA)
			tfMaHV.setEditable(false);
		add(tfMaHV);

		add(new JLabel("Tên học viên:"));
		tfTenHV = new JTextField(tenHV != null ? tenHV : "");
		add(tfTenHV);

		add(new JLabel("Ngày sinh (yyyy-MM-dd):"));
		String ngayStr = "";
		if (ngaySinh != null) {
			ngayStr = new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh);
		}
		tfNgaySinh = new JTextField(ngayStr);
		add(tfNgaySinh);

		add(new JLabel("Email:"));
		tfEmail = new JTextField(email != null ? email : "");
		add(tfEmail);

		btnXacNhan = new JButton(mode == Mode.THEM ? "Thêm" : "Cập nhật");
		btnHuy = new JButton("Hủy");

		add(btnXacNhan);
		add(btnHuy);

		btnXacNhan.addActionListener(e -> {
			confirmed = true;
			setVisible(false);
		});

		btnHuy.addActionListener(e -> {
			confirmed = false;
			setVisible(false);
		});
	}
	
}