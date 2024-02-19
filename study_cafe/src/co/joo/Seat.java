package co.joo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Seat {

	final int FIRST_SEAT = 1; // 첫번재 자리번호
	final int LAST_SEAT = 12; // 마지막 자리번호
	final int COL = 3; // 한줄에 자리몇개인지
	final int ROW = 4; // 몇줄인지

	// 자리정보 변수선언.
	private int seatId;
	private String end;
	private String name;

	// 자리아이디만 부여한 전체자리 리스트 만들기.
	public List<Seat> allSeats() {
		List<Seat> all = new ArrayList<>();
		for (int i = FIRST_SEAT; i <= LAST_SEAT; i++) {
			Seat s = new Seat();
			s.seatId = i;
			all.add(s);
		}
		return all;
	}

	// 전체자리 중 사용중인 자리 정보담기.
	public List<Seat> updateSeats(List<Seat> useSeats) {
		List<Seat> all = allSeats();
		for (int i = 0; i < useSeats.size(); i++) {
			for (int j = 0; j < all.size(); j++) {
				if (useSeats.get(i).getSeatId() == all.get(j).getSeatId()) {
					all.get(j).setEnd(useSeats.get(i).getEnd());
					all.get(j).setName(useSeats.get(i).getName());
				}
			}
		}
		return all;
	}

	// 보여지는 상세화면.
	public void seatDetailVu(List<Seat> all) {
		for (int i = 0; i < ROW; i++) {
			System.out.println("┌───────────────┬───────────────┬───────────────┐");
			System.out.print("| ");
			for (int j = 0; j < COL; j++) {
				if (all.get(COL * i + j).getName() != null) {
					System.out.print(" ● ");
					System.out.print(all.get(COL * i + j).getSeatId());
					System.out.print(" 사용중	|");
				} else {
					System.out.print(" ○ ");
					System.out.print(all.get(COL * i + j).getSeatId());
					System.out.print("		|");
				}
			}
			System.out.println();
			System.out.print("| ");
			for (int j = 0; j < COL; j++) {
				if (all.get(COL * i + j).getName() != null) {
					System.out.print(" ");
					System.out.print(all.get(COL * i + j).getName());
					System.out.print("		|");
				} else {
					System.out.print(" 	");
					System.out.print("	|");
				}
			}
			System.out.println();
			System.out.print("| ");
			for (int j = 0; j < COL; j++) {
				if (all.get(COL * i + j).getName() != null) {
					System.out.print(" ");
					System.out.print(all.get(COL * i + j).getEnd());
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

	// 간단하게 보여지는 화면 (자리번호만 있는 숫자리스트만 받음)
	public void seatVu(List<Integer> seatsNo) {
		int seat = 0;
		for (int i = 1; i <= ROW; i++) {
			System.out.println("┌───────────────┬───────────────┬───────────────┐");
			for (int j = 1; j <= COL; j++) {
				seat++;
				if (seatsNo.contains(seat)) {
					System.out.print("│● " + seat + " 선택불가	");
				} else {
					System.out.print("│○ " + seat + " 선택가능	");
				}
			}
			System.out.print("│");
			System.out.println("\n└───────────────┴───────────────┴───────────────┘");
		}
	}

}
