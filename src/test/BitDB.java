package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BitDB {
	Connection con = null;

	// 미리 쿼리문의 포맷을 미리 설정...
	PreparedStatement stmt_insert = null;

	public BitDB() throws Exception {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩 성공");
			// "jdbc:mysql://localhost:3306?serverTimezone=UTC","root","1234");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projDB?serverTimezone=UTC", "root", "1234");
			con.setAutoCommit(false);
			System.out.println("데이터베이스 연결성공");
			// <=================================

		} catch (Exception e) {
			throw new Exception("데이터베이스 연결 오류");
		}
	}

	public boolean MakeMember(String id, String name) {
		try {
			String Insert = "insert into bit_member values (?, ?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setString(1, id);
			sment.setString(2, name);
			sment.setString(3, "nomal");
			int i = sment.executeUpdate();
			sment.close(); //
			if (i > 0) {
				con.commit();
				return true;
			}
			return false;
		} catch (Exception ex) {
			System.out.println("회원 가입 실패" + ex.getMessage());
			return false;
		}
	}

	public ArrayList<String> SelectAllMember() {
		try {
			String sql = "Select * from bit_member;";
			PreparedStatement sment = this.con.prepareStatement(sql);

			// --------------------------------------------------
			ArrayList<String> arr = new ArrayList<String>();
			ResultSet rs = sment.executeQuery();
			while (rs.next()) {
				String tmp = "ID:";
				tmp += rs.getString(1) + " 이름:";
				tmp += rs.getString(2) + " 등급:";
				tmp += rs.getString(3);
				arr.add(tmp);
			}
			// db.getCon().commit();
			sment.close(); // <===================================
			return arr;
		} catch (Exception ex) {
			System.out.println("printAllBus fail" + ex.getMessage());
			return null;
		}

	}

	public boolean MakeDrink(String name, int price) {
		try {
			String Insert = "insert into bit_Drink(name,price) values (?, ?);";
			PreparedStatement sment = con.prepareStatement(Insert);
			sment.setString(1, name);
			sment.setInt(2, price);
			int i = sment.executeUpdate();
			sment.close(); //
			if (i > 0) {
				con.commit();
				return true;
			}
			return false;
		} catch (Exception ex) {
			System.out.println("음료 등록 실패" + ex.getMessage());
			return false;
		}
	}

	public ArrayList<String> SelectAllDrink() {
		try {
			String sql = "Select * from bit_drink;";
			PreparedStatement sment = this.con.prepareStatement(sql);

			// --------------------------------------------------
			ArrayList<String> arr = new ArrayList<String>();
			ResultSet rs = sment.executeQuery();
			while (rs.next()) {
				String tmp = "ID:";
				tmp += rs.getString(1) + " 이름:";
				tmp += rs.getString(2) + " 가격:";
				tmp += rs.getString(3) + " 판매량:";
				tmp += rs.getString(4);
				arr.add(tmp);
			}
			// db.getCon().commit();
			sment.close(); // <===================================
			return arr;
		} catch (Exception ex) {
			System.out.println("printAlldrink fail" + ex.getMessage());
			return null;
		}
	}

	public ArrayList<String> SelectAllMemberLevel() {
		try {
			String sql = "Select * from bit_memberlevel;";
			PreparedStatement sment = this.con.prepareStatement(sql);

			// --------------------------------------------------
			ArrayList<String> arr = new ArrayList<String>();
			ResultSet rs = sment.executeQuery();
			while (rs.next()) {
				String tmp = "등급:";
				tmp += rs.getString(1) + " low:";
				tmp += rs.getString(2) + " high:";
				tmp += rs.getString(3);
				arr.add(tmp);
			}
			// db.getCon().commit();
			sment.close(); // <===================================
			return arr;
		} catch (Exception ex) {
			System.out.println("printAllMemberLevel fail" + ex.getMessage());
			return null;
		}
	}

	public ArrayList<String> SelectAllBuyDrink() {
		try {
			String sql = "Select * from bit_buydrink order by memberid;";
			PreparedStatement sment = this.con.prepareStatement(sql);

			// --------------------------------------------------
			ArrayList<String> arr = new ArrayList<String>();
			ResultSet rs = sment.executeQuery();
			while (rs.next()) {
				String tmp = "ID:";
				tmp += rs.getString(1) + " 맴버아이디:";
				tmp += rs.getString(2) + " 음료아이디:";
				tmp += rs.getString(3) + " 수량";
				tmp += rs.getString(4) + " 총액";
				tmp += rs.getString(5);
				arr.add(tmp);
			}
			// db.getCon().commit();
			sment.close(); // <===================================
			return arr;
		} catch (Exception ex) {
			System.out.println("printAllMemberLevel fail" + ex.getMessage());
			return null;
		}
	}

	public boolean BuyDrink(String mid, int did, int cnt) {
		try {
			String sql1 = "insert into bit_buydrink(memberid,drinkid,count,totalmoney)+" + " values(?,?,?,?);";
			String sql = "select price from bit_drink where drinkid=?;";
			String sql2 = "update bit_drink set cout=cout+1 where drinkid=? ";
			PreparedStatement sment = this.con.prepareStatement(sql);
			sment.setInt(1, did);
			// --------------------------------------------------
			ResultSet rs = sment.executeQuery();
			sment = con.prepareStatement(sql2);
			sment.setInt(1, did);
			sment.executeUpdate();
			int price = 0;
			while (rs.next()) {
				price = rs.getInt(1);
			}
			sment = con.prepareStatement(sql1);
			sment.setString(1, mid);
			sment.setInt(2, did);
			sment.setInt(3, cnt);
			sment.setInt(4, price * cnt);
			int i = sment.executeUpdate();
			// sment.close();
			if (i > 0) {
				if (price >= 2000) {
					String tmp = "update bit_member set level='vvip' where memberid=? ";
					sment = con.prepareStatement(tmp);
					sment.setString(1, mid);
					sment.executeUpdate();
				} else if (price >= 1000) {
					String tmp = "update bit_member set level='vip' where memberid=? ";
					sment = con.prepareStatement(tmp);
					sment.setString(1, mid);
					sment.executeUpdate();
				}
				sment.close();  
				return true;
			} else {
				sment.close();  
				return false;
			}
		} catch (Exception ex) {
			System.out.println("buyDrinkfail" + ex.getMessage());
			return false;
		}
	}
}
