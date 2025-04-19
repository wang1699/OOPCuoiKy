package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import interface_.IDiemDanhDAO;
import model.DiemDanh;
import model.DiemSo;

public class DiemDanhDAO implements IDiemDanhDAO{

	public void diemDanh(String maHocVien, String maKhoaHoc, String ngay, String coMat) {
		String checkQuery = "SELECT COUNT(*) FROM DIEMDANH WHERE maHocVien = ? AND maKhoaHoc = ? AND ngay = ?";
		String insertQuery = "INSERT INTO DIEMDANH(maHocVien, maKhoaHoc, ngay, comat) VALUES (?, ?, ?, ?)";
		String updateQuery = "UPDATE DIEMDANH SET comat = ? WHERE maHocVien = ? AND maKhoaHoc = ? AND ngay = ?";

		try (Connection conn = DBConnection.getConnection()) {
			// Kiểm tra xem học viên đã điểm danh ngày hôm đó chưa
			try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
				checkStmt.setString(1, maHocVien);
				checkStmt.setString(2, maKhoaHoc);
				checkStmt.setString(3, ngay);

				ResultSet rs = checkStmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {
					// Nếu đã có, thực hiện UPDATE
					try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
						updateStmt.setString(1, coMat);
						updateStmt.setString(2, maHocVien);
						updateStmt.setString(3, maKhoaHoc);
						updateStmt.setString(4, ngay);
						updateStmt.executeUpdate();
					}
				} else {
					// Nếu chưa có, thực hiện INSERT
					try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
						insertStmt.setString(1, maHocVien);
						insertStmt.setString(2, maKhoaHoc);
						insertStmt.setString(3, ngay);
						insertStmt.setString(4, coMat);
						insertStmt.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			// Xử lý ngoại lệ nếu có lỗi xảy ra khi truy vấn
		}
	}

	public List<DiemDanh> getDanhSachDiemDanh(String maKhoaHoc, String maGiangVien) {
		List<DiemDanh> ds = new ArrayList<>();

		String sql = "SELECT " +
			    "    kh.maKhoaHoc, " +
			    "    hv.maHocVien, " +
			    "    hv.tenHocVien, " +
			    "    hv.sdtHocVien, " +
			    "    dd.ngay, " +
			    "    dd.comat " +
			    "FROM " +
			    "    KHOAHOC kh " +
			    "JOIN DANGKY dk ON kh.maKhoaHoc = dk.maKhoaHoc " +
			    "JOIN HOCVIEN hv ON dk.maHocVien = hv.maHocVien " +
			    "JOIN DIEMDANH dd ON dd.maHocVien = hv.maHocVien AND dd.maKhoaHoc = kh.maKhoaHoc " +
			    "WHERE kh.maKhoaHoc = ? AND kh.maGiangVien = ? " +
			    "ORDER BY dd.ngay, hv.maHocVien";


		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			// Set giá trị cho tham số trong câu truy vấn SQL
			pst.setString(2, maGiangVien);
			pst.setString(1, maKhoaHoc);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					DiemDanh dd = new DiemDanh();
					dd.setMaKhoaHoc(rs.getString("maKhoaHoc"));
					dd.setMaHocVien(rs.getString("maHocVien"));
					dd.setTenHocVien(rs.getString("tenHocVien"));
					dd.setSoDienThoai(rs.getString("sdthocvien"));
					dd.setNgay(rs.getString("ngay"));
					dd.setCoMat(rs.getString("coMat"));

					ds.add(dd);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return ds;
	}
	public List<DiemDanh> diemDanhSinhVien(String maKhoaHoc, String maGiangVien) {
		List<DiemDanh> ds = new ArrayList<>();

		String sql = "SELECT "
		        + "    kh.maKhoaHoc, "
		        + "    hv.maHocVien, "
		        + "    hv.tenHocVien, "
		        + "    hv.sdtHocVien, "
		        + "    NVL(dd.ngay, '') AS ngay, "
		        + "    NVL(dd.comat, '') AS comat "
		        + "FROM "
		        + "    KHOAHOC kh "
		        + "JOIN "
		        + "    DANGKY dk ON kh.maKhoaHoc = dk.maKhoaHoc "
		        + "JOIN "
		        + "    HOCVIEN hv ON dk.maHocVien = hv.maHocVien "
		        + "LEFT JOIN "
		        + "    DIEMDANH dd ON dd.maHocVien = hv.maHocVien "
		        + "    AND dd.maKhoaHoc = kh.maKhoaHoc "
		        + "    AND dd.ngay = TO_CHAR(SYSDATE, 'DD/MM/YYYY') "
		        + "WHERE "
		        + "    kh.maKhoaHoc = ? "
		        + "    AND kh.maGiangVien = ? "
		        + "ORDER BY "
		        + "    dd.ngay ASC, hv.maHocVien";



		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			// Set giá trị cho tham số trong câu truy vấn SQL
			pst.setString(2, maGiangVien);
			pst.setString(1, maKhoaHoc);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					DiemDanh dd = new DiemDanh();
					dd.setMaKhoaHoc(rs.getString("maKhoaHoc"));
					dd.setMaHocVien(rs.getString("maHocVien"));
					dd.setTenHocVien(rs.getString("tenHocVien"));
					dd.setSoDienThoai(rs.getString("sdthocvien"));
					dd.setNgay(rs.getString("ngay"));
					dd.setCoMat(rs.getString("coMat"));

					ds.add(dd);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return ds;
	}

}
