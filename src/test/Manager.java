package test;

import java.util.ArrayList;

public class Manager {
	//Database저장
		private BitDB  db;
		
		//생성자
		public Manager() {
			try {
				db = new BitDB();
			}
			catch(Exception ex) {
				System.out.println("데이터 베이스 접속 에러");			
			}
		}
	
		public void MakeMember() {
			try {
				//1. 요청정보(client)
				String id = BitGlobal.InputString("회원번호");
				String name = BitGlobal.InputString("이름");
				if(db.MakeMember(id,name))
					System.out.println("[저장 성공]");
				else {
					System.out.println("[저장 실패]");
				}
			}
			catch(Exception ex) {
				System.out.println("[저장실패] " + ex.getMessage());
			}
		}
		public void SelectAllMember() {
			try {
				//1. 요청정보(client)
				
				ArrayList<String> memlist=db.SelectAllMember();
				for(String st:memlist) {
					System.out.println(st);
				}
				
				
			}
			catch(Exception ex) {
				System.out.println("[출력실패 ] " + ex.getMessage());
			}
			
		}
		public void MakeDrink() {
			// TODO Auto-generated method stub
			try {
				//1. 요청정보(client)
				String name = BitGlobal.InputString("음료이름");
				int price = BitGlobal.InputNumber("음료가격");
				if(db.MakeDrink(name, price))
					System.out.println("[저장 성공]");
				else {
					System.out.println("[저장 실패]");
				}
			}
			catch(Exception ex) {
				System.out.println("[저장실패] " + ex.getMessage());
			}
			
		}
		public void SelectAllDrink() {
			// TODO Auto-generated method stub
			try {
				//1. 요청정보(client)
				
				ArrayList<String> drinklist=db.SelectAllDrink();
				for(String st:drinklist) {
					System.out.println(st);
				}
				
				
			}
			catch(Exception ex) {
				System.out.println("[출력실패 ] " + ex.getMessage());
			}
		}
		public void SelectAllMemberLevel() {
			try {
				//1. 요청정보(client)
				
				ArrayList<String> levellist=db.SelectAllMemberLevel();
				for(String st:levellist) {
					System.out.println(st);
				}
				
				
			}
			catch(Exception ex) {
				System.out.println("[출력실패 ] " + ex.getMessage());
			}
			
		}
		public void SelectAllBuyDrink() {
			try {
				//1. 요청정보(client)
				
				ArrayList<String> buyllist=db.SelectAllBuyDrink();
				for(String st:buyllist) {
					System.out.println(st);
				}
				
				
			}
			catch(Exception ex) {
				System.out.println("[출력실패 ] " + ex.getMessage());
			}
			
		}
		public void BuyDrink() {
			// TODO Auto-generated method stub
			String mid = BitGlobal.InputString("회원번호");
			int did = BitGlobal.InputNumber("음료번호");
			int cnt = BitGlobal.InputNumber("음료번호");
			if(db.BuyDrink(mid,did,cnt)) {
				System.out.println("음료구매 성공");
			}
			else {
				System.out.println("음료 구매 실패");
			}
			
		}
		public void MemberBuyInfo() {
			// TODO Auto-generated method stub
			
		}
		public void KingOfDrink() {
			// TODO Auto-generated method stub
			
		}
	}
