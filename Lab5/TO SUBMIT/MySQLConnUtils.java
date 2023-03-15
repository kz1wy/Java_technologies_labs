package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQLConnUtils {

	public static MySQLConnUtils getInstance(){
		return new MySQLConnUtils();
	}

	public Connection getConnection() {
		Connection con=null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url="jdbc:mySQL://localhost:3306/product_management";
			String username="root";
			String password="";
			
			con=DriverManager.getConnection(url,username,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
	public void closeConnection(Connection con) {
		try {
			if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
		}
	}
}
