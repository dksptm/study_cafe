package co.joo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	public static Connection conn;
	
	public static Connection getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "joo", "j1230");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}

}
