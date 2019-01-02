package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BJService {

	Scanner scan ;	//스캐너사용을 위해서 scan이라는 변수로 선언 
	
	/*
	 * 아래에서 A~D를 각각 ♠~♣까지로 변경 저장해주는 부분
	 */
	private final char A = '♠';
	private final char B = '◆';
	private final char C = '♥';
	private final char D = '♣';
	
	/*
	 * 카드  52장을 생성해서 담을 카드리스트(cardList)
	 * 딜러가 받을 카드들을 묶어서 담을 카드리스트(daeler)
	 * 유저가 받을 카드들을 묶어서 담을 카드리스트(user1)
	 * 세개의 리스트를 각각의 변수를 지정하여 선언
	 */
	List<String> cardList;
	List<String> dealer;
	List<String> user1;
	
	
	public BJService() {
		/*
		 * 생성자 안에서 스캐너와 3개의 리스트를 초기화
		 */
		scan = new Scanner(System.in);
		cardList = new ArrayList();
		dealer = new ArrayList();
		user1 = new ArrayList();
	}
	
	/*
	 * 52장의 카드를 만드는 메소드
	 */
	public void CreateCard() {
		for(int i = 1 ; i <= 10 ; i ++) { 	// 1~10까지 4가지 문양의 카드를 총 40장 만드는 부분
			for(int j = A ; j <= D ; j++) {
				String card = ""; 	// card라는 스트링변수를 생성해서 값을 담아 사용하기 위해서 
									// 초기화까지 해주는 부분
				if(i == 1) {	// 숫자 1을 A(ace)로 변경해서 저장해 주는 부분
								// 나중에 점수계산을 위해서 카드넘버와 패턴사이에 : 를 넣어서 저장
					card = "A" + ":" + (char)j;
					
				} else {		// 1을 제외한 2~10까지의 숫자를 사이에 :를 넣고 패턴과함께 저장
				card = ("" + i) + ":" + (char)j;
				}
				cardList.add(card);	// 카드리스트에 카드를 추가해주는 부분
			}
		}
		for(int j = A ; j <= D ; j++) { 	// J Q K 세장의 카드를 각 4가지 패턴을 적용해서 
											// 12장의 카드를 생성해서 리스트에 저장하는부분
			cardList.add("J" + ":" + (char)j);
			cardList.add("Q" + ":" + (char)j);
			cardList.add("K" + ":" + (char)j);
		}
	}
	
	/*
	 * 만들어진 카드리스트가 제대로 완성이 되었는지 확인하기 위해서 만든 메소드
	 */
	public void viewcardList() {
		for(String s : cardList) { 	//확장for를 이용하여 카드리스트의 개수만큼 실행
			System.out.print(s+"  ");
			// 각각의 카드 사이에 경계를 두기위해서 "  "를 추가함
		}
		System.out.println();
	}
	
	/*
	 * 52장으로 만들어진 카드의 리스트를 콜렉션클래스의 셔플메서드를 이용해서 섞어주는 부분 
	 */
	public void shuffleCard() {
		Collections.shuffle(cardList); 	// 일반배열은 안되고 리스트만 사용가능
	}
	
	/*
	 * 딜러의 현재 가지고있는 카드를 출력하는 메소드
	 */
	public void viewdealerList() {
		String list = "";
		for(String s : dealer) {
			list += s +"  ";
		}
		System.out.println("딜        러 의 카드 : " + list);
	}
	
	/*
	 * 유저가 현재 가지고있는 카드를 출력하는 메소드
	 */
	public void viewuser1List() {
		String list1 = "";
		for(String s : user1) {
			list1 += s +"  ";
		}
		System.out.println("user1님의 카드 : " + list1);
	}
	
	/*
	 * 52장의 카드를 맨위부터 유저와 딜러에게 한장씩 2번 나눠주는 메소드
	 */
	public void distributeCard() {
		
		for(int i = 0 ; i < 2 ; i ++) {
			user1.add(cardList.get(0)); //유저에게 카드한장지급
			cardList.remove(0);			//유저에게 준 카드를 카드리스트에서 삭제(중복값제거)
			dealer.add(cardList.get(0));//딜러에게 카드한장지급 
			cardList.remove(0);			//딜러에게 준 카드를 카드리스트에서 삭제(중복값제거)
		}
		viewdealerList(); 		//딜러의 현재카드리스트를 보여줌
		viewuser1List();		//유저의 현재카드리스트를 보여줌
	}
	
	/*
	 * 게임을 실행하는 메소드
	 */
	public void playGame() {
		
		//게임시작을 위해 카드를 생성하는 메서드
		this.CreateCard();
		
		//카드를 섞는 메서드
		this.shuffleCard();
		
		
		// strU1을 나중에 맨마지막에 와일문을 멈추기위한 조건으로 사용하기 위해서 미리 와일문 안 맨위에 선언 초기화해줌
		String strU1 = "";
		
		// 승패를 결정하는 메소드의 매개변수로 넣어주기위해서 미리 선언 초기화해줌
		int user1S = 0;
		int dealerS = 0;
		
		System.out.println("=======================================================");
		System.out.println("★★★★★★★★★★★★★★★★★Black Jack v1.0★★★★★★★★★★★★★★★★★");
		System.out.println("=======================================================");
		System.out.println();
		System.out.println("-------------------------------------------------------");
		this.distributeCard(); // 게임을 시작하기 위해서 딜러와 유저에게 각각 카드 배분
		System.out.println("-------------------------------------------------------");
		System.out.println();
		while(true) {
			
			// user1S라는 변수에 유저의 카드 합계를 계산하는 메소드를 이용해서 리턴받은 값을 저장
			user1S = user1Score();
			// dealerS라는 변수에 딜러의 카드 합계를 계산하는 메소드를 이용해서 리턴받은 값을 저장
			dealerS = dealerScore();
			
			if(user1S == 0) break; 	// 유저의 카드총합이 21점을 넘어간 경우 게임을 끝내기위한 조건문
			if(dealerS == 0) break; // 딜러의 카드총합이 21점을 넘어간 경우 게임을 끝내기위한 조건문
			if(user1S == 21 || dealerS == 21) break; 	// 유저와 딜러 중에 21점이 된 경우 게임을 끝내기 위한 조건문
			if(strU1.equalsIgnoreCase("N") & dealerS > 17) break;
			// 유저가 카드를 받지않고 딜러도 더이상의 카드 추가가 없을 때 승부를 가리기위해 게임을 끝내는 조건문
			
			//유저의 카드의 합이 21을 넘지 않으면 아래의 코드진행을 실행하기 위한 조건문
			//카드의 합의 21을 넘어가면 0을 리턴받기때문에 0이 아니고 21을 넘지않는다는 조건문을 작성
			if(user1S != 0 & user1S < 21) {
				// scan변수를 이용해서 스캐너를 실행 입력받은 문자열을 strU1에 저장하는 부분
				System.out.println(" ☞ user1님 카드를 받으시겠습니까?");
				System.out.print("Y/N >> ");
				strU1 = scan.nextLine();
				if(strU1.equalsIgnoreCase("Y")) {
					// if문을 사용해서 Y를 입력받으면 카드를 추가해서 유저리스트에 추가하고 
					// 해당 카드를 카드리스트에서 삭제하는 부분
					user1.add(cardList.get(0));
					cardList.remove(0);
				}
			}
			// 딜러의 카드의 합이 16이하 일때 카드를 추가 하기 위한 조건문
			// 딜러의 카드의 합이 21이 넘어가면 0을 리턴받기때문에 21을 넘지않는다는 조건문을 완성하기위해 0이 아닌 조건문 작성
			if(dealerScore() != 0 & dealerScore() <= 16) {
				// 딜러의 카드합이 16이하일 때 카드를 추가해서 딜러리스트에 추가하고 해당카드를 카드리스트에서 삭제하는 부분
				dealer.add(cardList.get(0));
				cardList.remove(0);
			}
			
			System.out.println();
			System.out.println("-------------------------------------------------------");
			// 카드를 추가 후 딜러의 카드리스트를 출력
			viewdealerList();
			// 카드를 추가 후 유저의 카드리스트를 출력
			viewuser1List();
			System.out.println("-------------------------------------------------------");
			System.out.println();
			
		}
		// 게임이 끝나고 딜러와 유저의 점수를 각각 출력해주는 부분
		System.out.println("딜러의 점수 : " + dealerS);
		System.out.println("유저의 점수 : " + user1S);
		//유저와 딜러의 점수를 매개변수로 보내서 winandlose메서드를 이용해서 승패를 출력하는 부분
		WinAndLose(user1S, dealerS);
	}
	
	/*
	 * 유저의 카드리스트의 카드점수를 더해서 총합을 구해 점수를 계산하고 계산된 점수를 리턴하는 메서드
	 */
	public int user1Score() {
		int intUser1 = 0; //int형 변수 intUser1을 선언 초기화
		for(String s1 : user1) { //user1리스트의 개수만큼 확장for를 이용해서 실행
			//:를 기준으로 패턴을 제외하고 앞의 숫자만 잘라내어 splitUser1의 String배열에 담음
			String[] splitUser1 = s1.split(":");
			//swichAJQK메서드를 사용해서 영문자로 된 카드의 점수를 리턴받거나 
			//해당 카드의 숫자를 int형으로 리턴받아서 int형 변수 score에 저장
			int score = swichAJQK(splitUser1[0]);
			//카드의 총합을 구하기 위해 intUser1의 변수에 계속해서 더해주는 부분
			intUser1 += score;
		}
		if(intUser1 <= 21) return intUser1; // 확장for이후의 값이 21을 이하이면 해당값을 리턴
		return 0;		// 21초과이면(21이하가 아니면) 0을 리턴해주는 부분
	}
	
	/*
	 * 딜러의 카드리스트의 카드점수를 더해서 총합을 구해 점수를 계산하고 계산된 점수를 리턴하는 메서드
	 */
	public int dealerScore() {
		int intdealer = 0; //int형 변수 intdealer를 선언 초기화
		for(String dr : dealer) { //dealer리스트의 개수만큼 확장for를 이용해서 실행
			//위의 user1Score메서드와 동일한 방법
			String[] splitdealer = dr.split(":");
			int score = swichAJQK(splitdealer[0]);
			intdealer += score;
		}
		if(intdealer <= 21) return intdealer;
		return 0;
	}
	
	/*
	 * A J Q K 의 문자열들을 매개변수로 받아서 해당 문자열의 경우 원하는 값을 리턴해주고 
	 * A J Q K 의 문자열이 아닌 경우 Integer형으로 변경해서 리턴해주는 메서드
	 */
	public int swichAJQK(String s) {
		
		// A문자열인 경우 정수 1을 리턴해주는 부분
		if(s.equalsIgnoreCase("A")) {
			return 1;
		}
		
		// J,Q,K의 문자열인 경우 정수 10을 리턴해주는 부분
		if(s.equalsIgnoreCase("J")
				|| s.equalsIgnoreCase("Q") || s.equalsIgnoreCase("K")) {
			return 10;
		}
		// A,J,Q,K의 문자열이 아닌경우 해당 문자열을 Integer형으로 변경하여 리턴해주는 부분
		return Integer.valueOf(s);
	}
	
	/*
	 * 게임이 끝나고 딜러와 유저의 카드총합을 매개변수로 받아 두 값을 비교해서 승리와 패배 그리고 무승부를 출력해주는 메소드
	 */
	public void WinAndLose(int user1S, int dealerS) {
		//승리자를 지정하여 배팅금액 계산에 쓰일 조건문의 조건을 위한 변수 지정부분
		//위너를 넣어서 이길경우 배팅금액만큼을 추가해주고
		//패배할 경우 배팅금액만큼을 제외시켜기 위한 if문의 조건에 들어갈 변수
		String winner = ""; 
		
		//유저와 딜러가 동점일 때 무승부출력부분
		if(user1S == dealerS) { 
			System.out.println("draw");
			winner = "draw";
			return;
		}
		//유저가 딜러의 점수총합보다 높을 때 승리출력 부분
		if(user1S > dealerS) {
			System.out.println("user1님이 승리하셨습니다." );
			winner = "user1"; 
			return;
		}
		//유저가 딜러의 점수총합보다 낮을 때 패배 출력 부분
		if(dealerS > user1S) {
			System.out.println("user1님이 패배하셨습니다." );
			winner = "dealer";
			return;
		}
	}
	
	
}
