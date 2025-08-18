package tasks;

import java.sql.*;

public class MariaDBUtils {
    private static final String DB_URL = "jdbc:mariadb://192.168.25.127:3306/finhub_data";
    private static final String USER = "hanhtt";
    private static final String PASS = "Abc@4321";

    // Mở connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // SELECT
    public static ResultSet executeQuery(String query) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    // INSERT/UPDATE/DELETE
    public static int executeUpdate(String query) throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        }
    }
}
