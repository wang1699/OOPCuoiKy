package model;

public class KhoaHoc {
	private String maKhoaHoc;
	private String tenKhoaHoc;
	private String maGiangVien;
	private int soTinchi;
	private double hocPhi;
	public KhoaHoc() {}
	public KhoaHoc(String maKhoaHoc, String tenKhoaHoc, String maGiangVien, int soTinchi, double hocPhi) {
		super();
		this.maKhoaHoc = maKhoaHoc;
		this.tenKhoaHoc = tenKhoaHoc;
		this.maGiangVien = maGiangVien;
		this.soTinchi = soTinchi;
		this.hocPhi = hocPhi;
	}
	public String getMaKhoaHoc() {
		return maKhoaHoc;
	}
	public void setMaKhoaHoc(String maKhoaHoc) {
		this.maKhoaHoc = maKhoaHoc;
	}
	public String getTenKhoaHoc() {
		return tenKhoaHoc;
	}
	public void setTenKhoaHoc(String tenKhoaHoc) {
		this.tenKhoaHoc = tenKhoaHoc;
	}
	public String getMaGiangVien() {
		return maGiangVien;
	}
	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}
	public int getSoTinchi() {
		return soTinchi;
	}
	public void setSoTinchi(int soTinchi) {
		this.soTinchi = soTinchi;
	}
	public double getHocPhi() {
		return hocPhi;
	}
	public void setHocPhi(double hocPhi) {
		this.hocPhi = hocPhi;
	}
	
	
}
