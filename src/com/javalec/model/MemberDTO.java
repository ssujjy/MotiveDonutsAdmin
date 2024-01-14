package com.javalec.model;

public class MemberDTO {
	// Field
	String custid, custpw, custname, phone, birthday, question1, answer1, question2, answer2, joinactive, modidate, deactive;

	
	// Constructor
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	//custid, custpw, custname, phone, joinactive, deactive
	public MemberDTO(String custid, String custpw, String custname, String phone, String joinactive, String deactive) {
		super();
		this.custid = custid;
		this.custpw = custpw;
		this.custname = custname;
		this.phone = phone;
		this.joinactive = joinactive;
		this.deactive = deactive;
	}

	// Method
	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}


	public String getCustpw() {
		return custpw;
	}


	public void setCustpw(String custpw) {
		this.custpw = custpw;
	}


	public String getCustname() {
		return custname;
	}


	public void setCustname(String custname) {
		this.custname = custname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getQuestion1() {
		return question1;
	}


	public void setQuestion1(String question1) {
		this.question1 = question1;
	}


	public String getAnswer1() {
		return answer1;
	}


	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}


	public String getQuestion2() {
		return question2;
	}


	public void setQuestion2(String question2) {
		this.question2 = question2;
	}


	public String getAnswer2() {
		return answer2;
	}


	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}


	public String getJoinactive() {
		return joinactive;
	}


	public void setJoinactive(String joinactive) {
		this.joinactive = joinactive;
	}


	public String getModidate() {
		return modidate;
	}


	public void setModidate(String modidate) {
		this.modidate = modidate;
	}


	public String getDeactive() {
		return deactive;
	}


	public void setDeactive(String deactive) {
		this.deactive = deactive;
	}
	
	
}
