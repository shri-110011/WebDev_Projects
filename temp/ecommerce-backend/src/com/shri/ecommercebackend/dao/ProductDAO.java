package com.shri.ecommercebackend.dao;

import java.util.List;

import com.shri.ecommercebackend.dto.CategoryProductsDTO;
import com.shri.ecommercebackend.entity.Category;

public interface ProductDAO {

	public List<Category> getCategories();
	
	public CategoryProductsDTO getProductsByCategory(byte categoryId);

}
