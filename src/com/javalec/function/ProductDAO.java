package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.common.ShareVar;
import com.javalec.model.ProductDTO;

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


	public ProductDAO(String proname) {
		super();
		this.proname = proname;
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
			String B = ") values (?,?,?,?,?,?,?)";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, proname);
			ps.setInt(2, sellprice);
			ps.setString(3, detail);
			ps.setString(4, nutritional);
			ps.setString(5, ingredient);
			ps.setBinaryStream(6, image);			
			ps.setString(7, imagename);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	// Table을 Click 하였을 경우 수정화면으로 이동하여 해당 필드에 데이터 입력하여 보여줌. 
		public ProductDTO loadDetailProduct() {
			ProductDTO dto = null;
			String where1 = "SELECT  proname, sellprice, detail, nutritional, ingredient, image, imagename\r\n"
							+ "FROM product";
			String where2 = " WHERE proname = '"+ proname + "'";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				ResultSet rs = stmt_mysql.executeQuery(where1+where2);
				if(rs.next()) {
					String wProname = rs.getString(1);
					int wSellprice = rs.getInt(2);
					String wDetail = rs.getString(3);
					String wNutritional = rs.getString(4);
					String wIngredient = rs.getString(5);
					String wImagename = rs.getString(7);
//					String wItem = rs.getString(8);
					String wItem = "";
					
					// File  그림 파일을 하나만들어준다.
					File file = new File(wImagename);
					FileOutputStream output = new FileOutputStream(file);
					InputStream input = rs.getBinaryStream(6);
					byte[] buffer = new byte[1024];
					while(input.read(buffer) > 0 ) {
						output.write(buffer);
					}
					
					dto = new ProductDTO(wProname, wSellprice, wDetail, wNutritional, wIngredient, wImagename, wItem);
					
				}
				conn_mysql.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return dto;
		}
		
		// 기존상품 업데이트.	// 구분을 두어서 상품을 전시할지 말지 결정할것.(삭제시 안보이게 할거면)
		public boolean updateProductAction() {
			PreparedStatement ps = null; 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				//UPDATE `motivedonuts`.`product` SET `sellprice` = '5200', `ingredient` = '녹차, 우유', `imagename` = 'the_green_mugwort' WHERE (`proname` = '더 그린 쑥 블렌디드');
//				proname, sellprice, detail, nutritional, ingredient, image, imagename
				String A = "UPDATE product SET sellprice = ?, detail = ?, nutritional = ?, ingredient = ?, image = ?, imagename = ? ";
				String B = " WHERE proname = ?";
				
				ps = conn_mysql.prepareStatement(A+B);
				ps.setInt(1, sellprice);
				ps.setString(2, detail);
				ps.setString(3, nutritional);
				ps.setString(4, ingredient);
				ps.setBinaryStream(5, image);
				ps.setString(6, imagename);
				ps.setString(7, proname);
				ps.executeUpdate();
				
				conn_mysql.close();
				
			}catch(Exception e) {
//				e.printStackTrace();
				return false;
			}
			return true;
		}
		// 삭제	// 구분을 두어서 상품을 전시할지 말지 결정할것.(삭제시 안보이게 할거면)
		public boolean deleteProductAction() {
			PreparedStatement ps = null; 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				String A = "DELETE FROM product";
				String B = " WHERE proname = ?";
				
				ps = conn_mysql.prepareStatement(A+B);
				ps.setString(1, proname);
				ps.executeUpdate();
				
				conn_mysql.close();
				
			}catch(Exception e) {
//				e.printStackTrace();
				return false;
			}
			return true;
		}
}
