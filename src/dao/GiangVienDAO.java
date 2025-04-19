package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interface_.IGiangVienDAO;
import model.GiangVien;
import model.HocVien;
import model.KhoaHoc;
import model.User;

public class GiangVienDAO implements IGiangVienDAO{
	public boolean taoGiangVien(GiangVien giangVien) {
		if (DBCheckExist.isMaTonTai("GiangVien", "maGiangVien", giangVien.getMaGiangVien())) {

			JOptionPane.showMessageDialog(null, "Mã giảng viên đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String sql = "INSERT INTO GiangVien (maGiangVien, tenGiangVien, sdtGiangVien, luong, maTaiKhoan) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

	public List<GiangVien> getAllGiangVien() {
		List<GiangVien> danhSach = new ArrayList<>();
		String sql = "SELECT * FROM GiangVien";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				GiangVien gv = new GiangVien();
				gv.setMaGiangVien(rs.getString("maGiangVien"));
				gv.setTen(rs.getString("tenGiangVien"));
				gv.setSoDienThoai(rs.getString("sdtGiangVien"));
				gv.setTienLuong(rs.getDouble("luong"));
				danhSach.add(gv);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return danhSach;
	}

	public List<KhoaHoc> getKhoaHocByMaGiangVien(String maGiangVien) {
		List<KhoaHoc> danhSach = new ArrayList<>();
		String sql = "SELECT * FROM KhoaHoc WHERE maGiangVien = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {
			pst.setString(1, maGiangVien);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				KhoaHoc kh = new KhoaHoc();
				kh.setMaKhoaHoc(rs.getString("maKhoaHoc"));
				kh.setTenKhoaHoc(rs.getString("tenKhoaHoc"));
				kh.setMaGiangVien(rs.getString("maGiangVien"));
				kh.setSoTinchi(rs.getInt("soTinChi"));
				kh.setHocPhi(rs.getDouble("hocPhi"));
				danhSach.add(kh);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return danhSach;
	}

	public List<HocVien> getHocVienByKhoaHocAndGiangVien(String maKhoaHoc, String maGiangVien) {
		List<HocVien> danhSach = new ArrayList<>();
		String sql = """
				    SELECT hv.maHocVien, hv.tenHocVien, hv.sdtHocVien
				    FROM HOCVIEN hv
				    JOIN DANGKY dk ON hv.maHocVien = dk.maHocVien
				    JOIN KHOAHOC kh ON dk.maKhoaHoc = kh.maKhoaHoc
				    WHERE kh.maKhoaHoc = ? AND kh.maGiangVien = ?
				""";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, maKhoaHoc);
			pst.setString(2, maGiangVien);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				HocVien hv = new HocVien();
				hv.setMaHocVien(rs.getString("maHocVien"));
				hv.setTen(rs.getString("TenHocVien"));
				hv.setSoDienThoai(rs.getString("SDTHocVien"));
				// nếu cần thêm maTaiKhoan thì lấy luôn

				danhSach.add(hv);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return danhSach;
	}

	public boolean xoaGiangVien(String maGiangVien) {
		String sql = "DELETE FROM GiangVien WHERE maGiangVien = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maGiangVien);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public GiangVien timKiemGiangVien(String maGiangVien) {
		String sql = "SELECT * FROM GIANGVIEN WHERE maGiangVien = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, maGiangVien);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				GiangVien gv = new GiangVien();
				gv.setMaGiangVien(rs.getString("maGiangVien"));
				gv.setTen(rs.getString("tenGiangVien"));
				gv.setSoDienThoai(rs.getString("sdtGiangVien"));
				gv.setTienLuong(rs.getDouble("luong"));
				return gv;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public String getMaGiangVienByTaiKhoan(String maTaiKhoan) {
		String sql = "SELECT maGiangVien FROM GiangVien WHERE maTaiKhoan = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maTaiKhoan);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getString("maGiangVien");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GiangVien getGiangVienByTaiKhoan(String maTaiKhoan) {
		String sql = "SELECT * FROM GiangVien WHERE maTaiKhoan = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maTaiKhoan);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				GiangVien gv = new GiangVien();
				gv.setMaGiangVien(rs.getString("maGiangVien"));
				gv.setTen(rs.getString("tenGiangVien"));
				gv.setSoDienThoai(rs.getString("sdtGiangVien"));
				gv.setTienLuong(rs.getDouble("luong"));
				return gv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
