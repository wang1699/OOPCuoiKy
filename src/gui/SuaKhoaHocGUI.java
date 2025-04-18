package gui;

import javax.swing.*;

import dao.HocVienDAO;
import dao.KhoaHocDAO;

import java.awt.*;

public class SuaKhoaHocGUI extends JFrame {
	private JTextField tftMaKhoaHoc, tftTenKhoaHoc, tftMaGiangVien, tftSoTinChi, tftHocPhi;
	private JButton btnXacNhan, btnHuy;

	public SuaKhoaHocGUI(String maKhoaHoc, String tenKhoaHoc, String maGiangVien, String soTinChi, String hocPhi) {
		setTitle("Chỉnh sửa học viên");
	//	setLayout(new GridLayout(4, 2, 5, 5));
		getContentPane().setLayout(null);
		setSize(320, 365);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // chỉ đóng cửa sổ này

		JLabel label = new JLabel("Mã khóa học:");
		label.setBounds(10, 10, 115, 30);
		getContentPane().add(label);
		tftMaKhoaHoc = new JTextField(maKhoaHoc != null ? maKhoaHoc : "");
		tftMaKhoaHoc.setBounds(105, 10, 187, 30);
		tftMaKhoaHoc.setEditable(false);
		getContentPane().add(tftMaKhoaHoc);

		JLabel label_1 = new JLabel("Tên khóa học:");
		label_1.setBounds(10, 50, 115, 30);
		getContentPane().add(label_1);
		tftTenKhoaHoc = new JTextField(tenKhoaHoc != null ? tenKhoaHoc : "");
		tftTenKhoaHoc.setLocation(105, 49);
		tftTenKhoaHoc.setSize(187, 30);
		getContentPane().add(tftTenKhoaHoc);

		JLabel label_2 = new JLabel("Mã giảng viên:");
		label_2.setLocation(10, 90);
		label_2.setSize(115, 30);
		getContentPane().add(label_2);
		tftMaGiangVien = new JTextField(maGiangVien != null ? maGiangVien : "");
		tftMaGiangVien.setLocation(105, 90);
		tftMaGiangVien.setSize(187, 30);
		getContentPane().add(tftMaGiangVien);
		
		JLabel label_3 = new JLabel("Số tín chỉ:");
		label_3.setLocation(10, 130);
		label_3.setSize(115, 30);
		getContentPane().add(label_3);
		tftSoTinChi = new JTextField(soTinChi != null ? soTinChi : "");
		tftSoTinChi.setLocation(105, 130);
		tftSoTinChi.setSize(187, 30);
		getContentPane().add(tftSoTinChi);
		
		JLabel label_4 = new JLabel("Học phí:");
		label_4.setLocation(10, 170);
		label_4.setSize(115, 30);
		getContentPane().add(label_4);
		tftHocPhi = new JTextField(hocPhi != null ? hocPhi : "");
		tftHocPhi.setLocation(105, 170);
		tftHocPhi.setSize(187, 30);
		getContentPane().add(tftHocPhi);

		btnXacNhan = new JButton("Cập nhật");
		btnXacNhan.setLocation(37, 210);
		btnXacNhan.setSize(150, 25);
		btnHuy = new JButton("Hủy");
		btnHuy.setLocation(197, 210);
		btnHuy.setSize(71, 25);

		getContentPane().add(btnXacNhan);
		getContentPane().add(btnHuy);

		btnXacNhan.addActionListener(e -> {
			String newTenKhoaHoc = tftTenKhoaHoc.getText().trim();
			String newMaGiangVien = tftMaGiangVien.getText().trim();
			String newSoTinChi = tftSoTinChi.getText().trim();
			String newHocPhi = tftHocPhi.getText().trim();
			
			KhoaHocDAO dao = new KhoaHocDAO();
		    boolean success = dao.capNhat(tftMaKhoaHoc.getText().toUpperCase(), newTenKhoaHoc, newMaGiangVien.toUpperCase(),   Integer.parseInt(newSoTinChi), Double.parseDouble(newHocPhi));

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
