package com.shri.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.springdemo.entity.Customer;

/* This @Repository annotation can be added only over a DAO implementation.
 * And this annotation does two things:
 * 1. It will automatically register this dao implementation.
 * 2. It will translate the JDBC related checked exceptions into 
 * unchecked exceptions.
 * 
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	/* @Transactional annotation helps us to make use of the transaction
	 * management feature provided by spring, i.e. we don't have to explicitly
	 * begin and end transaction as we used to do in standalone hibernate 
	 * code.*/
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> query = session.createQuery("from Customer order by lastName", 
				Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		/* If we use session.save(theCustomer); then we won't be able
		 * to save the updates made on an existing customer i.e. why we are
		 * using session.saveOrUpdate(theCustomer); because hibernate will
		 * behind the scene check if this customer already exist or is a new
		 * one based on the primary key of the Customer entity. 
		 * 
		 */
		session.saveOrUpdate(theCustomer);
		
		System.out.println("Customer saved to the database!");
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// retrieve the customer from the database using primary key
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Customer where id = :customerId");
		query.setParameter("customerId", theId);
		
		// delete the customer from the database using primary key
		query.executeUpdate();
	}

}
