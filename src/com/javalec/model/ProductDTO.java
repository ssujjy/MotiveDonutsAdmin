package com.javalec.model;

import java.io.FileInputStream;

public class ProductDTO {
	// Field
	String proname;
	int sellprice;
	String detail;
	String nutritional;
	String ingredient;
	String imagename;
	String item;
	
	// Constructor
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(String proname, int sellprice, String detail, String nutritional, String ingredient,
			String imagename, String item) { 	// proname, sellprice, detail, nutritional, ingredient, image, imagename, wItem
		super();
		this.proname = proname;
		this.sellprice = sellprice;
		this.detail = detail;
		this.nutritional = nutritional;
		this.ingredient = ingredient;
		this.imagename = imagename;
		this.item = item;
	}
	
	// Method
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public int getSellprice() {
		return sellprice;
	}

	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getNutritional() {
		return nutritional;
	}

	public void setNutritional(String nutritional) {
		this.nutritional = nutritional;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
}
