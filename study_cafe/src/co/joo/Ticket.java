package co.joo;

import lombok.Data;

// 이용권 정보 클래스
@Data
public class Ticket {
	
	private int ticketNo;
	private int time; //분 단위
	
	private String startDate;
	private String endDate;
	
	private int restTime; //분 단위
	private int seatId;
	private String use;
	
	@Override
	public String toString() {
		String str = "사용중이용권: %d(%s)\n시작일시: %s\n종료일시: %s\n자리번호: %d번";
		return String.format(str, this.ticketNo, timeString(), this.startDate, this.endDate, this.seatId);
	}
	
	public String simpleString() {
		String str = "미사용이용권: %d(%s)\n";
		return String.format(str, this.ticketNo, timeString());
	}
	
	public String timeString() {
		int htime, mtime = 0;
		htime = time / 60;
		mtime = time % 60;
		if (htime == 0) {
			return mtime + "분";
		}
		if (mtime == 0) {
			return htime + "시간";
		}
		return htime + "시간" + mtime + "분";
	}
	
	public String restTimeString() {
		int htime, mtime = 0;
		htime = restTime / 60;
		mtime = restTime % 60;
		if (htime == 0) {
			return mtime + "분";
		}
		if (mtime == 0) {
			return htime + "시간";
		}
		return htime + "시간" + mtime + "분";
	}
	
	
}
