package model;


public class DiemDanh {
    private String maKhoaHoc;
    private String maHocVien;
    private String tenHocVien;
    private String soDienThoai;
    private String ngay;
    private String coMat;

    // Constructor không tham số
    public DiemDanh() {}

    // Constructor đầy đủ
    public DiemDanh(String maKhoaHoc, String maHocVien, String tenHocVien, String soDienThoai, String ngay, String coMat) {
        this.maKhoaHoc = maKhoaHoc;
        this.maHocVien = maHocVien;
        this.tenHocVien = tenHocVien;
        this.soDienThoai = soDienThoai;
        this.ngay = ngay;
        this.coMat = coMat;
    }

    // Getters và Setters
    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(String maHocVien) {
        this.maHocVien = maHocVien;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getCoMat() {
        return coMat;
    }

    public void setCoMat(String coMat) {
        this.coMat = coMat;
    }
}
