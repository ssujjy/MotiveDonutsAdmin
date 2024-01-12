package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.common.ShareVar;

public class StockDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	// Constructor
	public StockDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Method
	public boolean insertSellAction(String pItem, String pProname, int pqty) {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			String A = "INSERT INTO sell (stonum, cateitem, proname, sellqty, stockqty selldate)";
			String B = " VALUES ( ?, ?, ?, ?, ?, sysdate())";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setInt(1, ShareVar.storeNum);
			ps.setString(2, pItem);
			ps.setString(3, pProname);
			ps.setInt(4, pqty);
			ps.setInt(5, pqty);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
