package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DiemSo;

public class DiemSoDAO {
	

	public void diemSo(String maHocVien, String maKhoaHoc, double diem) {
	    String checkQuery = "SELECT COUNT(*) FROM DIEMSO WHERE maHocVien = ? AND maKhoaHoc = ?";
	    String insertQuery = "INSERT INTO DIEMSO(maHocVien, maKhoaHoc, diem) VALUES (?,  ?, ?)";
	    String updateQuery = "UPDATE DIEMSO SET diem = ? WHERE maHocVien = ? AND maKhoaHoc = ?";

	    try (Connection conn = DBConnection.getConnection()) {
	        // Kiểm tra xem học viên đã có điểm số ngày hôm đó chưa
	        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
	            checkStmt.setString(1, maHocVien);
	            checkStmt.setString(2, maKhoaHoc);
	            
	            ResultSet rs = checkStmt.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                // Nếu đã có điểm, thực hiện UPDATE
	                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
	                    updateStmt.setDouble(1, diem);
	                    updateStmt.setString(2, maHocVien);
	                    updateStmt.setString(3, maKhoaHoc);
	                    updateStmt.executeUpdate();
	                }
	            } else {
	                // Nếu chưa có điểm, thực hiện INSERT
	                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
	                    insertStmt.setString(1, maHocVien);
	                    insertStmt.setString(2, maKhoaHoc);
	                    insertStmt.setDouble(3, diem);
	                    insertStmt.executeUpdate();
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        // Xử lý ngoại lệ nếu có lỗi xảy ra khi truy vấn
	    }
	}

	public List<DiemSo> getDanhSachDiem(String maGiangVien, String maKhoaHoc) {
		List<DiemSo> ds = new ArrayList<>();

		String sql = "SELECT " + "    hv.maHocVien, " + "    hv.tenHocVien, " + "    ds.diem " + "FROM "
				+ "    KHOAHOC kh " + "JOIN " + "    DANGKY dk ON kh.maKhoaHoc = dk.maKhoaHoc " + "JOIN "
				+ "    HOCVIEN hv ON dk.maHocVien = hv.maHocVien " + "LEFT JOIN "
				+ "    DIEMSO ds ON ds.maHocVien = hv.maHocVien AND ds.maKhoaHoc = kh.maKhoaHoc " + "WHERE "
				+ "    kh.maKhoaHoc = ? AND kh.maGiangVien = ? " + "ORDER BY " + "    hv.maHocVien";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			// Set giá trị cho tham số trong câu truy vấn SQL
			pst.setString(1, maKhoaHoc );
			pst.setString(2, maGiangVien);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					DiemSo diem = new DiemSo();
					diem.setMaHocVien(rs.getString("maHocVien"));
					diem.setTenHocVien(rs.getString("tenHocVien"));
					diem.setDiem(rs.getDouble("diem")); // Sử dụng getDouble để lấy giá trị điểm

					ds.add(diem);
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return ds;
	}
	public List<DiemSo> getDiemTheoTaiKhoan(String maTaiKhoan) {
	    List<DiemSo> list = new ArrayList<>();

	    String sql = "SELECT ds.maKhoaHoc, kh.tenKhoaHoc, ds.diem " +
	                 "FROM DIEMSO ds " +
	                 "JOIN HOCVIEN hv ON ds.maHocVien = hv.maHocVien " +
	                 "JOIN KHOAHOC kh ON ds.maKhoaHoc = kh.maKhoaHoc " +
	                 "WHERE hv.maTaiKhoan = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {

	        pst.setString(1, maTaiKhoan);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            DiemSo diem = new DiemSo();
	            diem.setMaKhoaHoc(rs.getString("maKhoaHoc"));
	            diem.setDiem(rs.getFloat("diem"));

	            list.add(diem);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

}
