package model;

public class HocVien extends User{
	private String maHocVien;
	public HocVien() {}
	public HocVien(String username, String password, String role, String maTaiKhoan,String maHocVien, String soDienThoai, String ten) {
		super(username, password, role, maTaiKhoan, soDienThoai, ten);
		this.maHocVien = maHocVien;
		// TODO Auto-generated constructor stub
	}
	public String getMaHocVien() {
		return maHocVien;
	}
	public void setMaHocVien(String maHocVien) {
		this.maHocVien = maHocVien;
	}
	

}
