package com.shri.ecommercebackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shri.ecommercebackend.dao.ProductDAO;
import com.shri.ecommercebackend.dto.CategoryProductsDTO;
import com.shri.ecommercebackend.entity.Category;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	@Transactional
	public List<Category> getCategories() {
		return productDAO.getCategories();
	}

	@Override
	@Transactional
	public CategoryProductsDTO getProductsByCategory(byte categoryId) {
		return productDAO.getProductsByCategory(categoryId);
	}

}
