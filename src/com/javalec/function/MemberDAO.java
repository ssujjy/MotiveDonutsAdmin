package com.javalec.function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.common.ShareVar;
import com.javalec.model.MemberDTO;
import com.javalec.model.ProductDTO;

public class MemberDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	String custid, custpw, custname, phone, birthday, question1, answer1, question2, answer2, joinactive, modidate, deactive;
	FileInputStream image;
	
	// Constructor
	public MemberDAO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDAO(String custid, String custpw, String custname, String phone, String birthday, String question1,
			String answer1, String question2, String answer2, String joinactive, String modidate, String deactive,
			FileInputStream image) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.birthday = birthday;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.joinactive = joinactive;
		this.modidate = modidate;
		this.deactive = deactive;
		this.image = image;
	}
	
	
	// Method
	public ArrayList<MemberDTO> searchMemberAction(String col, String val){
		
		ArrayList<MemberDTO> dtoList = new ArrayList<MemberDTO>();
		String whereDefault = "SELECT custid, custpw, custname, phone, joinactive, deactive, image"
				+ " FROM customer ";
		String where = "";
		if(col.equals("회원ID")) {
			where = " WHERE custid like '%"+val+"%'";
		}else if(col.equals("회원명")) {
			where = " WHERE custname like '%"+val+"%'";
		}else if(col.equals("전화번호")) {
			where = " WHERE phone like '%"+val+"%'";
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
//			System.out.println(whereDefault+where);
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			while(rs.next()) {	
				String custid = rs.getString(1);
				String custpw = rs.getString(2);
				String custname = rs.getString(3);
				String phone = rs.getString(4);
				String joinactive = rs.getString(5);
				String deactive = rs.getString(6);
				
				// File  그림 파일을 하나만들어준다.
				File file = new File(custid);
				FileOutputStream output = new FileOutputStream(file);
				InputStream image = rs.getBinaryStream(7);
				byte[] buffer = new byte[1024];
				while(image.read(buffer) > 0 ) {
					output.write(buffer);
				}
				
				MemberDTO memberDto = new MemberDTO(custid, custpw, custname, phone, joinactive, deactive);
				dtoList.add(memberDto);
				
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return dtoList;
	}	// End of selectProductListByItem
}
