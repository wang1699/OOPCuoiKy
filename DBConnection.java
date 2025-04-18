package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;  //nhớ đổi tên. cả bên UserDAO

    // Thông tin kết nối
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";  // chỉnh lại theo Oracle của bạn
    private static final String USER = "hr";                       // đổi username của bạn
    private static final String PASSWORD = "hr";                   // đổi password của bạn

    // Lấy kết nối, đảm bảo luôn là kết nối mở
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Kết nối Oracle thành công!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver Oracle JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Không thể kết nối tới Oracle.");
            e.printStackTrace();
        }
        return conn;
    }

    // Đóng kết nối (gọi ở nơi nào cần đóng lại)
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối Oracle.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
