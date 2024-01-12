package com.javalec.model;

public class StockDTO {
	// Field
	int stocknum;
	int stonum;
	String cateitem;
	String proname;
	int sellqty;
	int stockqty;
	int likes;
	String selldate;
	
	// Constructor
	public StockDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public StockDTO(int stocknum, int stonum, String cateitem, String proname, int sellqty, int stockqty, int likes,
			String selldate) {
		super();
		this.stocknum = stocknum;
		this.stonum = stonum;
		this.cateitem = cateitem;
		this.proname = proname;
		this.sellqty = sellqty;
		this.stockqty = stockqty;
		this.likes = likes;
		this.selldate = selldate;
	}

	// Method
	public int getStocknum() {
		return stocknum;
	}

	public void setStocknum(int stocknum) {
		this.stocknum = stocknum;
	}

	public int getStonum() {
		return stonum;
	}

	public void setStonum(int stonum) {
		this.stonum = stonum;
	}

	public String getCateitem() {
		return cateitem;
	}

	public void setCateitem(String cateitem) {
		this.cateitem = cateitem;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public int getSellqty() {
		return sellqty;
	}

	public void setSellqty(int sellqty) {
		this.sellqty = sellqty;
	}

	public int getStockqty() {
		return stockqty;
	}

	public void setStockqty(int stockqty) {
		this.stockqty = stockqty;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getSelldate() {
		return selldate;
	}

	public void setSelldate(String selldate) {
		this.selldate = selldate;
	}
	
}
