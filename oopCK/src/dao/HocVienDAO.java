package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.HocVien;
import model.User;

public class HocVienDAO {
	public boolean taoHocVien(HocVien  hocVien) {
		if (DBCheckExist.isMaTonTai("HocVien", "maHocVien", hocVien.getMaHocVien())) {
            System.out.println("Mã tài khoản đã tồn tại.");
            JOptionPane.showMessageDialog(null,"Mã học viên đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String sql = "INSERT INTO HocVien (maHocVien, tenHocVien, sdtHocVien, maTaiKhoan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hocVien.getMaHocVien());
            stmt.setString(2, hocVien.getTen());
            stmt.setString(3, hocVien.getSoDienThoai());
            stmt.setString(4, hocVien.getMaTaiKhoan());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public List<HocVien> getAllHocVien() {
	    List<HocVien> danhSach = new ArrayList<>();
	    String sql = "SELECT * FROM HocVien";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            HocVien hv = new HocVien();
	            hv.setMaHocVien(rs.getString("maHocVien"));
	            hv.setTen(rs.getString("tenHocVien"));
	            hv.setSoDienThoai(rs.getString("sdtHocVien"));
	            danhSach.add(hv);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return danhSach;
	}
	
	public HocVien timKiemHocVien(String maHocVien) {
	    String sql = "SELECT maHocVien, tenHocVien, sdtHocVien FROM HocVien WHERE maHocVien = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, maHocVien);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            HocVien hv = new HocVien();
	            hv.setMaHocVien(rs.getString("maHocVien"));
	            hv.setTen(rs.getString("tenHocVien"));
	            hv.setSoDienThoai(rs.getString("sdtHocVien"));
	            return hv;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public boolean xoaHocVien(String maHocVien) {
	    String sql = "DELETE FROM HocVien WHERE maHocVien = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, maHocVien);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}
