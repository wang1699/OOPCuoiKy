package dao;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangKyDAO {

	// Hàm kiểm tra xem học viên đã đăng ký khóa học chưa
	public boolean kiemTraDangKy(String maHocVien, String maKhoaHoc) {
		String sql = "SELECT COUNT(*) FROM DANGKY WHERE maHocVien = ? AND maKhoaHoc = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, maHocVien);
			pst.setString(2, maKhoaHoc);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // Nếu trả về > 0, tức là học viên đã đăng ký
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Hàm đăng ký khóa học cho học viên và hiển thị dialog
	public void dangKyKhoaHoc(String maHocVien, String maKhoaHoc) {
		// Kiểm tra xem học viên đã đăng ký khóa học chưa
		if (kiemTraDangKy(maHocVien, maKhoaHoc)) {
			// Hiển thị hộp thoại thông báo học viên đã đăng ký
			JOptionPane.showMessageDialog(null, "Học viên đã đăng ký khóa học này rồi!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return; // Dừng lại nếu học viên đã đăng ký
		}

		String sql = "INSERT INTO DANGKY (maHocVien, maKhoaHoc) VALUES (?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, maHocVien);
			pst.setString(2, maKhoaHoc);

			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				// Hiển thị hộp thoại thông báo thành công
				JOptionPane.showMessageDialog(null, "Đăng ký khóa học thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				// Hiển thị hộp thoại thông báo thất bại
				JOptionPane.showMessageDialog(null, "Đăng ký thất bại! Vui lòng thử lại.", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Hiển thị hộp thoại thông báo lỗi
			JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
}
