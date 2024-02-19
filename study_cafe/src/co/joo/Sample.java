package co.joo;

import java.util.List;

public class Sample {
	
	public static void main(String[] args) {
		
		TicketDAO tdao = new TicketDAO();
		Seat seat = new Seat();
		List<Integer> seats = tdao.useSeatsNo();
		
		
		
		seat.seatVu(seats);
		
	}

}
