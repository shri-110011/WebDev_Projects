package com.shri.ecommercebackend.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReturnItemKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "return_id")
	private int returnId;
	
	@Column(name = "inventory_event_id")
	private int inventoryId;
	
	public ReturnItemKey() {
		
	}

	public ReturnItemKey(int returnId, int inventoryId) {
		this.returnId = returnId;
		this.inventoryId = inventoryId;
	}

	public int getReturnId() {
		return returnId;
	}

	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId, returnId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnItemKey other = (ReturnItemKey) obj;
		return inventoryId == other.inventoryId && returnId == other.returnId;
	}

	@Override
	public String toString() {
		return "ReturnItemKey [returnId=" + returnId + ", inventoryId=" + inventoryId + "]";
	}
	
}
