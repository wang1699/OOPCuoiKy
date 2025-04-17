package model;

public class TaiKhoan {
    private String username;
    private String password;
    private String role;
    private String maTaiKhoan;
    
    public TaiKhoan() {}
    
    //public TaiKhoan(String id) {this.maTaiKhoan = maTaiKhoan;}

    public TaiKhoan(String username, String password, String role, String maTaiKhoan) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getMaTaiKhoan() { return maTaiKhoan; }
    public void setId(String maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }
    
}