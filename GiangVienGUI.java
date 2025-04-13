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
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        btnDSGV.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDSGV);
        
        Component verticalStrut_1 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_1);
  
        JButton btnDSKHGVPT = new JButton("Danh sách khóa học giảng viên phụ trách");
        btnDSKHGVPT.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String giangVienId = JOptionPane.showInputDialog(null, "Nhập ID giảng viên:");
        		if ( giangVienId != null) {
        			if (giangVienId.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập ID giảng viên");
                    }
        		}
        		
      		List<String[]> danhSach = new ArrayList<>();
      		 	danhSach.add(new String[]{"KH001", "Lập trình Java", "GV001"});
                danhSach.add(new String[]{"KH002", "Cơ sở dữ liệu", "GV001"});
        		System.out.println("Chỉ mục dòng được chọn: " + giangVienId);
        		
        		DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                String[] columnNames = {"Mã KH", "Tên KH", "GV"};
                model.setColumnIdentifiers(columnNames);
//                table.getColumnModel().getColumn(0).setHeaderValue("Mã KH");
//                table.getColumnModel().getColumn(1).setHeaderValue("Tên KH");
//                table.getColumnModel().getColumn(2).setHeaderValue("Giảng viên");
//           table.getTableHeader().repaint(); // cập nhật giao diện

                // Thêm dòng mới vào bảng
                for (String[] row : danhSach) {
                    model.addRow(row);
                }
        	}
        });
        btnDSKHGVPT.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDSKHGVPT);
        
        
        Component verticalStrut_4_1 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_4_1);
        
        JButton btnDSHV = new JButton("Danh sách học viên của giảng viên");
        btnDSHV.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDSHV);
        
        Component verticalStrut_4 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_4);
        
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(new ActionListener() {
        	DefaultTableModel model = (DefaultTableModel) table.getModel();
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    System.out.println("Chỉ mục dòng được chọn: " + selectedRow);
                   // model.removeRow(selectedRow);
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xóa dòng này?\n",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(selectedRow);
                        System.out.println("Đã xóa dòng.");
                    }
                } else {
                	JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.");
                	 
                    System.out.println("Chưa chọn dòng nào.");
                }
                
        	}
        });
        panel_1.add(btnTimKiem);
        btnTimKiem.setMaximumSize(new Dimension(300, 30));
        panel_1.add(Box.createVerticalStrut(10));
     
        
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnXoa);
        
        Component verticalStrut = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut);
        
        JButton btnDiemDanh = new JButton("Điểm danh");
        btnDiemDanh.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnDiemDanh);
        
        Component verticalStrut_2 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_2);
        
        JButton btnSuaDiem = new JButton("Sửa điểm");
        btnSuaDiem.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnSuaDiem);
        
        Component verticalStrut_3 = Box.createVerticalStrut(10);
        panel_1.add(verticalStrut_3);
        
        JButton btnBack = new JButton("Quay lại");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new AdminGUI().setVisible(true);
        		setVisible(false);
        	}
        });
        btnBack.setMaximumSize(new Dimension(300, 30));
        panel_1.add(btnBack);
        

        setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GiangVienGUI());
	}

	

}
