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
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.model.ProductDTO;

public class ProductDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	// Product DB
	String proname;
	String engproname;
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
	
	public ProductDAO(String proname, String engproname, int sellprice, String detail, String nutritional,
			String ingredient, FileInputStream image, String imagename, String item) {
		super();
		this.proname = proname;
		this.engproname = engproname;
		this.sellprice = sellprice;
		this.detail = detail;
		this.nutritional = nutritional;
		this.ingredient = ingredient;
		this.image = image;
		this.imagename = imagename;
		this.item = item;
	}

	// Method
	// ========= [ 상품관리 ] ========
	// 카테고리별로 상품 목록 가져오기 
	public ArrayList<ProductDTO> selectProductListByItem(String item, String val){
		
		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String whereDefault = "SELECT p.proname, p.engproname, p.sellprice, p.detail, p.nutritional, p.ingredient, p.imagename, p.image, r.item"
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
//			System.out.println(whereDefault+where);
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			while(rs.next()) {	
				String proname = rs.getString(1);
				String engproname = rs.getString(2);
				int sellprice = rs.getInt(3);
				String detail = rs.getString(4);
				String nutritional = rs.getString(5);
				String ingredient = rs.getString(6);
				String imagename = rs.getString(7);
				
				// File  그림 파일을 하나만들어준다.
//				ShareVar.filename = ShareVar.filename +1 ;
				File file = new File(engproname);
				FileOutputStream output = new FileOutputStream(file);
				InputStream image = rs.getBinaryStream(8);
				byte[] buffer = new byte[1024];
				while(image.read(buffer) > 0 ) {
					output.write(buffer);
				}
				String wItem = rs.getString(9);
				
				ProductDTO productDto = new ProductDTO(proname, engproname, sellprice, detail, nutritional, ingredient, imagename, wItem);
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
		String whereDefault = "SELECT item FROM category";
//		String where = "";
//		if(val != null && !(val.equals(""))) {
//			where = " WHERE item like '%"+val+"%'";
//		}
//		System.out.println(whereDefault+where);
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
	
	// 상품등록이 완료되었는지 여부를 리턴해주는 메소드
	public boolean insertProductAction() {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = "INSERT INTO product (proname, engproname, sellprice, detail, nutritional, ingredient, image, imagename";
			String B = ") values (?,?,?,?,?,?,?,?)";
			
//			System.out.println(A+B);
//			
//			System.out.println(" proname : "+ proname);
//			System.out.println(" engproname : "+ engproname);
//			System.out.println(" sellprice : "+ sellprice);
//			System.out.println(" detail : "+ detail);
//			System.out.println(" nutritional : "+ nutritional);
//			System.out.println(" ingredient : "+ ingredient);
//			System.out.println(" image : "+ image);
//			System.out.println(" imagename : "+ imagename);
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, proname);
			ps.setString(2, engproname);
			ps.setInt(3, sellprice);
			ps.setString(4, detail);
			ps.setString(5, nutritional);
			ps.setString(6, ingredient);
			ps.setBinaryStream(7, image);			
			ps.setString(8, imagename);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	// 상품등록 할 때 [Register DB]에도 등록해주기. 
	public boolean insertRegisterAction(String pproname, String pitem, String gubun) {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String A = " INSERT INTO register (adminid, stonum, proname, item, gubun, regdate) ";
			String B = " VALUES (?,?,?,?,?,sysdate())";
			System.out.println(A+B);
			System.out.println("id : "+ ShareVar.loginID +" storeNum : "+ ShareVar.storeNum + " pproname : "+ pproname + " pitem : " + pitem+" gubun : " + gubun);
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, ShareVar.loginID); // 로그인한 아이디로 등록.
			ps.setInt(2, ShareVar.storeNum);	// 로그인한 상점으로 등록.
			ps.setString(3, pproname);
			ps.setString(4, pitem);
			ps.setString(5, gubun);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	// Table을 Click 하였을 경우 수정화면으로 이동하여 해당 필드에 데이터 입력하여 보여줌. 
	public ProductDTO loadDetailProduct(String pproname) {
		ProductDTO dto = null;
		String whereDefault = "SELECT p.proname, p.engproname, p.sellprice, p.detail, p.nutritional, p.ingredient, p.imagename, p.image, r.item"
				+ " FROM register r, product p"
				+ " WHERE r.proname = p.proname";
		String where = " AND  p.proname = '"+ pproname + "'";
		
//			System.out.println(whereDefault+where);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			if(rs.next()) {
				String wProname = rs.getString(1);
				String wEngproname = rs.getString(2);
				int wSellprice = rs.getInt(3);
				String wDetail = rs.getString(4);
				String wNutritional = rs.getString(5);
				String wIngredient = rs.getString(6);
				String wImagename = rs.getString(7);
				// File  그림 파일을 하나만들어준다.
				File file = new File(wImagename);
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(8);
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0 ) {
					output.write(buffer);
				}
				String wItem = rs.getString(9);
				
				dto = new ProductDTO(wProname, wEngproname, wSellprice, wDetail, wNutritional, wIngredient, wImagename, wItem);
				
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
			String A = "UPDATE product SET engproname = ?, sellprice = ?, detail = ?, nutritional = ?, ingredient = ?, image = ?, imagename = ? ";
			String B = " WHERE proname = ?";
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, engproname);
			ps.setInt(2, sellprice);
			ps.setString(3, detail);
			ps.setString(4, nutritional);
			ps.setString(5, ingredient);
			ps.setBinaryStream(6, image);
			ps.setString(7, imagename);
			ps.setString(8, proname);
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
	
	// ========= [ 상품분류(Category/Item) 관리 ] ========
	// 상품분류가 있는지 체크 
	public boolean checkItemAction(String pItem) {
		boolean chkResult = false;
		String whereDefault = "SELECT item FROM category";
		String where = " WHERE item = '"+pItem+"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			
			if(rs.next()) {	
				chkResult = true;
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return chkResult;
	}
	
	// 상품분류 등록.
	public boolean insertItemAction(String pItem) {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			String A = "INSERT INTO category (item)";
			String B = "values (?)";
			
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, pItem);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//						e.printStackTrace();
			return false;
		}
		return true;
	}
	// 상품분류 수정.
	public boolean updateItemAction(String inputItem, String pItem) {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			String A = "UPDATE category SET item = ? ";
			String B = " WHERE item = ?";
			
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, inputItem);
			ps.setString(2, pItem);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
//							e.printStackTrace();
			return false;
		}
		return true;
	}//updateItemAction
	
	// 상품분류가 있는지 체크 
	public boolean chkExistRegisterAction(String pItem) {
		boolean chkResult = false;
		String whereDefault = "SELECT item FROM register";
		String where = " WHERE item = '"+pItem+"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			
			if(rs.next()) {	
				chkResult = true;
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return chkResult;
	}
		
	// 상품분류 삭제.
	public boolean deleteItemAction(String pItem) {
		PreparedStatement ps = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			String A = "DELETE FROM category";
			String B = " WHERE item = ?";
			
			
			ps = conn_mysql.prepareStatement(A+B);
			ps.setString(1, pItem);
			ps.executeUpdate();
			
			conn_mysql.close();
			
		}catch(Exception e) {
			return false;
		}
		return true;
	}//updateItemAction
	
}
