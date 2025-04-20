package giangVienGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.DBCheckExist;
import dao.DiemDanhDAO;
import dao.DiemSoDAO;
import dao.GiangVienDAO;
import dao.KhoaHocDAO;
import gui.LoginGUI;
import model.DiemDanh;
import model.DiemSo;
import model.GiangVien;
import model.HocVien;
import model.KhoaHoc;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginGiangVienGUI extends JFrame {
	/**
	 * Create the frame.
	 */
	private String tableMode = "";
	public LoginGiangVienGUI(String maTaiKhoan) {
		GiangVienDAO dao = new GiangVienDAO();
		GiangVien gv = dao.getGiangVienByTaiKhoan(maTaiKhoan);
		setTitle("Giảng viên " + gv.getTen());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setMinimumSize(new Dimension(800, 500));

		getContentPane().setLayout(new BorderLayout());

		JTable table = new JTable(new DefaultTableModel());
		JScrollPane scrollPane = new JScrollPane(table); // Bắt buộc để scroll và auto resize

		getContentPane().add(scrollPane, BorderLayout.CENTER); // Thêm vào CENTER
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    String maKhoaHoc = table.getValueAt(selectedRow, 0).toString();
                    String maHocVien = table.getValueAt(selectedRow, 1).toString();
                    String tenHocVien = table.getValueAt(selectedRow, 2).toString();

                    if (tableMode.equals("DIEM_DANH")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String ngayHienTai = sdf.format(new Date());

                        int luaChon = JOptionPane.showConfirmDialog(
                            null,
                            "Học viên " + tenHocVien + " (" + maHocVien + ") có đi học không?",
                            "Xác nhận điểm danh",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (luaChon != JOptionPane.CLOSED_OPTION) {
                            boolean coMat = (luaChon == JOptionPane.YES_OPTION);
                            table.setValueAt(ngayHienTai, selectedRow, 4); // Cột ngày
                            table.setValueAt(coMat ? "Có" : "Vắng", selectedRow, 5); // Cột điểm danh

                            // Gọi DAO để lưu vào DB
                            DiemDanhDAO dao = new DiemDanhDAO();
                            dao.diemDanh(maHocVien, maKhoaHoc, ngayHienTai, coMat ? "Có" : "Vắng");
                        }
                    } else if (tableMode.equals("SUA_DIEM")) {
                        String diemStr = JOptionPane.showInputDialog(
                            null,
                            "Nhập điểm cho học viên " + tenHocVien + " (" + maHocVien + "):",
                            "Nhập điểm",
                            JOptionPane.PLAIN_MESSAGE
                        );

                        if (diemStr != null) {
                            try {
                                double diem = Double.parseDouble(diemStr);
                                table.setValueAt(diem, selectedRow, 3); // Cột điểm

                                // Gọi DAO để lưu vào DB
                                DiemSoDAO dao = new DiemSoDAO();
                                dao.diemSo(maHocVien, maKhoaHoc, diem);
                            } catch (NumberFormatException event) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JButton btnDSGV = new JButton("Thông tin cá nhân");
		btnDSGV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiangVienDAO dao = new GiangVienDAO();
				GiangVien gv = dao.getGiangVienByTaiKhoan(maTaiKhoan);
				DefaultTableModel model = new DefaultTableModel();
				table.setDefaultEditor(Object.class, null);
				model.setColumnIdentifiers(new String[] { "Mã Giảng Viên", "Tên Giảng Viên", "SĐT", "Lương" });
				table.setModel(model);
				model.setRowCount(0); // Xóa dữ liệu cũ

				if (gv != null) {
					model.addRow(
							new Object[] { gv.getMaGiangVien(), gv.getTen(), gv.getSoDienThoai(), gv.getTienLuong(), });
				}
			}
		});
		btnDSGV.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnDSGV);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_1);

		JButton btnDSKHGVPT = new JButton("Danh sách khóa học phụ trách");
		btnDSKHGVPT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				KhoaHocDAO dao = new KhoaHocDAO();
				List<KhoaHoc> danhSachKhoaHoc = dao.getKhoaHocByTaiKhoan(maTaiKhoan);
				DefaultTableModel model = new DefaultTableModel();
				table.setDefaultEditor(Object.class, null);
				model.setColumnIdentifiers(
						new String[] { "Mã Khóa Học", "Tên Khóa Học", "Mã Giảng Viên", "Số Tín Chỉ", "Học Phí" });
				table.setModel(model);
				model.setRowCount(0); // Xóa dữ liệu cũ

				for (KhoaHoc kh : danhSachKhoaHoc) {
					model.addRow(new Object[] { kh.getMaKhoaHoc(), kh.getTenKhoaHoc(), kh.getMaGiangVien(),
							kh.getSoTinchi(), kh.getHocPhi(), });
				}
			}

		});
		btnDSKHGVPT.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnDSKHGVPT);

		Component verticalStrut_4_1 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4_1);

		JButton btnDSHV = new JButton("Danh sách học viên của giảng viên");
		btnDSHV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
				if (maKhoaHoc != null) {
					maKhoaHoc = maKhoaHoc.toUpperCase();
					if (maKhoaHoc.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
					} else if (DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
						GiangVienDAO dao = new GiangVienDAO();
						String maGiangVien = dao.getMaGiangVienByTaiKhoan(maTaiKhoan);
						List<HocVien> dsHocVien = dao.getHocVienByKhoaHocAndGiangVien(maKhoaHoc, maGiangVien);
						DefaultTableModel model = new DefaultTableModel();
						table.setDefaultEditor(Object.class, null);
						model.setColumnIdentifiers(
								new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "SĐT" });
						table.setModel(model);
						model.setRowCount(0); // Xóa dữ liệu cũ

						for (HocVien hv : dsHocVien) {
							model.addRow(
									new Object[] { maKhoaHoc, hv.getMaHocVien(), hv.getTen(), hv.getSoDienThoai() });
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mã khóa học không tồn tại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		btnDSHV.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnDSHV);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4);

		JButton btnDiemDanh = new JButton("Danh sách điểm danh");
		btnDiemDanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
				if (maKhoaHoc != null) {
					maKhoaHoc = maKhoaHoc.toUpperCase();
					if (maKhoaHoc.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
					} else if (DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
						
						GiangVienDAO giangVienDAO = new GiangVienDAO();
						String maGiangVien = giangVienDAO.getMaGiangVienByTaiKhoan(maTaiKhoan);
						DiemDanhDAO diemDanhDAO = new DiemDanhDAO();
						List<DiemDanh> dsHocVien = diemDanhDAO.getDanhSachDiemDanh(maKhoaHoc, maGiangVien);
						DefaultTableModel model = new DefaultTableModel();
						table.setDefaultEditor(Object.class, null);
						table.setModel(model);
						model.setColumnIdentifiers(new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "SĐT",
								"Ngày", "Điểm Danh" });
						
						model.setRowCount(0); // Xóa dữ liệu cũ

						for (DiemDanh hv : dsHocVien) {
							model.addRow(new Object[] { maKhoaHoc, hv.getMaHocVien(), hv.getTenHocVien(),
									hv.getSoDienThoai(), hv.getNgay(), hv.getCoMat() });
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mã khóa học không tồn tại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}

		});
		btnDiemDanh.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnDiemDanh);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_2);

		JButton btnSuaDiem = new JButton("Sửa điểm");
		btnSuaDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
				if (maKhoaHoc != null) {
					maKhoaHoc = maKhoaHoc.toUpperCase();
					if (maKhoaHoc.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
					} else if (DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
						tableMode = "SUA_DIEM";
						GiangVienDAO giangVienDAO = new GiangVienDAO();
						String maGiangVien = giangVienDAO.getMaGiangVienByTaiKhoan(maTaiKhoan);
						DiemSoDAO dao = new DiemSoDAO();
						List<DiemSo> dsHocVien = dao.getDanhSachDiem(maGiangVien, maKhoaHoc);
						DefaultTableModel model = new DefaultTableModel();
						table.setDefaultEditor(Object.class, null);
						model.setColumnIdentifiers(
								new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "Điểm" });
						table.setModel(model);
						model.setRowCount(0); // Xóa dữ liệu cũ

						for (DiemSo hv : dsHocVien) {
							model.addRow(
									new Object[] { maKhoaHoc, hv.getMaHocVien(), hv.getTenHocVien(), hv.getDiem() });
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mã khóa học không tồn tại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}

			}
		});
		
		JButton btnDiemDanh_1 = new JButton("Điểm danh");
		btnDiemDanh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiangVienDAO giangVienDAO = new GiangVienDAO();
				String maGiangVien = giangVienDAO.getMaGiangVienByTaiKhoan(maTaiKhoan);
        		
                    	String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
                		if ( maKhoaHoc != null) {
                			maKhoaHoc = maKhoaHoc.toUpperCase();
                			if (maKhoaHoc.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
                            }else if(DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
                            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                String ngayHienTai = sdf.format(new Date());
                                DefaultTableModel model = new DefaultTableModel();
                                 
                            		tableMode = "DIEM_DANH";
                                	DiemDanhDAO khoaHocDAO = new DiemDanhDAO();
                            		List<DiemDanh> dsHocVien = khoaHocDAO.diemDanhSinhVien(maKhoaHoc, maGiangVien );
                    				//DefaultTableModel model = new DefaultTableModel();
                    				table.setDefaultEditor(Object.class, null);
                    				model.setColumnIdentifiers(
                    						new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "SĐT" , "Ngày", "Điểm Danh" });
                    				table.setModel(model);
                    				model.setRowCount(0); // Xóa dữ liệu cũ
                    				 
                    				for (DiemDanh hv : dsHocVien) {
                    					model.addRow(new Object[] {maKhoaHoc, hv.getMaHocVien(), hv.getTenHocVien(), hv.getSoDienThoai(), ngayHienTai, hv.getCoMat() });
                    				}
                                }else {
                                	 JOptionPane.showMessageDialog(null,"Mã khóa học không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                			 
                			  
                            }
                		
                       
                    
        	
			}
		});
		btnDiemDanh_1.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnDiemDanh_1);
		
		Component verticalStrut_2_1 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_2_1);
		btnSuaDiem.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnSuaDiem);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_3);

		JButton btnBack = new JButton("Đăng xuất");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginGUI().setVisible(true);
				dispose();
			}
		});
		btnBack.setMaximumSize(new Dimension(300, 30));
		panel_1.add(btnBack);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LoginGiangVienGUI("TK002"));
	}
}
