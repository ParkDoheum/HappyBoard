package kr.co.happy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
	private DBConnector() {}
	
	public static Connection getConn() {
		Connection conn = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "hr";
			String pw = "hkitedu";			
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("연결 이상무");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection c, PreparedStatement p, ResultSet r) {
		if(r != null) {
			try { r.close(); } catch(Exception e) {}
		}
		
		if(p != null) {
			try { p.close(); } catch(Exception e) {}
		}
		
		if(c != null) {
			try { c.close(); } catch(Exception e) {}
		}
	}
}
