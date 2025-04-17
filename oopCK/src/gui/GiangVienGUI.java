package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DBCheckExist;
import dao.DiemDanhDAO;
import dao.DiemSoDAO;
import dao.GiangVienDAO;
import dao.HocVienDAO;
import dao.KhoaHocDAO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class GiangVienGUI extends JFrame {
	/**
	 * Create the frame.
	 */
	public GiangVienGUI() {
		setTitle("Giảng viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setMinimumSize(new Dimension(800, 500));
        
        getContentPane().setLayout(new BorderLayout());
        
        JTable table = new JTable(new DefaultTableModel());
        JScrollPane scrollPane = new JScrollPane(table); // Bắt buộc để scroll và auto resize

        getContentPane().add(scrollPane, BorderLayout.CENTER); // Thêm vào CENTER
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel_1, BorderLayout.EAST);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        
        JButton btnDSGV = new JButton("Danh sách giảng viên");
        btnDSGV.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GiangVienDAO dao = new GiangVienDAO();
        		List<GiangVien> dsGiangVien = dao.getAllGiangVien();
        		DefaultTableModel model = new DefaultTableModel();
        		table.setDefaultEditor(Object.class, null);
        		model.setColumnIdentifiers(new String[] { "Mã Giảng Viên", "Tên Giảng Viên", "SĐT", "Lương"});
        		table.setModel(model);
        		model.setRowCount(0); // Xóa dữ liệu cũ

        		for (GiangVien gv : dsGiangVien) {
        		    model.addRow(new Object[] {
        		        gv.getMaGiangVien(),
        		        gv.getTen(),
        		        gv.getSoDienThoai(),
        		        gv.getTienLuong(),
        		    });
        		}
        	}
        });
        btnDSGV.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDSGV);
        
        Component verticalStrut_1 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_1);
  
        JButton btnDSKHGVPT = new JButton("Danh sách khóa học giảng viên phụ trách");
        btnDSKHGVPT.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String maGiangVien = JOptionPane.showInputDialog(null, "Nhập ID giảng viên:");
        		if ( maGiangVien != null) {
        			maGiangVien = maGiangVien.toUpperCase();
        			if (maGiangVien.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập ID giảng viên");
                    }else if(DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", maGiangVien)) {
                    	GiangVienDAO dao = new GiangVienDAO();
        				List<KhoaHoc> dsKhoaHoc = dao.getKhoaHocByMaGiangVien(maGiangVien);
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
                    }else {
                    	 JOptionPane.showMessageDialog(null,"Mã giảng viên không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
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
        		String maGiangVien = JOptionPane.showInputDialog(null, "Nhập mã giảng viên:");
        		if ( maGiangVien != null) {
        			maGiangVien = maGiangVien.toUpperCase();
        			if (maGiangVien.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã giảng viên");
                    }else if(DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", maGiangVien)){
                    	String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
                		if ( maKhoaHoc != null) {
                			maKhoaHoc = maKhoaHoc.toUpperCase();
                			if (maKhoaHoc.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
                            }else if(DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
                                	GiangVienDAO dao = new GiangVienDAO();
                            		List<HocVien> dsHocVien = dao.getHocVienByKhoaHocAndGiangVien(maKhoaHoc, maGiangVien );
                    				DefaultTableModel model = new DefaultTableModel();
                    				table.setDefaultEditor(Object.class, null);
                    				model.setColumnIdentifiers(
                    						new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "SĐT" });
                    				table.setModel(model);
                    				model.setRowCount(0); // Xóa dữ liệu cũ

                    				for (HocVien hv : dsHocVien) {
                    					model.addRow(new Object[] {maKhoaHoc, hv.getMaHocVien(), hv.getTen(), hv.getSoDienThoai() });
                    				}
                                }else {
                                	 JOptionPane.showMessageDialog(null,"Mã khóa học không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                		}else {
                       	 JOptionPane.showMessageDialog(null,"Mã giảng viên không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                       }
                    }
        		}
        		

        	
        });
        btnDSHV.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDSHV);
        
        Component verticalStrut_4 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_4);
        
        JButton btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(new ActionListener() {
        	DefaultTableModel model = (DefaultTableModel) table.getModel();
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	String maHV = table.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xóa dòng này?\n",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        
                      
                        GiangVienDAO dao = new GiangVienDAO();
                        boolean success= dao.xoaGiangVien(maHV);
                        if (success) {
                        	DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Xóa thành công!");
                            // Gợi ý: gọi lại phương thức loadTable() để cập nhật JTable
                        }
                    }
                } else {
                	JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
                	
                }
                
        	}
        });
        panel_1.add(btnXoa);
        btnXoa.setMaximumSize(new Dimension(300, 30));
        panel_1.add(Box.createVerticalStrut(10));
     
        
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String maGiangVien = JOptionPane.showInputDialog(null, "Nhập mã học viên:");
        		if ( maGiangVien != null) {
        			maGiangVien = maGiangVien.toUpperCase();
        			if (maGiangVien.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã học viên");
                    }else if(DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", maGiangVien)){
                    	GiangVienDAO dao = new GiangVienDAO();
                    	GiangVien gv = dao.timKiemGiangVien(maGiangVien);
                    	DefaultTableModel model = new DefaultTableModel();
                    	table.setDefaultEditor(Object.class, null);
                		model.setColumnIdentifiers(new String[] { "Mã Giảng Viên", "Tên Giảng Viên", "SĐT", "Lương"});
                		table.setModel(model);
                		model.setRowCount(0); // Xóa dữ liệu cũ

                		if ( gv != null) {
                		    model.addRow(new Object[] {
                		        gv.getMaGiangVien(),
                		        gv.getTen(),
                		        gv.getSoDienThoai(),
                		        gv.getTienLuong(),
                		    });
                		}
                    }else {
                    	JOptionPane.showMessageDialog(null,"Mã giảng viên không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
        		}
        	}
        });
        btnTimKiem.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnTimKiem);
        
        Component verticalStrut = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut);
        
        JButton btnDiemDanh = new JButton("Điểm danh");
        btnDiemDanh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
            		String maGiangVien = JOptionPane.showInputDialog(null, "Nhập mã giảng viên:");
            		if ( maGiangVien != null) {
            			maGiangVien = maGiangVien.toUpperCase();
            			if (maGiangVien.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã giảng viên");
                        }else if(DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", maGiangVien)){
                        	String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
                    		if ( maKhoaHoc != null) {
                    			maKhoaHoc = maKhoaHoc.toUpperCase();
                    			if (maKhoaHoc.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
                                }else if(DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
                                    	DiemDanhDAO dao = new DiemDanhDAO();
                                		List<DiemDanh> dsHocVien = dao.getDanhSachDiemDanh(maKhoaHoc, maGiangVien );
                        				DefaultTableModel model = new DefaultTableModel();
                        				table.setDefaultEditor(Object.class, null);
                        				model.setColumnIdentifiers(
                        						new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "SĐT" , "Ngày", "Điểm Danh" });
                        				table.setModel(model);
                        				model.setRowCount(0); // Xóa dữ liệu cũ

                        				for (DiemDanh hv : dsHocVien) {
                        					model.addRow(new Object[] {maKhoaHoc, hv.getMaHocVien(), hv.getTenHocVien(), hv.getSoDienThoai(), hv.getNgay(), hv.getCoMat() });
                        				}
                                    }else {
                                    	 JOptionPane.showMessageDialog(null,"Mã khóa học không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                    }
                    			table.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                                            
                                            int selectedRow = table.getSelectedRow();
                                            String maKhoaHoc = table.getValueAt(selectedRow, 0).toString();
                                            String maHocVien = table.getValueAt(selectedRow, 1).toString(); // cột 1 là mã học viên
                                            String tenHocVien = table.getValueAt(selectedRow, 2).toString(); // cột 2 là tên
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
                                            
                                        }
                                    }
                                });
                                }
                    		}else {
                           	 JOptionPane.showMessageDialog(null,"Mã giảng viên không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        		String maGiangVien = JOptionPane.showInputDialog(null, "Nhập mã giảng viên:");
        		if ( maGiangVien != null) {
        			maGiangVien = maGiangVien.toUpperCase();
        			if (maGiangVien.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã giảng viên");
                    }else if(DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", maGiangVien)){
                    	String maKhoaHoc = JOptionPane.showInputDialog(null, "Nhập mã khóa học:");
                		if ( maKhoaHoc != null) {
                			maKhoaHoc = maKhoaHoc.toUpperCase();
                			if (maKhoaHoc.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã khóa học");
                            }else if(DBCheckExist.isMaTonTai("KhoaHoc", "maKhoaHoc", maKhoaHoc)) {
                                	DiemSoDAO dao = new DiemSoDAO();
                            		List<DiemSo> dsHocVien = dao.getDanhSachDiem(maGiangVien, maKhoaHoc );
                    				DefaultTableModel model = new DefaultTableModel();
                    				table.setDefaultEditor(Object.class, null);
                    				model.setColumnIdentifiers(
                    						new String[] { "Mã Khóa Học", "Mã Học Viên", "Tên Học Viên", "Điểm" });
                    				table.setModel(model);
                    				model.setRowCount(0); // Xóa dữ liệu cũ

                    				for (DiemSo hv : dsHocVien) {
                    					model.addRow(new Object[] {maKhoaHoc, hv.getMaHocVien(), hv.getTenHocVien(), hv.getDiem() });
                    				}
                                }else {
                                	 JOptionPane.showMessageDialog(null,"Mã khóa học không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                			table.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                                        
                                        int selectedRow = table.getSelectedRow();
                                        String maKhoaHoc = table.getValueAt(selectedRow, 0).toString();
                                        String maHocVien = table.getValueAt(selectedRow, 1).toString(); // cột 1 là mã học viên
                                        String tenHocVien = table.getValueAt(selectedRow, 2).toString(); // cột 2 là tên
                                        

                                        String diemStr = JOptionPane.showInputDialog(
                                        	    null,
                                        	    "Nhập điểm cho học viên " + tenHocVien + " (" + maHocVien + "):",
                                        	    "Nhập điểm",
                                        	    JOptionPane.PLAIN_MESSAGE
                                        	);

                                        	if (diemStr != null) { // Kiểm tra người dùng có nhấn Cancel không
                                        	    try {
                                        	        double diem = Double.parseDouble(diemStr); // Ép kiểu về số nếu cần
                                        	        table.setValueAt(diem, selectedRow, 3); // Giả sử cột điểm là cột số 3

                                        	        // Gọi DAO để lưu vào DB
                                        	        DiemSoDAO dao = new DiemSoDAO();
                                        	        dao.diemSo(maHocVien, maKhoaHoc, diem);
                                        	    } catch (NumberFormatException event) {
                                        	        JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                        	    }
                                        	}

                                        
                                    }
                                }
                            });
                            }
                		}else {
                       	 JOptionPane.showMessageDialog(null,"Mã giảng viên không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                       }
                    }
        	}
        });
        btnSuaDiem.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnSuaDiem);
        
        Component verticalStrut_3 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_3);
        
        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminGUI().setVisible(true);
        		dispose();
        	}
        });
        btnBack.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnBack);
        

        setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GiangVienGUI());
		Date a = new Date();
		System.out.println(a.getDate());
	}
}
