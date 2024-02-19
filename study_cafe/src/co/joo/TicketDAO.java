package co.joo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//이용권구입 및 대여관련 기능 구현 클래스
public class TicketDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	// disconn
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
	
	// 1.이용권구입 - 메뉴조회.
	public List<Menu> menuList() {
		List<Menu> list = new ArrayList<>();
		conn = DAO.getConn();
		sql = "SELECT menu_num,"
				+ "   menu_name,"
				+ "   menu_price,"
				+ "   menu_time\r\n"
				+ "FROM   menu\r\n"
				+ "ORDER BY menu_num";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Menu menu = new Menu();
				menu.setMenuNum(rs.getInt(1));
				menu.setMenuName(rs.getString(2));
				menu.setMenuPrice(rs.getInt("menu_price"));
				menu.setMenuTime(rs.getInt("menu_time"));
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	// 1.이용권구입 - 메뉴번호, loginMem으로 ticket 테이블 데이터등록.
	public boolean purchase(int num, Member mem) {
		List<Menu> list = menuList(); // 메뉴리스트(time 정보 가져옴)
		int time = 0;
		for(Menu m : list) {
			if(m.getMenuNum() == num) {
				time = m.getMenuTime();
			}
		}
		conn = DAO.getConn();
		sql = "INSERT INTO ticketvu(time,"
				+ "               member_id)\r\n"
				+ "VALUES (?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, time);
			psmt.setString(2, mem.getMemberId());
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
	
	// 2. 상세 자리조회 - 자리번호, 회원이름, 종료시간.
	public List<Seat> useSeats() {
		List<Seat> useSeats = new ArrayList<>();
		conn = DAO.getConn();
		sql = "SELECT m.name,"
				+ "   TO_CHAR(r.end_date, 'HH24:MI') AS end,"
				+ "   r.seat_id\r\n"
				+ "FROM   member m JOIN ticketvu t\r\n"
				+ "                 ON (m.member_id = t.member_id)\r\n"
				+ "                JOIN rentvu r\r\n"
				+ "                 ON (t.ticket_no = r.ticket_no)\r\n"
				+ "ORDER BY r.seat_id";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Seat s = new Seat();
				s.setSeatId(rs.getInt(3));
				s.setEnd(rs.getString(2));
				s.setName(rs.getString(1));
				useSeats.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return useSeats;
	}
	
	// 간단한 자리조회 - rentvu 테이블에서 자리번호가 있는것 가져오기.
	public List<Integer> useSeatsNo() {
		List<Integer> seatsNo = new ArrayList<>();
		conn = DAO.getConn();
		sql = "SELECT seat_id\r\n"
				+ "FROM   rentvu\r\n";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				seatsNo.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return seatsNo;
	}
	
	
	// 3.자리대여 - 회원의 사용중인 이용권 가져오기.
	public Ticket myTkUse(Member mem) {
		Ticket tk = new Ticket();
		conn = DAO.getConn();
		sql = "SELECT ticket_no,"
				+ "   start_date,"
				+ "   end_date,"
				+ "   TRUNC((end_date - SYSDATE)*24*60) as rest,"
				+ "   seat_id\r\n"
				+ "FROM   rentvu\r\n"
				+ "WHERE  ticket_no = (SELECT ticket_no\r\n"
				+ "				       FROM   ticketvu\r\n"
				+ "				       WHERE  member_id = ?\r\n"
				+ "				       AND    use = '사용중')\r\n";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				tk.setTicketNo(rs.getInt("ticket_no"));
				tk.setStartDate(rs.getString("start_date"));
				tk.setEndDate(rs.getString("end_date"));
				tk.setRestTime(rs.getInt("rest"));
				tk.setSeatId(rs.getInt("seat_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return tk;
	}
	
	// 3.자리대여 - 회원의 미사용이용권들 가져오기.
	//            (모든정보X, 미사용인 이용권번호와 time).
	//            (myTkInfo()는 테이블조인이라 사용안함).
	public List<Ticket> myTkNoAry(Member mem){
		List<Ticket> tkAry = new ArrayList<>();
		conn = DAO.getConn();
		sql = "SELECT ticket_no,\r\n"
				+ "   time\r\n"
				+ "FROM   ticketvu\r\n"
				+ "WHERE  member_id = ?"
				+ "AND    use = '미사용'";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				Ticket tk = new Ticket();
				tk.setTicketNo(rs.getInt("ticket_no"));
				tk.setTime(rs.getInt("time"));
				tkAry.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return tkAry;
	}
		
	// 3.자리대여 - 티켓번호, 자리번호로 rent 테이블 데이터등록.
	public boolean rentInsert(int tk, int seat) {
		conn = DAO.getConn();
		sql = "INSERT INTO rentvu(end_date,"
				+ "               ticket_no,"
				+ "               seat_id)\r\n"
				+ "  SELECT sysdate + time/(60*24),"
				+ "         ticket_no,"
				+ "         ?\r\n"
				+ "  FROM   ticketvu\r\n"
				+ "  WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seat);
			psmt.setInt(2, tk);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	// 3.자리대여 - 티켓번호로 ticket 테이블 데이터 수정.
	public boolean tkUpdate(int tk) {
		conn = DAO.getConn();
		sql = "UPDATE ticketvu\r\n"
				+ "SET    use = '사용중',"
				+ "       rent_no = (SELECT rent_no\r\n"
				+ "                  FROM   rentvu\r\n"
				+ "                  WHERE  ticket_no = ?)\r\n"
				+ "WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tk);
			psmt.setInt(2, tk);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	// 4. 자리변경
	public boolean changeSeat(int tk, int seat) {
		conn = DAO.getConn();
		sql = "UPDATE rentvu\r\n"
				+ "SET    seat_id = ?\r\n"
				+ "WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seat);
			psmt.setInt(2, tk);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	
	// 5. 정보조회 - 티켓정보조회(+ JoinDAO)
	public List<Ticket> myTkInfo(Member mem) {
		List<Ticket> myTkAry = new ArrayList<>();
		conn = DAO.getConn();
		sql= "SELECT t.ticket_no AS no,"
				+ "  t.time AS time,"
				+ "	 TO_CHAR(r.start_date, 'YY-MM-DD HH24:MI') AS sta,"
				+ "  TO_CHAR(r.end_date, 'YY-MM-DD HH24:MI') AS end,"
				+ "  TRUNC((r.end_date - SYSDATE)*24*60) AS rest,"
				+ "  r.seat_id AS seat,"
				+ "  t.use AS tuse\r\n"
				+ "FROM   ticketvu t LEFT OUTER JOIN rentvu r \r\n"
				+ "				     ON (t.ticket_no = r.ticket_no)\r\n"
				+ "WHERE  t.member_id = ?\r\n"
				+ "ORDER BY t.use DESC";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getMemberId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				Ticket myTk = new Ticket();
				myTk.setTicketNo(rs.getInt("no"));
				myTk.setTime(rs.getInt("time"));
				myTk.setStartDate(rs.getString("sta"));
				myTk.setEndDate(rs.getString("end"));
				myTk.setRestTime(rs.getInt("rest"));
				myTk.setSeatId(rs.getInt("seat"));
				myTk.setUse(rs.getString("tuse"));
				myTkAry.add(myTk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return myTkAry;
	}
	
	// 7.퇴실 - 사용중인 Ticket 으로
	//         rent 테이블 자리번호 null 변경, 기존종료시간을 퇴실시간으로 변경.
	public boolean checkOutRent(Ticket t) {
		conn = DAO.getConn();
		sql = "UPDATE rentvu\r\n"
				+ "SET    seat_id = NULL,\r\n"
				+ "       end_date = sysdate\r\n"
				+ "WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, t.getTicketNo());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}
	// 7. 퇴실 - 사용중인 Ticket 으로
	//          ticket 테이블 use 를 사용완료로 변경.
	public boolean checkOutTk(Ticket t) {
		conn = DAO.getConn();
		sql = "UPDATE ticketvu\r\n"
				+ "SET    use = '사용완료'\r\n"
				+ "WHERE  ticket_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, t.getTicketNo());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return false;
	}	
	
	// 기타 : 종료시간이 지난자리 반납처리 (메인메소드 실행시)	
	//       ticket 테이블 수정.(티켓수정부터 먼저해야함!)
	public int resetTk() {
		int r = -1;
		conn = DAO.getConn();
		sql = "UPDATE ticketvu\r\n"
				+ "SET    use = '사용완료'\r\n"
				+ "WHERE  ticket_no IN (SELECT ticket_no\r\n"
				+ "                     FROM   rentvu\r\n"
				+ "                     WHERE  end_date < SYSDATE)";
		try {
			psmt = conn.prepareStatement(sql);
			r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return r;
	}
	// 기타 : 종료시간이 지난자리 반납처리.
	//       rent 테이블 수정.
	public int resetRent() {
		int r = -1;
		conn = DAO.getConn();
		sql = "UPDATE rentvu\r\n"
				+ "SET    seat_id = NULL\r\n"
				+ "WHERE  end_date < SYSDATE";
		try {
			psmt = conn.prepareStatement(sql);
			r = psmt.executeUpdate();
//			if (r > 0) {
//				return true;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return r;
	}

	
}
