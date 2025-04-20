package interface_;

import java.util.List;
import model.DiemSo;

public interface IDiemSoDAO {
	public void diemSo(String maHocVien, String maKhoaHoc, double diem);
	List<DiemSo> getDanhSachDiem(String maGiangVien, String maKhoaHoc);
	List<DiemSo> getDiemTheoTaiKhoan(String maTaiKhoan);
}