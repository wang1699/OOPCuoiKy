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

import dao.DBCheckExist;
import dao.DangKyDAO;
import dao.GiangVienDAO;
import dao.HocVienDAO;
import dao.KhoaHocDAO;
import model.HocVien;
import model.KhoaHoc;

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
	 * /** Create the frame.
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
		btn_showDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoaHocDAO dao = new KhoaHocDAO();
				List<KhoaHoc> dsKhoaHoc = dao.getAllKhoaHoc();
				DefaultTableModel model = new DefaultTableModel();
				table.setDefaultEditor(Object.class, null);
				model.setColumnIdentifiers(
						new String[] { "Mã Khóa Học", "Tên Khóa Học", "Mã Giảng Viên", "Số Tín Chỉ", "Học Phí" });
				table.setModel(model);
				model.setRowCount(0); // Xóa dữ liệu cũ

				for (KhoaHoc kh : dsKhoaHoc) {
					model.addRow(new Object[] { kh.getMaKhoaHoc(), kh.getTenKhoaHoc(), kh.getMaGiangVien(),
							kh.getSoTinchi(), kh.getHocPhi(), });
				}

			}
		});
		btn_showDS.setBounds(727, 20, 195, 40);
		contentPane.add(btn_showDS);

		btn_TimKiem = new JButton("Tìm kiếm khóa học");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
				if (maKhoaHoc != null) {
					maKhoaHoc = maKhoaHoc.toUpperCase();
					if (maKhoaHoc.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
					} else {
						KhoaHocDAO dao = new KhoaHocDAO();
						KhoaHoc kh = dao.timKiemKhoaHoc(maKhoaHoc);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						table.setDefaultEditor(Object.class, null);
						model.setColumnIdentifiers(new String[] { "Mã Khóa Học", "Tên Khóa Học", "Mã Giảng Viên",
								"Số Tín Chỉ", "Học Phí" });
						model.setRowCount(0); // Xóa dữ liệu cũ

						if (kh != null) {
							model.addRow(new Object[] { kh.getMaKhoaHoc(), kh.getTenKhoaHoc(), kh.getMaGiangVien(),
									kh.getSoTinchi(), kh.getHocPhi(), });
						} else {
							JOptionPane.showMessageDialog(null, "Không tìm thấy khóa học!");
						}
					}
				}

			}
		});
		btn_TimKiem.setBounds(727, 70, 195, 40);
		contentPane.add(btn_TimKiem);

		btn_Xoa = new JButton("Xóa khóa học");
		btn_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String maKhoaHoc = table.getValueAt(selectedRow, 0).toString(); // cột 0 là mã học viên
					int confirm = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn xóa khóa học " + maKhoaHoc + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						KhoaHocDAO dao = new KhoaHocDAO();
						boolean success = dao.xoaKhoaHoc(maKhoaHoc);
						if (success) {
							JOptionPane.showMessageDialog(null, "Xóa thành công. Vui lòng Reload lại để cập nhật!");
							// Gợi ý: gọi lại phương thức loadTable() để cập nhật JTable
						} else {
							JOptionPane.showMessageDialog(null, "Xóa thất bại hoặc khóa học không tồn tại!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn khóa học cần xóa!");
				}
			}
		});
		btn_Xoa.setBounds(727, 120, 195, 40);
		contentPane.add(btn_Xoa);

		btn_Them = new JButton("Thêm khóa học");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemKhoaHocGUI gui = new ThemKhoaHocGUI();
				gui.setVisible(true);
			}
		});
		btn_Them.setBounds(727, 170, 195, 40);
		contentPane.add(btn_Them);

		btn_fixInfo = new JButton("Sửa khóa học");
		btn_fixInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn Khóa học để chỉnh sửa");
					return;
				}
				String maKhoaHoc = table.getValueAt(selectedRow, 0).toString();
				String tenKhoaHoc = table.getValueAt(selectedRow, 1).toString();
				String MaGiangVien = table.getValueAt(selectedRow, 2).toString();
				String soTinChi = table.getValueAt(selectedRow, 3).toString();
				String hocPhi = table.getValueAt(selectedRow, 4).toString();
				SuaKhoaHocGUI dialog = new SuaKhoaHocGUI(maKhoaHoc, tenKhoaHoc, MaGiangVien, soTinChi, hocPhi);
				dialog.setVisible(true);
			}
		});
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

		JButton btn_fixInfo_1 = new JButton("Đăng ký cho học viên");
		btn_fixInfo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKyDAO dao = new DangKyDAO();
				String maHocVien = JOptionPane.showInputDialog(null, "Nhập mã học viên:");
				if (maHocVien != null) {
					maHocVien = maHocVien.toUpperCase();
					if (maHocVien.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã học viên");
					} else if (DBCheckExist.isMaTonTai("HocVien", "maHocVien", maHocVien)) {
						String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
						if (maKhoaHoc != null) {
							maKhoaHoc = maKhoaHoc.toUpperCase();
							if (maKhoaHoc.trim().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
							} else if (DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
								 dao.dangKyKhoaHoc(maHocVien, maKhoaHoc);
								
							} else {
								JOptionPane.showMessageDialog(null, "Mã khóa học không tồn tại", "Lỗi",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mã học viên không tồn tại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				// Giả sử học viên với mã 'HV001' muốn đăng ký khóa học với mã 'KH001'

			}
		});
		btn_fixInfo_1.setBounds(727, 270, 195, 40);
		contentPane.add(btn_fixInfo_1);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new KhoaHocGUI());
	}
}
