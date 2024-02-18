package co.joo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sample {
	
	public static void main(String[] args) {
		
		TicketDAO tdao = new TicketDAO();
//		Map<Integer, String> seats = tdao.seatDList();
		
//		for (int i = 0;i < 4;i++) {
////			System.out.println("┌───────────┬───────────┬───────────┐");
//			for (int j = 1;j <= 3; j++) {
//				System.out.print("┌──────────┐");
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				int idx = 3 * i + j;
//				if(seats.containsKey(idx)) {
//					System.out.print("│● " + idx + "선택불가 │");			
//				} else {
//					System.out.print("│○ " + idx + "선택가능 │");
//				}
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				int idx = 3 * i + j;
//				if(seats.containsKey(idx)) {
//					System.out.print("│ (~" + seats.get(idx) + ") │");				
//				} else {
//					System.out.print("│         │");		
//				}
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				System.out.print("└──────────┘");
//			}
//			System.out.println();
////			System.out.println("└───────────┴───────────┴───────────┘");
//		}
		
		
		Seat seat = new Seat();
		List<Seat> full = seat.full();
		
		
//		TestDAO dao = new TestDAO();
//		
//		List<Seat> use = dao.useSeat();
//		

//		
//		for(Seat s : full) {
//			System.out.println(s.test());
//		}
		
		for(int i = 0; i < full.size(); i+=2) {
			full.get(i).setName("홍길동");
			full.get(i).setEnd("-07:00");
		}
		
		
		
		
		for(int i=0; i < seat.ROW; i++) {
			System.out.println("┌───────────────┬───────────────┬───────────────┐");
			System.out.print("| ");
			for(int j=0; j < seat.COL; j++) {
				if(full.get(seat.COL*i+j).getName() != null) {
					System.out.print(" ● ");
					System.out.print(full.get(seat.COL*i+j).getSeatId());
					System.out.print("		|");
				} else {
					System.out.print(" ○ ");
					System.out.print(full.get(seat.COL*i+j).getSeatId());
					System.out.print("		|");									
				}				
			}
			System.out.println();
			System.out.print("| ");
			for(int j=0; j < seat.COL; j++) {
				if(full.get(seat.COL*i+j).getName() != null) {
					System.out.print(" ");
					System.out.print(full.get(seat.COL*i+j).getName());
					System.out.print("		|");
				} else {
					System.out.print(" 	");
					System.out.print("	|");									
				}				
			}
			System.out.println();
			System.out.print("| ");
			for(int j=0; j < seat.COL; j++) {
				if(full.get(seat.COL*i+j).getName() != null) {
					System.out.print(" ");
					System.out.print(full.get(seat.COL*i+j).getEnd());
					System.out.print("	|");
				} else {
					System.out.print(" 	");
					System.out.print("	|");									
				}
			}
			System.out.println();
			System.out.println("└───────────────┴───────────────┴───────────────┘");
		}
		
		
		//// 원래있던 코드
//		Map<Integer, String> detail = tdao.seatDList();
//		
//		for (int i = 0;i < 3;i++) {
//			for (int j = 1;j <= 3; j++) {
//				System.out.print("┌──────────┐");
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				int idx = 3 * i + j;
//				if(detail.containsKey(idx)) {
//					System.out.print("│● " + idx + " 불가   │");			
//				} else {
//					System.out.print("│○ " + idx + " 선택   │");
//				}
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				int idx = 3 * i + j;
//				if(detail.containsKey(idx)) {
//					System.out.print("│ (~" + detail.get(idx) + ") │");				
//				} else {
//					System.out.print("│          │");		
//				}
//			}
//			System.out.println();
//			for (int j = 1;j <= 3; j++) {
//				System.out.print("└──────────┘");
//			}
//			System.out.println();
//		}
//		
//		detail = null;
		
		
		

		
	}

}
