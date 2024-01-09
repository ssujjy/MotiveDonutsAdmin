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
	
	// ========= [ 상품관리 Tab ] ========
	// 카테고리별로 상품 목록 가져오기 
	public ArrayList<ProductDTO> selectProductListByItem(String item, String val){
		
		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String whereDefault = "SELECT p.proname, p.sellprice, p.detail, p.nutritional, p.ingredient, p.imagename, p.image, r.item"
				+ " FROM register r, product p"
				+ " WHERE r.proname = p.proname";
		String where = "";
		if(item!=null && !(item.equals(""))) {
			where = " AND r.item = '"+item+"'";
		}
		if(val!=null && !(val.equals(""))) {
			where = " AND p.proname like '%"+val+"%'";
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			System.out.println(whereDefault+where);
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			while(rs.next()) {	
				String proname = rs.getString(1);
				int sellprice = rs.getInt(2);
				String detail = rs.getString(3);
				String nutritional = rs.getString(4);
				String ingredient = rs.getString(5);
				String imagename = rs.getString(6);
				
				// File
            	File file = new File("./" + imagename);
				FileOutputStream output = new FileOutputStream(file);
				InputStream image = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				while(image.read(buffer) > 0 ) {
					output.write(buffer);
				}
				
				String wItem = rs.getString(8);
				
				
				
				ProductDTO productDto = new ProductDTO(proname, sellprice, detail, nutritional, ingredient, imagename, wItem);
				dtoList.add(productDto);
				
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dtoList;
	}	// End of selectProductListByItem
	
	
	// 콤보박스에 사용할 카테고리 가져오기.
	public ArrayList<String> selectItem(){
		ArrayList<String> itemList = new ArrayList<String>();
//		String[] strCategory;
		String whereDefault = "SELECT item FROM category";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while(rs.next()) {	
				String wItem = rs.getString(1);
				itemList.add(wItem);
				
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return itemList;
	}	// End of selectProductListByItem
	
	
	
}
