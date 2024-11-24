package com.shri.ecommercebackend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.InventoryOrder;
import com.shri.ecommercebackend.entity.Reservation;
import com.shri.ecommercebackend.entity.ReservationEntityStatus;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int getReservationId(int userId, List<Integer> inventoryIds) {
		Reservation reservation = new Reservation(userId, ReservationEntityStatus.ACTIVE);
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(reservation);
		System.out.println(reservation);
		int reservationId = reservation.getReservationId();
		
		for (int inventoryId : inventoryIds) {
	        InventoryOrder inventoryOrder = new InventoryOrder();
	        inventoryOrder.setInventoryId(inventoryId);
	        inventoryOrder.setReservation(reservation);
	        
	        System.out.println(inventoryOrder);
	        currentSession.save(inventoryOrder);

	        // Synchronize the relationship
	        reservation.addInventoryOrder(inventoryOrder);
	    }
		
		return reservationId;
	}
	
}
