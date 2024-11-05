package com.shri.ecommercebackend.dto;

import java.util.List;

public class CategoryProductsDTO {
	
	private int categoryId;
	
	private String categoryName;
	
	private List<ProductDTO> products;

	public CategoryProductsDTO(int categoryId, String categoryName, List<ProductDTO> products) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = products;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

}
