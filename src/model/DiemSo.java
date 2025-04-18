package model;

public class DiemSo {
	private String maHocVien;
	private String tenHocVien;
	private String maKhoaHoc;
	private double diem;
	public DiemSo() {}
	public DiemSo(String maHocVien,String tenHocVien, String maKhoaHoc, double diem) {
		this.maHocVien = maHocVien;
		this.tenHocVien = tenHocVien;
		this.maKhoaHoc = maKhoaHoc;
		this.diem = diem;
	}
	public String getMaHocVien() {
		return maHocVien;
	}
	public void setMaHocVien(String maHocVien) {
		this.maHocVien = maHocVien;
	}
	public String getMaKhoaHoc() {
		return maKhoaHoc;
	}
	public void setMaKhoaHoc(String maKhoaHoc) {
		this.maKhoaHoc = maKhoaHoc;
	}
	public double getDiem() {
		return diem;
	}
	public void setDiem(double diem) {
		this.diem = diem;
	}
	public String getTenHocVien() {
		return tenHocVien;
	}
	public void setTenHocVien(String tenHocVien) {
		this.tenHocVien = tenHocVien;
	}
	
	
}
