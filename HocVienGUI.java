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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;



public class HocVienGUI extends JFrame {

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
	/**
	 * Create the frame.
	 */
	public HocVienGUI() {
		setTitle("Học viên");
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
        
        btn_showDS = new JButton("Hiển thị danh sách");
        btn_showDS.setBounds(727, 20, 195, 40);
        contentPane.add(btn_showDS);
        
        btn_TimKiem = new JButton("Tìm kiếm học viên");
        btn_TimKiem.setBounds(727, 70, 195, 40);
        contentPane.add(btn_TimKiem);
        
        btn_Xoa = new JButton("Xóa học viên");
        btn_Xoa.setBounds(727, 120, 195, 40);
        contentPane.add(btn_Xoa);
       
        btn_Them = new JButton("Thêm học viên");
        btn_Them.setBounds(727, 170, 195, 40);
        contentPane.add(btn_Them);
        
        btn_fixInfo = new JButton("Sửa học viên");
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
        setLocationRelativeTo(null);
        setVisible(true);
	}
/*	public void addShowListListener(ActionListener listener) {
        btn_showDS.addActionListener(listener);
    }
	public void addEditListener(ActionListener listener) {
        btn_fixInfo.addActionListener(listener);
    }
    public void displayHocVienList(Object[][] data, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        
        // Tùy chỉnh hiển thị
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.setRowHeight(25);
    }
    public String getSelectedHocVien() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return table.getValueAt(selectedRow, 0).toString(); // Lấy mã HV từ cột 0
        }
        return null; // Không có hàng nào được chọn
    }
    
    public Object[] getSelectedHocVienDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return new Object[]{
                table.getValueAt(selectedRow, 0),
                table.getValueAt(selectedRow, 1),
                table.getValueAt(selectedRow, 2),
                table.getValueAt(selectedRow, 3)
            };
        }
        return null;
    }
    
    			//Xoa hoc vien
    public void addDeleteListener(ActionListener listener) {
        btn_Xoa.addActionListener(listener); // Gán sự kiện cho nút Xóa
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Thêm phương thức mới để hiển thị hộp thoại xác nhận
    public int showConfirmDialog(String message, String title) {
        Object[] options = {"Xóa", "Hủy"};
        return JOptionPane.showOptionDialog(
            this,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            options,
            options[1] // Mặc định chọn "Hủy"
        );
    }
    public void loadDataToTable(List<HocVien> dsHocVien) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        for (HocVien hv : dsHocVien) {
            model.addRow(new Object[]{
                hv.getMaHV(),
                hv.getTenHV(),
                hv.getNgaySinh() != null ? sdf.format(hv.getNgaySinh()) : "",
                hv.getEmail()
            });
        }
    }
 */   
    				//Nut xu ly them hoc vien
    public void addInsertListener(ActionListener listener) {
        btn_Them.addActionListener(listener);
    }
    public void addSearchListener(ActionListener listener) {
        btn_TimKiem.addActionListener(listener);
    }
    public void addBackListener(ActionListener listener) {
        btn_Back.addActionListener(listener); // Gắn ActionListener cho btn_Back
    }
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> new HocVienGUI());
    }
}
