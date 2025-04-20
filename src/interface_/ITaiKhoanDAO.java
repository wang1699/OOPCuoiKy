package interface_;

import model.TaiKhoan;

public interface ITaiKhoanDAO {
	TaiKhoan kiemTraDangNhap(String username, String password);
	boolean taoTaiKhoan(TaiKhoan taiKhoan);
}
