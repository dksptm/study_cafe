package co.joo;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 필드 생성자
@NoArgsConstructor // 기본생성자
public class Member {

	// 고객정보 클래스.
	
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private Date joinDate;
	
	public Member(String memberId, String password, String name) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
	}

	public Member(String memberId, String password, String name , String phone) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.phone = phone;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String str = "이름: %s\n연락처:%s\n가입일:%s";
		return String.format(str, this.name, this.phone, sdf.format(this.joinDate));
	}
	
}
