package co.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public class Test {
	


	public static void main(String[] args) {

		
		TicketDAO tdao = new TicketDAO();
		Member loginMem = new Member();
		loginMem.setMemberId("rent7");
		
		System.out.println(testupdate(10021, "미사용"));
		
	}
	
	
	public static boolean testupdate(int tk, String str) {
		
		Connection conn;
		PreparedStatement psmt;
		ResultSet rs;
		String sql;
		
		conn = DAO.getConn();
		sql = "UPDATE ticketvu\r\n"
				+ "SET    use = CONCAT('사용중', ?)"
//				+ "       rent_no = (SELECT rent_no\r\n"
//				+ "                  FROM   rentvu\r\n"
//				+ "                  WHERE  ticket_no = ?)\r\n"
				+ "WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, str);
			psmt.setInt(2, tk);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
}
