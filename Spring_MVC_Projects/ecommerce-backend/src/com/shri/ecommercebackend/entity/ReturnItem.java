package com.shri.ecommercebackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "return_items")
public class ReturnItem {
	
	@Id
	@Column(name = "inventory_event_id")
	private int inventoryEventId;
	
	@Column(name = "return_id")
	private int returnId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "reason")
	private String reason;

	public ReturnItem() {
		
	}
	
	public ReturnItem(int inventoryEventId, int returnId, int productId, int quantity, String reason) {
		this.inventoryEventId = inventoryEventId;
		this.returnId = returnId;
		this.productId = productId;
		this.quantity = quantity;
		this.reason = reason;
	}
	
	public int getInventoryEventId() {
		return inventoryEventId;
	}

	public void setInventoryEventId(int inventoryEventId) {
		this.inventoryEventId = inventoryEventId;
	}

	public int getReturnId() {
		return returnId;
	}

	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ReturnItem [inventoryEventId=" + inventoryEventId + ", returnId=" + returnId + 
				", productId=" + productId + ", quantity=" + quantity + ", reason=" + reason + "]";
	}
	
}
