package com.biz.blackjack.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 이 곳은 테스트를 위한 서비스 클래스 입니다.
 */
public class BJServicetest {

	Scanner scan ;
	
	private final char A = '♠';
	private final char B = '◆';
	private final char C = '♥';
	private final char D = '♣';
	
	List<String> cardList;
	List<String> dealer;
	List<String> user1;
	List<String> user2;
	
	
	public BJServicetest() {
		scan = new Scanner(System.in);
		cardList = new ArrayList();
		dealer = new ArrayList();
		user1 = new ArrayList();
		user2 = new ArrayList();
	}
	
	
	public void CreateCard() {
		for(int i = 1 ; i <= 10 ; i ++) {
			for(int j = A ; j <= D ; j++) {
				String card = "";
				if(i == 1) {
					card = "A" + ":" + (char)j;
					
				} else {
				card = ("" + i) + ":" + (char)j;
				}
				cardList.add(card);
			}
		}
		for(int j = A ; j <= D ; j++) {
			cardList.add("J" + ":" + (char)j);
			cardList.add("Q" + ":" + (char)j);
			cardList.add("K" + ":" + (char)j);
		}
	}
	
	
	public void viewcardList() {
		for(String s : cardList) {
			System.out.print(s+"  ");
			
		}
		System.out.println();
//		System.out.println(cardList.size());
	}
	
	
	public void viewdealerList() {
		String list = "";
		for(String s : dealer) {
			list += s +"  ";
		}
		System.out.println("딜  러 의 카드 : " + list);
	}
	
	public void viewuser1List() {
		String list1 = "";
		for(String s : user1) {
			list1 += s +"  ";
		}
		System.out.println("user1님의 카드 : " + list1);
	}
	
	
	public void viewuser2List() {
		String list2 = "";
		for(String ss : user2) {
			list2 += ss +"  ";
		}
		System.out.println("user2님의 카드 : " + list2);
	}
	
	
	public void shuffleCard() {
		Collections.shuffle(cardList);
	}
	
	
	public void distributeCard() {
		
		for(int i = 0 ; i < 2 ; i ++) {
			user1.add(cardList.get(0));
			user2.add(cardList.get(1));
			dealer.add(cardList.get(2));
			for(int j = 0 ; j < 3 ; j++){
				cardList.remove(0);
			}
		}
		viewdealerList();
		viewuser1List();
		viewuser2List();
	}
	
	public void playGame() {
		String strU1 = "";
		String strU2 = "";
		
		int user1S = 0;
		int user2S = 0;
		int dealerS = 0;
		
		while(true) {
			
			user1S = user1Score();
			user2S = user2Score();
			dealerS = dealerScore();
			
			if(user1S != 0 & user1S < 21) {
				System.out.println("user1님 카드를 받으시겠습니까?");
				System.out.print("Y/N >> ");
				strU1 = scan.nextLine();
				if(strU1.equalsIgnoreCase("Y")) {
					user1.add(cardList.get(0));
					cardList.remove(0);
					user1S = user1Score();
				}
				
			}
			
			if(user2S != 0 & user2S < 21) {
				System.out.println("user2 님 카드를 받으시겠습니까?");
				System.out.print("Y/N >> ");
				strU2 = scan.nextLine();
				if(strU2.equalsIgnoreCase("Y")) {
					user2.add(cardList.get(0));
					cardList.remove(0);
					user2S = user2Score();
				}
				
			}
			
			int SumDC = dealerScore();
			if(SumDC != 0 & SumDC <= 16) {
				dealer.add(cardList.get(0));
				cardList.remove(0);
			}
			dealerS = dealerScore();
			
			viewdealerList();
			viewuser1List();
			viewuser2List();
			
			if(user1S == 21 || user2S == 21 || dealerS == 21) break;
			
			if( (user1S == 0 & user2S == 0) || (user1S == 0 & dealerS == 0) ||
			(user2S == 0 & dealerS == 0) ) break;
			
			if((strU1.equalsIgnoreCase("N") & strU2.equalsIgnoreCase("N")) ||
			(user2S == 0 & dealerS >= 17)|| (user1S == 0 & dealerS >= 17)) break;
		}
		WinAndLose(user1S, user2S, dealerS);
		
	}
	
	
	public int user1Score() {
		int intUser1 = 0;
		for(String s1 : user1) {
			String[] splitUser1 = s1.split(":");
			int score = swichAJQK(splitUser1[0]);
			intUser1 += score;
		}
		System.out.println(intUser1);
		if(intUser1 <= 21) return intUser1;
		return 0;
	}
	
	
	public int user2Score() {
		int intUser2 = 0;
		for(String s2 : user2) {
			String[] splitUser2 = s2.split(":");
			int score = swichAJQK(splitUser2[0]);
			intUser2 += score;
		}
		System.out.println(intUser2);
		if(intUser2 <= 21) return intUser2;
		return 0;
	}
	
	
	public int dealerScore() {
		int intdealer = 0;
		for(String dr : dealer) {
			String[] splitdealer = dr.split(":");
			int score = swichAJQK(splitdealer[0]);
			intdealer += score;
		}
		System.out.println("딜러점수 : " + intdealer);
		if(intdealer <= 21) return intdealer;
		return 0;
	}
	
	
	public int swichAJQK(String s) {
		if(s.equalsIgnoreCase("A")) {
			return 1;
		}
		if(s.equalsIgnoreCase("J")
				|| s.equalsIgnoreCase("Q") || s.equalsIgnoreCase("K")) {
			return 10;
		}
		return Integer.valueOf(s);
	}
	
	public void WinAndLose(int user1S, int user2S, int dealerS) {
		String winner = "";
		if(user1S == user2S & user1S > dealerS) {
			System.out.println("user1 & user2 draw");
			winner = "user1and2 draw";
			return;
		}
		if(user1S > user2S & user1S == dealerS) {
			System.out.println("user1 & dealer draw");
			winner = "user1andDealer draw";
			return;
		}
		if(user2S > user1S & user2S == dealerS) {
			System.out.println("user2 & dealer draw");
			winner = "user2andDealer draw";
			return;
		}
		if(user1S == user2S & user1S == dealerS) {
			System.out.println("draw");
			winner = "draw";
			return;
		}
		
		if(user1S > user2S & user1S > dealerS) {
			System.out.println("user1님이 승리하셨습니다." );
			winner = "user1";
			return;
		}
		if(user2S > user1S & user2S > dealerS) {
			System.out.println("user2님이 승리하셨습니다." );
			winner = "user2";
			return;
		}
		if(dealerS > user1S & dealerS > user2S) {
			System.out.println("dealer님이 승리하셨습니다." );
			winner = "dealer";
			return;
		}
	}
	
	public void chipset() {
		
	}
}
