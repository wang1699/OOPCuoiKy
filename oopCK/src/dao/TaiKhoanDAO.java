package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import gui.LoginGUI;
import model.TaiKhoan;

public class TaiKhoanDAO {
    public TaiKhoan kiemTraDangNhap(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("username"));
                tk.setPassword(rs.getString("password"));
                tk.setRole(rs.getString("role"));
                tk.setId(rs.getString("maTaiKhoan"));
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean taoTaiKhoan(TaiKhoan taiKhoan) {
    	if (DBCheckExist.isMaTonTai("TaiKhoan", "maTaiKhoan", taiKhoan.getMaTaiKhoan())) {
            System.out.println("Mã tài khoản đã tồn tại.");
            JOptionPane.showMessageDialog(null,"Mã tài khoản đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String sql = "INSERT INTO TaiKhoan (username, password, role, maTaiKhoan) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, taiKhoan.getUsername());
            stmt.setString(2, taiKhoan.getPassword()); // Lưu ý: nếu dùng mật khẩu hash thì hash trước
            stmt.setString(3, taiKhoan.getRole());
            stmt.setString(4, taiKhoan.getMaTaiKhoan());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
