package interface_;

import java.util.List;
import model.DiemDanh;

public interface IDiemDanhDAO {
	public void diemDanh(String maHocVien, String maKhoaHoc, String ngay, String coMat);
	List<DiemDanh> getDanhSachDiemDanh(String maKhoaHoc, String maGiangVien);
	List<DiemDanh> diemDanhSinhVien(String maKhoaHoc, String maGiangVien);
}