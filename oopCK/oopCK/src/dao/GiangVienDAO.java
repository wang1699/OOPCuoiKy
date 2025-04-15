package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import model.GiangVien;
import model.HocVien;
import model.User;

public class GiangVienDAO {
	public boolean taoGiangVien(GiangVien  giangVien) {
		if (DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", giangVien.getMaGiangVien())) {
           
            JOptionPane.showMessageDialog(null,"Mã giảng viên đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String sql = "INSERT INTO GiangVien (maGiangVien, tenGiangVien, sdtGiangVien, luong, maTaiKhoan) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, giangVien.getMaGiangVien());
            stmt.setString(2, giangVien.getTen());
            stmt.setString(3, giangVien.getSoDienThoai());
            stmt.setDouble(4, giangVien.getTienLuong());
            stmt.setString(5, giangVien.getMaTaiKhoan());
           
         

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
