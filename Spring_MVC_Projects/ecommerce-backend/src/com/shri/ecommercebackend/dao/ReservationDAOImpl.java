package com.shri.ecommercebackend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.InventoryEventLog;
import com.shri.ecommercebackend.entity.InventoryEventOrderReservationLink;
import com.shri.ecommercebackend.entity.Reservation;
import com.shri.ecommercebackend.entity.ReservationEntityStatus;
import com.shri.ecommercebackend.validation.CartItem;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int getReservationId(int userId, List<CartItem> validatedCartItems, 
			List<InventoryEventLog> inventoryEventLogs) {
		Reservation reservation = new Reservation(userId, ReservationEntityStatus.ACTIVE);
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(reservation);
		System.out.println(reservation);
		int reservationId = reservation.getReservationId(), i = 0;
		
		
		for (CartItem cartItem : validatedCartItems) {
			InventoryEventLog inventoryEventLog = inventoryEventLogs.get(i);
	        InventoryEventOrderReservationLink inventoryEventOrderReservationLink = 
	        		new InventoryEventOrderReservationLink();
	        inventoryEventOrderReservationLink.setInventoryEventLog(inventoryEventLog);
	        inventoryEventOrderReservationLink.setReservation(reservation);
	        inventoryEventOrderReservationLink.setProductId(cartItem.getProductId());
	        inventoryEventOrderReservationLink.setQuantity(cartItem.getQuantity());
	        inventoryEventOrderReservationLink.setPriceAtPurchase(cartItem.getPricePerUnit());
	        
	        System.out.println(inventoryEventOrderReservationLink);

	        // Synchronize the relationship
	        reservation.addInventoryEventOrderReservationLink(inventoryEventOrderReservationLink);
	        
	        i++;
	    }
		
		return reservationId;
	}
	
}
