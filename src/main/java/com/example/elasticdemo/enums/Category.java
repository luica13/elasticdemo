package com.example.elasticdemo.enums;

public enum Category {
	BOOKS("Books"), HOME("Home"), CLOTHING("Clothing and Accessories"), PARTY("Party Supplies");

	private String category;

	private Category(String category) {
		this.category = category;
	}

	
}
