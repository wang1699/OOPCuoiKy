package interface_;
import java.util.List;

import model.KhoaHoc;
public interface IKhoaHocDAO {
	boolean taoKhoaHoc(KhoaHoc khoaHoc);
	List<KhoaHoc> getAllKhoaHoc();
	KhoaHoc timKiemKhoaHoc(String maKhoaHoc);
	boolean xoaKhoaHoc(String maKhoaHoc);
	boolean capNhat(String maKhoaHoc, String tenKhoaHoc, String maGiangVien, int soTinChi, double hocPhi);
	List<KhoaHoc> getKhoaHocByTaiKhoan(String maTaiKhoan);
	List<KhoaHoc> layKhoaHocChuaDangKy(String maHocVien);
	
}