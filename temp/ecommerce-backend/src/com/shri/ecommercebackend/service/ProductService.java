package com.shri.ecommercebackend.service;

import java.util.List;

import com.shri.ecommercebackend.dto.CategoryProductsDTO;
import com.shri.ecommercebackend.entity.Category;

public interface ProductService {

	public List<Category> getCategories();
	
	public CategoryProductsDTO getProductsByCategory(byte categoryId);

}
