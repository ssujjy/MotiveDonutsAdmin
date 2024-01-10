package com.javalec.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.model.ProductDTO;

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
	// ========= [ 관리자 로그인 ] ==========
	// Login시 등록된 관리자인지 확인 후 있으면 true, 없으면 false 리턴값을 넘겨줌. 
	public boolean loginCheckAction(String id, String pw) {
		String where1 = "SELECT adminid, adminpw, adminname FROM admin ";
		String where2 = "WHERE adminid = '"+ id + "'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where1+where2);
			if(rs.next()) {
				return true;	// 등록된 ID가 있으면 true.
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;	// 등록된 ID가 없으면 false.
	}
}
