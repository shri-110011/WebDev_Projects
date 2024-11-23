package com.shri.ecommercebackend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_orders")
public class InventoryOrder {

	private Inventory inventory;
	
	private Reservation reservation;
	
}
