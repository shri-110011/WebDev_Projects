package com.shri.ecommercebackend.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "return_items")
public class ReturnItem {
	
	@EmbeddedId
	private ReturnItemKey returnItemKey;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "reason")
	private String reason;

	public ReturnItem() {
		
	}

	public ReturnItem(int returnId, int inventoryEventId, int productId, int quantity, String reason) {
		this.returnItemKey = new ReturnItemKey(returnId, inventoryEventId);
		this.productId = productId;
		this.quantity = quantity;
		this.reason = reason;
	}

	public ReturnItemKey getReturnItemKey() {
		return returnItemKey;
	}

	public void setReturnItemKey(ReturnItemKey returnItemKey) {
		this.returnItemKey = returnItemKey;
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
		return "ReturnItem [returnItemKey=" + returnItemKey + ", productId=" + productId + ", quantity=" + quantity
				+ ", reason=" + reason + "]";
	}
	
}
