package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.HocVien;
import model.KhoaHoc;

public class KhoaHocDAO {
	public boolean taoKhoaHoc(KhoaHoc khoaHoc) {
		if (DBCheckExist.isMaTonTai("KhoaHoc", "makhoaHoc", khoaHoc.getMaKhoaHoc())) {
			System.out.println("Mã khóa học đã tồn tại.");
			JOptionPane.showMessageDialog(null, "Mã khóa học đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String sql = "INSERT INTO KhoaHoc (maKhoaHoc,tenKhoaHoc, maGiangVien, soTinCHi, hocPhi ) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, khoaHoc.getMaKhoaHoc().toUpperCase());
			stmt.setString(2, khoaHoc.getTenKhoaHoc());
			stmt.setString(3, khoaHoc.getMaGiangVien().toUpperCase());
			stmt.setInt(4, khoaHoc.getSoTinchi());
			stmt.setDouble(5, khoaHoc.getHocPhi());

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<KhoaHoc> getAllKhoaHoc() {
		List<KhoaHoc> danhSach = new ArrayList<>();
		String sql = "SELECT * FROM KhoaHoc";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

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

	public KhoaHoc timKiemKhoaHoc(String maKhoaHoc) {
		String sql = "SELECT maKhoaHoc, tenKhoaHoc, maGiangVien, soTinCHi, hocPhi FROM KhoaHoc WHERE maKhoaHoc = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maKhoaHoc);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				KhoaHoc kh = new KhoaHoc();
				kh.setMaKhoaHoc(rs.getString("maKhoaHoc"));
				kh.setTenKhoaHoc(rs.getString("tenKhoaHoc"));
				kh.setMaGiangVien(rs.getString("maGiangVien"));
				kh.setSoTinchi(rs.getInt("soTinChi"));
				kh.setHocPhi(rs.getDouble("hocPhi"));
				return kh;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean xoaKhoaHoc(String maKhoaHoc) {
		String sql = "DELETE FROM KhoaHoc WHERE maKhoaHoc = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, maKhoaHoc);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean capNhat(String maKhoaHoc, String tenKhoaHoc, String maGiangVien, int soTinChi, double hocPhi) {
		String sql = "UPDATE KHOAHOC SET tenKhoaHoc = ?, maGiangVien = ?, soTinChi = ?, hocPhi = ? WHERE maKhoaHoc = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, tenKhoaHoc);
			stmt.setString(2, maGiangVien);
			stmt.setInt(3, soTinChi);
			stmt.setDouble(4, hocPhi);
			stmt.setString(5, maKhoaHoc);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	public List<KhoaHoc> getKhoaHocByTaiKhoan(String maTaiKhoan) {
	    List<KhoaHoc> list = new ArrayList<>();

	    String sql = "SELECT kh.* FROM KhoaHoc kh "
	               + "JOIN GiangVien gv ON kh.maGiangVien = gv.maGiangVien "
	               + "WHERE gv.maTaiKhoan = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {

	        pst.setString(1, maTaiKhoan);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            KhoaHoc kh = new KhoaHoc();
	            kh.setMaKhoaHoc(rs.getString("maKhoaHoc"));
	            kh.setTenKhoaHoc(rs.getString("tenKhoaHoc"));
	            kh.setMaGiangVien(rs.getString("maGiangVien"));
	            kh.setSoTinchi(rs.getInt("soTinChi"));
				kh.setHocPhi(rs.getDouble("hocPhi"));
	            list.add(kh);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

}
