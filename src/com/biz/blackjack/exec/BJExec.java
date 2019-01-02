package com.biz.blackjack.exec;

import com.biz.blackjack.service.BJService;

public class BJExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//블랙잭서비스 클래스를 사용하기 위해서 생성자를 bjs라는 객체로  지정 초기화
		BJService bjs = new BJService();
		
		//게임을 시작하는 메서드
		bjs.playGame();
	}

}
