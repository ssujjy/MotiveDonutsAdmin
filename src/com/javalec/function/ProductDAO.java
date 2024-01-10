package com.javalec.function;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.javalec.common.ShareVar;

public class ProductDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	String proname;
	int sellprice;
	String detail;
	String nutritional;
	String ingredient;
	FileInputStream image;
	String imagename;
	String item;
	
	// Constructor
	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}


	public ProductDAO(String proname, int sellprice, String detail, String nutritional, String ingredient,
			FileInputStream image, String imagename, String item) {
		super();
		this.proname = proname;
		this.sellprice = sellprice;
		this.detail = detail;
		this.nutritional = nutritional;
		this.ingredient = ingredient;
		this.image = image;
		this.imagename = imagename;
		this.item = item;
	}



	// Method
	// 상품등록이 완료되었는지 여부를 리턴해주는 메소드
	public boolean insertProductAction() {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "INSERT INTO product (proname, sellprice, detail, nutritional, ingredient, image, imagename";
			String B = ") values (?,?,?,?,?,?)";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, proname);
			ps.setInt(2, sellprice);
			ps.setString(3, detail);
			ps.setString(4, nutritional);
			ps.setString(5, ingredient);
			ps.setBinaryStream(6, image);			
			ps.setString(7, nutritional);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
