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
import com.shri.ecommercebackend.dto.ProductInventoryDTO;
import com.shri.ecommercebackend.entity.Category;
import com.shri.ecommercebackend.exception.CategoryNotFoundException;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> getCategories() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Category> query = currentSession.createQuery("from Category order by categoryName", Category.class);

		List<Category> categories = query.getResultList();

		return categories;

	}

	@Override
	public CategoryProductsDTO getProductsByCategory(byte categoryId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Category category = currentSession.get(Category.class, categoryId);

		System.out.println("Requested Category: " + category);

		if (category == null)
			throw new CategoryNotFoundException("Category id: " + categoryId + " not found!");

		System.out.println(category.getProducts());

		List<ProductDTO> productDTOs = category.getProducts().stream().map(product -> new ProductDTO(product))
				.collect(Collectors.toList());

		return new CategoryProductsDTO(category.getCategoryId(), category.getCategoryName(), productDTOs);

	}

	@Override
	public List<ProductInventoryDTO> getProductsInventoryInfo(List<Integer> productIds) {
		if(productIds.size() == 0) return List.of();
				
		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProductInventoryDTO> query = currentSession.createQuery(
				"select new com.shri.ecommercebackend.dto.ProductInventoryDTO(p.productId, "
						+ "p.availableQuantity, p.price) from Product p " + "where p.productId in :productIds",
				ProductInventoryDTO.class);
		query.setParameter("productIds", productIds);

		return query.getResultList();
	}

}
