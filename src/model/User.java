package model;

public class User extends TaiKhoan {
	private String soDienThoai;
    private String ten;
    public User() {}
	public User(String username, String password, String role, String maTaiKhoan, String soDienThoai, String ten) {
		super(username, password, role, maTaiKhoan);
		this.soDienThoai = soDienThoai;
		this.ten = ten;
		// TODO Auto-generated constructor stub
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	

}
