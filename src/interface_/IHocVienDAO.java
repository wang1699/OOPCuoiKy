package interface_;

import java.util.List;
import model.HocVien;

public interface IHocVienDAO {
    boolean taoHocVien(HocVien hocVien);
    List<HocVien> getAllHocVien();
    HocVien timKiemHocVien(String maHocVien);
    boolean xoaHocVien(String maHocVien);
    boolean capNhat(String maHV, String tenMoi, String sdtMoi);
    HocVien getThongTinHocVien(String maTaiKhoan);
    String getMaHocVIenByTaiKhoan(String maTaiKhoan);
}