package com.javalec.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.common.ShareVar;

public class AdminDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	String adminid;
	String adminpw;
	String adminname;
	
	
	// Constructor
	public AdminDAO() {
		// TODO Auto-generated constructor stub
		
	}
	
	// Method
	public boolean loginCheckAction(String id, String pw) {
		String where1 = "SELECT adminid, adminpw, adminname FROM admin ";
		String where2 = "WHERE adminid = '"+ id + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where1+where2);
			if(rs.next()) {
				return false;	// 이미 모델번호가 있으면 false.
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;	// 이미 모델번호가 없으면 true.
	}
}
