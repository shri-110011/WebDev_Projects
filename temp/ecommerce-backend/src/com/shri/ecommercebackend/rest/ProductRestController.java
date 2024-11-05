package com.shri.ecommercebackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shri.ecommercebackend.dto.CategoryProductsDTO;
import com.shri.ecommercebackend.entity.Category;
import com.shri.ecommercebackend.exception.CategoryNotFoundException;
import com.shri.ecommercebackend.exception.ErrorResponse;
import com.shri.ecommercebackend.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private ProductService productSerivce;
	
	@GetMapping(path = "/v1/categories")
	public List<Category> getCategories() {
		return productSerivce.getCategories();
	}
	
	@GetMapping(path = "/v1/categories/{categoryId}/products")
	public CategoryProductsDTO getProductsByCategory(@PathVariable byte categoryId) {
		
		if(categoryId <= 0) throw new CategoryNotFoundException("Category id: "+ categoryId + " not found!");
		return productSerivce.getProductsByCategory(categoryId);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(CategoryNotFoundException exc) {
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), 
				System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
		
	}

}
