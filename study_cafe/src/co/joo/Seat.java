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
					System.out.print("		|");
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

	
	
	
	
	
	
	
}
