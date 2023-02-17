import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/ProductManagement";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException ex) {
            System.out.println("Failed to register MySQL JDBC driver: " + ex.getMessage());
        }

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}