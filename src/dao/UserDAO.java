package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import interface_.IUserDAO;
import model.User;

public class UserDAO implements IUserDAO{
	public boolean createUser(User user) {
        String sql = "INSERT INTO TaiKhoan (ten, sdt,id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getTen());
            stmt.setString(2, user.getSoDienThoai()); // Lưu ý: nếu dùng mật khẩu hash thì hash trước
            stmt.setString(3, user.getRole());
            

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
