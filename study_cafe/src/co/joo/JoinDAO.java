package co.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinDAO {
	
	// 고객가입, 정보수정, 정보조회를 위한 클래스.
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	void disconn() {
		try {
			if(conn != null) {
				conn.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 1.고객가입-아이디 중복체크.
	public boolean checkId(String id) {
		int chk = -1;
		conn = DAO.getConn();
		sql = "SELECT COUNT(member_id) AS chk\r\n"
				+ "FROM   member\r\n"
				+ "WHERE  member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				chk = rs.getInt("chk");
			}
			if(chk == 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	// 1.고객가입- member 테이블 등록.
	public boolean insertMem(Member mem) {
		conn = DAO.getConn();
		sql = "INSERT INTO member (member_id,\r\n"
				+ "                password,\r\n"
				+ "                name,\r\n"
				+ "                phone)\r\n"
				+ "VALUES (?,"
				+ "        ?,"
				+ "        ?,"
				+ "        ?"
				+ ")";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getMemberId());
			psmt.setString(2, mem.getPassword());
			psmt.setString(3, mem.getName());
			psmt.setString(4, mem.getPhone());
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	// 2. 로그인 
	// 5. 정보조회시 재로그인, 기본정보조회(+ TicketDAO)
	// 6. 정보수정시 재로그인
	public Member login(String id, String pw) {
		Member loginMem = new Member();
		conn = DAO.getConn();
		sql = "SELECT member_id,"
				+ "   name,"
				+ "   phone,"
				+ "   join_date\r\n"
				+ "FROM   member\r\n"
				+ "WHERE  member_id = ?\r\n"
				+ "AND    password = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			while(rs.next()) {
				loginMem.setMemberId(rs.getString("member_id"));
				loginMem.setName(rs.getString("name"));
				loginMem.setPhone(rs.getString("phone"));
				loginMem.setJoinDate(rs.getDate("join_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return loginMem;
	}
	
	// 6.정보수정 - member 테이블 수정.
	public boolean myInfoUpdate(Member mem, String pw, String phone) {
		conn = DAO.getConn();
		sql = "UPDATE member\r\n"
				+ "SET    password = NVL(?, password),"
				+ "       phone = NVL(?, phone)\r\n"
				+ "WHERE  member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, phone);
			psmt.setString(3, mem.getMemberId());
			int r = psmt.executeUpdate();
			if(r == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
}
