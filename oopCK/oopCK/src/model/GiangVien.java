package model;

public class GiangVien extends User{
	private String maGiangVien;
	private double tienLuong;
	public GiangVien(String username, String password, String role, String maTaiKhoan, String soDienThoai, String ten, String maGiangVien, double tienLuong) {
		super(username, password, role, maTaiKhoan, soDienThoai, ten);
		this.tienLuong = tienLuong;
		this.maGiangVien= maGiangVien;
	}
	public double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	public String getMaGiangVien() {
		return maGiangVien;
	}
	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}
	
	
}
