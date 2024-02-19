package co.joo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//사용자의 입출력 처리. 메인메소드.
public class CafeApp {
	
	// 필드선언
	// 인스턴스 생성 (기능구현을 위함)
	static Scanner scn = new Scanner(System.in);
	static JoinDAO jdao = new JoinDAO();
	static TicketDAO tdao = new TicketDAO();
	static boolean run = false;
	// 인스턴스 생성 (데이터를 담기위한 변수)
	static Member mem = null;
	static Member loginMem = null;

	// 메인메소드
	public static void main(String[] args) {
		
		/**  시간지난 자리 리셋  **/	
		System.out.printf("%d건의 대여정보 리셋.\n", tdao.resetTk());
		System.out.printf("%d건의 이용권정보 리셋.\n", tdao.resetRent());
		
		while(true) { // 로그인
			try {
				memberLogin();
				break;
			} catch (InputMismatchException e) {
				System.out.println("메뉴는 숫자만 입력가능합니다.\n");
				scn.nextLine(); // 꼭 해줘야 함! 아니면 무한반복!
			}	
		}
		
		while(true) { // 스터디
			try {
				studyMethod();
				break;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.\n메인메뉴로 이동합니다.\n");
				scn.nextLine(); // 꼭 해줘야 함! 아니면 무한반복!
			}	
		}

		System.out.println("end of main prog.");
	}// end of main.
		
	// 로그인 메소드.
	public static void memberLogin() throws InputMismatchException {
		int menu, choice = 0;
		/**  회원가입 또는 로그인  **/		
		System.out.println("1.회원가입 2.로그인");
		System.out.print("▷ 선택>> ");
		menu = scn.nextInt();scn.nextLine();
		
		if (menu == 1) { // 회원가입위한 정보입력.
			// 아이디 중복체크
			boolean go = false;
			String id = "";
			while(true) {
				System.out.print("▷ 아이디>> ");
				id = scn.nextLine();	
				if(jdao.checkId(id)) {
					System.out.println("▷ 사용가능한 아이디입니다. ◁");
					go = true;
					break;
				}
				id = "";
				System.out.println("▶ 이미 사용중인 아이디입니다. ◀\n");
				System.out.print("▶ 1.재입력 2.로그인 3.나가기 >> ");
				choice = scn.nextInt(); scn.nextLine();
				if (choice == 2) {
					System.out.println("▶ 로그인 화면으로 이동합니다. ◀\n");
					menu = 2;
					break;
				} else if(choice != 1) {
					System.out.println("▶ 고객가입 중단 ◀\n");
					break;
				}
			}
			//중복체크 완료된 경우(go=true) 진행하기.
			String pw, name, phone = "";
			if(go) {
				System.out.print("▷ 비밀번호>> ");
				pw = scn.nextLine();
				System.out.print("▷ 이름>> ");
				name = scn.nextLine();
				System.out.print("▷ 연락처>> ");
				phone = scn.nextLine();
				mem = new Member(id, pw, name, phone);
				//정보입력받아 Member 만들면 회원가입 완료.
				if(jdao.insertMem(mem)) {
					System.out.println("▷ " + mem.getName() +"님 가입완료 ◁");
					System.out.println("▷ 로그인 메뉴로 이동합니다. ◁");
					menu = 2;
				} else {
					System.out.println("▶ 등록 오류 발생 ◀");
				}
			} else if (menu != 2) {
				System.out.println("▶ 스터디카페는 회원만 이용가능합니다. ◀\n▶ 안녕히가세요. ◀");				
			}
			choice = 0;
		} // 1번 메뉴.
		
		// if~else if 안한 이유 : 1번메뉴에서 회원가입 성공한 경우 2번으로 이동하기 위함.
		if (menu == 2) { // chkId, chkPw 입력받아 로그인시도.		
			String chkId, chkPw = "";
			while(true) {
				if (mem != null) { // 1번에서 회원가입하고 온 경우.
					chkId = mem.getMemberId();
					System.out.println("▷ 아이디>> " + chkId);
				} else {
					System.out.print("▷ 아이디>> ");
					chkId = scn.nextLine();					
				}
				System.out.print("▷ 비밀번호>> ");
				chkPw = scn.nextLine();
				loginMem = jdao.login(chkId, chkPw);
				// 로그인이 성공해서(longinMem의 id가 null아니면) 
				// 메인메뉴(while) 진행(run=true).
				if(loginMem.getMemberId() != null) {
					System.out.println("\n▷ "+loginMem.getName()+"님 환영합니다. ◁\n");
					run = true;
					break;
				} else {
					chkId = ""; chkPw = ""; loginMem = null;
					System.out.println("▶ 로그인 실패 ◀");
					System.out.print("\n▶ 1.재입력 2.나가기 >> ");
					choice = scn.nextInt(); scn.nextLine();
				}
				if(choice != 1) {
					System.out.println("\n▶ 로그인시도를 종료합니다. ◀\n▶ 안녕히가세요. ◀");
					break;
				}
			}
		} // 2번메뉴.
		
		System.out.println("end of login prog.");
	}// 로그인 메소드.

	// 스터디 메소드.
	public static void studyMethod() throws InputMismatchException {
		int menu, choice = 0;
		/**  스터디카페 이용하기  **/
		while(run) {
			// 자주사용하는 변수선언 및 초기화.
			Seat seat = new Seat();
			List<Integer> seatsNo;
			Ticket myTk;
			menu= 0; choice = 0; mem = null;
			
			System.out.println("1.이용권구입 2.자리조회 3.자리대여 4.자리변경 5.정보조회 6.정보수정 7.퇴실 9.종료");
			System.out.print("▷ 선택>> ");
			menu = scn.nextInt(); scn.nextLine();
			
			switch(menu) {
			
			case 1: // 1.이용권 구입.
				
				// 이용권 메뉴조회.
				List<Menu> list = tdao.menuList();
				System.out.println("\n  ─────메 뉴─────");
				for (Menu m : list) {
					System.out.println(m);
				}
				System.out.println("  ──────────────");
				
				// 이용권 선택하면 이용권 등록(loginMem).
				System.out.print("\n▷ 이용권 선택>> ");
				choice = scn.nextInt(); scn.nextLine();
				if (tdao.purchase(choice, loginMem)) {
					System.out.println("▷ 이용권 구입완료. ◁\n");
				} else {
					System.out.println("▶등록 오류 발생◀");
				}
				
				break;
				
			case 2: // 2.자리조회(상세조회).
				
				System.out.println("▷ 자리를 조회합니다. ◁");
				
				List<Seat> useSeats = tdao.useSeats();
				List<Seat> allSeats = seat.updateSeats(useSeats);
				seat.seatDetailVu(allSeats);
				
				useSeats = null;  allSeats = null;
				break;
				
			case 3: // 3.자리대여.
				
				// 빈자리여부 확인.
				seatsNo = tdao.useSeatsNo();
				if (seatsNo.size() > 8) { // 빈자리 없으면 switch 나가기.
					System.out.println("▶ 모든 자리가 대여중입니다. ◀");
					System.out.println("▶ 메인메뉴로 이동합니다. ◀\n");
					break; 
				}
				
				// loginMem의 현재상태(이미 사용중인지) 확인.
				myTk = tdao.myTkUse(loginMem);
				int mySeat = myTk.getSeatId();
				if(mySeat != 0) { // 이미 자리를 사용중이면 switch 나가기.
					System.out.println("▶ 현재 자리를 사용중입니다 → " + mySeat + "번 ◀");
					System.out.println("▶ 자리를 변경하려면 자리변경 메뉴를 이용해주세요. ◀\n");
					myTk = null; mySeat = 0;
					break;
				}
				
				// loginMem의 이용권 확인.
				List<Ticket> myTkAry = tdao.myTkNoAry(loginMem);
				int tkCnt = myTkAry.size();					
				if (tkCnt < 1) { // 이용권 없으면 switch 나가기.
					System.out.println("▶ 사용가능한 이용권이 없습니다. ◀");
					System.out.println("▶ 이용권부터 구입해주세요. ◀\n");
					myTkAry = null; tkCnt = 0;
					break;
				}	
				
				// loginMem의 이용권 확인 - 이용권 선택.
				int choiceTk = -1;
				// 이용권 선택 - 목록출력
				System.out.println("───" + loginMem.getName()+ "님의 이용권───");
				for (int i = 0; i < tkCnt; i++) {
					System.out.print((i+1)+". ");
					System.out.print(myTkAry.get(i).timeString());
					System.out.print("(" + myTkAry.get(i).getTicketNo()+")");
					System.out.println();
				}
				System.out.println("───────────────────");
				// 이용권 선택 - 여러장일때 하나선택
				while (tkCnt > 1) {
					System.out.print("▷ 어떤 이용권을 사용할까요?>> ");
					choice = scn.nextInt(); scn.nextLine();
					if ((choice-1) >= 0 && (choice-1) < tkCnt) {
						choiceTk = myTkAry.get(choice-1).getTicketNo();
						break;
					} else {
						System.out.println("▶ 잘못 입력하셨습니다. ◀");
					}
				}
				// 이용권 선택 - 한개일때
				if (tkCnt == 1) {
					choiceTk = myTkAry.get(0).getTicketNo();
				}
				
				// 자리선택.
				int choiceSeat = 0;
				System.out.println("\n▷ 대여가능한 자리를 확인하세요. ◁");
				seat.seatVu(seatsNo);
				while(true) {
					System.out.print("\n▷ 원하는 자리를 입력하세요.>> ");
					choiceSeat = scn.nextInt(); scn.nextLine();
					if (choiceSeat < seat.FIRST_SEAT || choiceSeat > seat.LAST_SEAT) {
						System.out.println("▶ 잘못된 자리번호 입니다. ◀");
					} else if (seatsNo.contains(choiceSeat)) {
						System.out.println("▶ 현재 사용중인 자리입니다. ◀");
					} else {
						System.out.println("▷ " + choiceSeat + "번 자리 대여를 시작합니다.◁");
						break;
					}
				}
				
				// 대여진행.(rent 등록과 ticket 업데이트)
				if (tdao.rentInsert(choiceTk, choiceSeat)) {
					if(tdao.tkUpdate(choiceTk)) {
						System.out.println("▷ 지금부터 시간이 차감됩니다... ◁");
						myTk = tdao.myTkUse(loginMem); // 다시 티켓정보 가져오기.
						System.out.printf("▷ %s님 / ", loginMem.getName());
						System.out.printf("자리번호: %d번 /", myTk.getSeatId());
						System.out.printf("남은시간: %s ◁\n\n", myTk.restTimeString());
					} else {
						System.out.println("▶ 티켓수정 오류 발생 ◀");
					}
				} else {
					System.out.println("▶ 대여등록 오류 발생 ◀");
				}
				
				// 변수들 초기화
				myTkAry = null;
				choiceTk = 0; choiceSeat = 0; 
				tkCnt = 0; mySeat = 0;
				
				break;
				
			case 4: // 4.자리변경.
				
				// 빈자리여부 확인.
				seatsNo = tdao.useSeatsNo();
				if (seatsNo.size() > 8) { // 빈자리 없으면 switch 나가기.
					System.out.println("▶ 모든 자리가 대여중입니다. ◀");
					System.out.println("▶ 메인메뉴로 이동합니다. ◀\n");
					break; 
				}
				
				// loginMem의 현재상태(대여,티켓) 확인.
				myTk = tdao.myTkUse(loginMem);
				mySeat = myTk.getSeatId();
				
				// 대여시작 안했으면 switch 나가기.
				if(mySeat == 0) { 
					System.out.println("▶ 자리대여를 시작하지않았습니다. ◀");
					System.out.println("▶ 자리대여 메뉴를 이용해주세요. ◀\n");
					mySeat = 0;
					break;
				} 
				
				// 현재 사용중인 정보 출력.
				System.out.printf("▷ %s님 / ", loginMem.getName());
				System.out.printf("자리번호: %d번 / ", mySeat);
				System.out.printf("남은시간: %s ◁\n", myTk.restTimeString());
				
				// 10분이하 남은경우 변경불가. switch 나가기.
				if (myTk.getRestTime() < 10) {
					System.out.println("▶ 잔여시간이 10분 이하인 경우 자리변경이 되지않습니다. ◀\n");
					mySeat = 0;
					break;
				}
				
				// 자리선택.
				System.out.println("\n▷ 변경가능한 자리를 확인하세요. ◁");
				seat.seatVu(seatsNo);
				while(true) {
					System.out.print("▷ 원하는 자리를 입력하세요.>> ");
					choiceSeat = scn.nextInt(); scn.nextLine();
					if (choiceSeat < seat.FIRST_SEAT || choiceSeat > seat.LAST_SEAT) {
						System.out.println("▶ 잘못된 자리번호 입니다. ◀");
					} else if (choiceSeat == mySeat) {
						System.out.printf("▶ 현재 %s님의 자리입니다. ◀\n", loginMem.getName());
					} else if (seatsNo.contains(choiceSeat)) {
						System.out.println("▶ 현재 사용중인 자리입니다. ◀");
					} else {
						System.out.println("▷ " + choiceSeat + "번 자리로 변경합니다. ◁");
						break;
					}
					System.out.println("▶ 자리변경을 취소하고 메인메뉴로 이동할까요? ◀");
					System.out.print("▶ 1.재입력 2.나가기 >> ");
					choice = scn.nextInt(); scn.nextLine();
					if (choice != 1) {
						System.out.println();
						choiceSeat = 0;
						break;
					}
				}
				if (choiceSeat == 0) break;
				
				// 변경진행.(rent 업데이트)
				if (tdao.changeSeat(myTk.getTicketNo(), choiceSeat)) {
					myTk = tdao.myTkUse(loginMem); // 다시 티켓정보 가져오기.
					System.out.println("▷ 자리변경이 완료되었습니다. ◁");
					System.out.printf("▷ %s님 / ", loginMem.getName());
					System.out.printf("자리번호: %d번 /", myTk.getSeatId());
					System.out.printf("남은시간: %s ◁\n\n", myTk.restTimeString());
				} else {
					System.out.println("▶ 자리변경 오류 발생 ◀");
				}
				
				choiceSeat = 0; mySeat = 0;
				
				break;
				
			case 5: // 5.정보조회
				
				// 로그인 다시하기.
				String chkId = loginMem.getMemberId();
				System.out.print("▷ 비밀번호를 다시 입력하세요>> ");
				String chkPw = scn.nextLine();
				mem = jdao.login(chkId, chkPw); // 로그인시도 : mem 에 일단 담음.
				
				// 로그인 성공하면 정보조회.
				if(mem.getMemberId() != null) {
					loginMem = mem; // loginMem에 mem을 다시 담음.
					// 기본정보 조회.
					System.out.println("───────────────────────");
					System.out.println(loginMem);
					// 이용권정보 조회.
					List<Ticket> myTkInfo = tdao.myTkInfo(loginMem);
					for(Ticket tk : myTkInfo) {
						if (tk.getUse().equals("사용중")) {
							System.out.println(tk);
							System.out.println("남은시간: " + tk.restTimeString());
						} else if(tk.getUse().equals("미사용")) {
							System.out.print(tk.simpleString());
						}
					}
					System.out.println("───────────────────────");
					System.out.println("▷ 조회완료 ◁\n");
					chkId = ""; chkPw = ""; mem = null;
				} else {
					System.out.println("▶ 비밀번호 오류입니다. ◀\n");
					chkId = ""; chkPw = ""; mem = null;
				}
				
				break;
				
			case 6: // 6.정보수정
				
				// 로그인 다시하기.
				chkId = loginMem.getMemberId();
				System.out.print("▷ 비밀번호를 다시 입력하세요>> ");
				chkPw = scn.nextLine();
				mem = jdao.login(chkId, chkPw);
				
				// 로그인 성공하면 정보수정.(loginMem에 담기)
				if(mem.getMemberId() != null) {
					loginMem = mem;
					// 비밀번호와 연락처만 수정가능.
					System.out.print("▷ 수정할 비밀번호>> ");
					String pw = scn.nextLine();
					System.out.print("▷ 수정할 연락처>> ");
					String phone = scn.nextLine();
					if(jdao.myInfoUpdate(loginMem, pw, phone)) {
						System.out.println("▷ 수정완료 ◁\n");
					} else {
						System.out.println("▶ 수정 오류 발생 ◀");
					}
					chkId = ""; chkPw = ""; mem = null;
				} else {
					System.out.println("▶ 비밀번호 오류입니다. ◀\n");
					chkId = ""; chkPw = ""; mem = null;
				}
				
				break;
				
			case 7: // 7.퇴실
				
				// 회원이 자리를 사용중인지 확인.
				myTk = tdao.myTkUse(loginMem);
				mySeat = myTk.getSeatId();
				if(mySeat == 0) {
					System.out.println("▶ 현재 사용중인 자리가 없습니다. ◀\n");
					System.out.print("▶ 1.메인메뉴로 2.카페종료  >> ");
					choice = scn.nextInt(); scn.nextLine();
					if (choice == 2 ) {
						System.out.println("▷ 안녕히가세요...◁");
						run = false;
					}
					System.out.println();
					break;
				}
				// 사용중이면 반납진행.
				System.out.printf("▷ %s님의 ", loginMem.getName());
				System.out.printf("남은시간: %s", myTk.restTimeString());
				System.out.printf("(자리번호: %d번) ◁\n", mySeat);
				System.out.println("▷ 퇴실시 잔여시간 사용포기 처리됩니다. ◁\n▷ 퇴실하시겠습니까? ◁\n");
				System.out.print("▷ 1.취소 2.퇴실 >> ");
				choice = scn.nextInt(); scn.nextLine();
				
				if(choice == 2) {
					boolean out = tdao.checkOutRent(myTk) && tdao.checkOutTk(myTk);
					if(out) {
						System.out.println("▷ 퇴실완료(잔여시간 환불불가) ◁\n");
					}else {
						System.out.println("퇴실오류.");
					}
				} else {
					System.out.println("▶ 퇴실이 취소되었습니다... ◀\n");
				}
				
				break;
				
			case 9:
				System.out.println("▷종료합니다...◁");
				run = false;
				
			}// end of switch.
			
		}// end of while.
		
		System.out.println("end of study prog.");
	}// 스터디 메소드.

	
}// end of class.
