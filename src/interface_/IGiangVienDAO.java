package interface_;

import java.util.List;
import model.GiangVien;
import model.HocVien;
import model.KhoaHoc;

public interface IGiangVienDAO {
    boolean taoGiangVien(GiangVien giangVien);
    boolean xoaGiangVien(String maGiangVien); 

    GiangVien timKiemGiangVien(String maGiangVien);
    GiangVien getGiangVienByTaiKhoan(String maTaiKhoan);
    String getMaGiangVienByTaiKhoan(String maTaiKhoan);

    List<GiangVien> getAllGiangVien();
    List<KhoaHoc> getKhoaHocByMaGiangVien(String maGiangVien);
    List<HocVien> getHocVienByKhoaHocAndGiangVien(String maKhoaHoc, String maGiangVien);
}