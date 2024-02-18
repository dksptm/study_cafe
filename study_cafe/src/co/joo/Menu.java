package co.joo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu{
	
	private int menuNum;
	private String menuName;
	private int menuPrice;
	private int menuTime;
	
	@Override
	public String toString() {
		String str = "  %d) %s %d원";
		return String.format(str, menuNum, menuName, menuPrice);
	}
	
	
	
}

