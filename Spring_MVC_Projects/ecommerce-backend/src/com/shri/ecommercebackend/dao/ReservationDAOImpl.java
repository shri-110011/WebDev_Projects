package com.shri.ecommercebackend.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shri.ecommercebackend.entity.Reservation;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int getReservationId(Reservation reservation) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(reservation);
		return reservation.getReservationId();
	}
	
}
