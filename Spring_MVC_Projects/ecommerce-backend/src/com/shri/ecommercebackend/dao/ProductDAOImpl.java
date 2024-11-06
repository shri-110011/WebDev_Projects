package com.shri.ecommercebackend.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.dto.CategoryProductsDTO;
import com.shri.ecommercebackend.dto.ProductDTO;
import com.shri.ecommercebackend.dto.ProductIdQuantityDTO;
import com.shri.ecommercebackend.entity.Category;
import com.shri.ecommercebackend.exception.CategoryNotFoundException;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Category> query = currentSession.createQuery("from Category order by categoryName", 
															Category.class);

		List<Category> categories = query.getResultList();

		return categories;

	}

	@Override
	public CategoryProductsDTO getProductsByCategory(byte categoryId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Category category = currentSession.get(Category.class, categoryId);
		
		System.out.println("Requested Category: " + category);
		
		if(category == null) throw new CategoryNotFoundException("Category id: "+ categoryId + " not found!");
		
		System.out.println(category.getProducts());
		
		List<ProductDTO> productDTOs = category.getProducts()
		.stream()
		.map(product -> new ProductDTO(product.getProductId(), product.getProductName(), product.getPrice(), 
				product.getAvailableQuantity())).collect(Collectors.toList());
		
		return new CategoryProductsDTO(category.getCategoryId(), category.getCategoryName(), productDTOs);
	
	}

	@Override
	public List<ProductIdQuantityDTO> getProductsIdAndQuantity() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProductIdQuantityDTO> query = currentSession
				.createQuery("select new com.shri.ecommercebackend.dto.ProductIdQuantityDTO(p.productId, availableQuantity)" + 
				"from Product p", 
				ProductIdQuantityDTO.class);
		
		return query.getResultList();
	}

}
