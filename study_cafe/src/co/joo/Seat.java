package co.joo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Seat {
	
	final int FIRST_SEAT = 1;
	final int LAST_SEAT = 12;	
	final int COL = 3;
	final int ROW = 4;
	
	private int seatId;
	private String end;
	private String name;
	
	// 자리아이디만 부여한 리스트 만들기.
	public List<Seat> full(){
		List<Seat> total = new ArrayList<>();
		for (int i = FIRST_SEAT ; i <= LAST_SEAT; i++) {
			Seat s = new Seat();
			s.seatId = i;
			total.add(s);
		}
		return total;
	}
	
	// 전체자리 중 사용중인 자리 정보담기.
	public List<Seat> useSeat(List<Seat> useSeats){
		List<Seat> full = full();
		for(int i=0; i<useSeats.size(); i++) {
			for(int j=0; j<full.size();j++) {
				if(useSeats.get(i).getSeatId() == full.get(j).getSeatId()) {
					full.get(j).setEnd(useSeats.get(i).getEnd());
					full.get(j).setName(useSeats.get(i).getName());
				}
			}
		}
		return full;
	}
	
	// 보여지는 화면.
	public void seatDetailVu(List<Seat> full) {
		
		for(int i=0; i < ROW; i++) {
			System.out.println("┌───────────────┬───────────────┬───────────────┐");
			System.out.print("| ");
			for(int j=0; j < COL; j++) {
				if(full.get(COL*i+j).getName() != null) {
					System.out.print(" ● ");
					System.out.print(full.get(COL*i+j).getSeatId());
					System.out.print(" 사용중	|");
				} else {
					System.out.print(" ○ ");
					System.out.print(full.get(COL*i+j).getSeatId());
					System.out.print("		|");									
				}				
			}
			System.out.println();
			System.out.print("| ");
			for(int j=0; j < COL; j++) {
				if(full.get(COL*i+j).getName() != null) {
					System.out.print(" ");
					System.out.print(full.get(COL*i+j).getName());
					System.out.print("		|");
				} else {
					System.out.print(" 	");
					System.out.print("	|");									
				}				
			}
			System.out.println();
			System.out.print("| ");
			for(int j=0; j < COL; j++) {
				if(full.get(COL*i+j).getName() != null) {
					System.out.print(" ");
					System.out.print(full.get(COL*i+j).getEnd());
					System.out.print("  	|");
				} else {
					System.out.print(" 	");
					System.out.print("	|");									
				}
			}
			System.out.println();
			System.out.println("└───────────────┴───────────────┴───────────────┘");
		}
	}
	
	// 간단하게 보여지는 화면 (자리번호만 있는 숫자리스트)
	public void seatVu(List<Integer> seats) {
		int seat = 0;
		for (int i = 1; i <= ROW; i++) {
			System.out.println("┌────────────┬────────────┬────────────┐");
			for (int j = 1; j <= COL; j++) {
				seat++;
				if (seats.contains(seat)) {
					System.out.print("│● " + seat + " 선택불가  ");
				} else {
					System.out.print("│○ " + seat + " 선택가능  ");
				}
			}
			System.out.print("│");
			System.out.println("\n└────────────┴────────────┴────────────┘");
		}
	}

	
	
	
	
	
	
	
}
