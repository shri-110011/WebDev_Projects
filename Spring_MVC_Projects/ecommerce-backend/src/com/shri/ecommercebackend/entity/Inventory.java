package com.shri.ecommercebackend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private int inverntoryId;
	
	@Column(name = "product_id")
	private int productId;

	@Column(name = "change_type")
	private String changeType;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "status_change_datetime")
	private LocalDateTime statusChangeDatetime;
	
	@Column(name = "adjusted_by")
	private String adjustedBy;
	
	public Inventory() {
		
	}

	public Inventory(int productId, ChangeType changeType, int quantity, String reason, 
			InventoryStatus status, LocalDateTime statusChangeDatetime, String adjustedBy) {
		this.productId = productId;
		this.changeType = changeType.name();
		this.quantity = quantity;
		this.reason = reason;
		this.status = status.name();
		this.statusChangeDatetime = statusChangeDatetime;
		this.adjustedBy = adjustedBy;
	}
	
	public int getInverntoryId() {
		return inverntoryId;
	}

	public void setInverntoryId(int inverntoryId) {
		this.inverntoryId = inverntoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ChangeType getChangeType() {
		return ChangeType.valueOf(changeType);
	}

	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType.name();
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

	public InventoryStatus getStatus() {
		return InventoryStatus.valueOf(status);
	}

	public void setStatus(InventoryStatus status) {
		this.status = status.name();
	}

	public LocalDateTime getStatusChangeDatetime() {
		return statusChangeDatetime;
	}

	public void setStatusChangeDatetime(LocalDateTime statusChangeDatetime) {
		this.statusChangeDatetime = statusChangeDatetime;
	}

	public String getAdjustedBy() {
		return adjustedBy;
	}

	public void setAdjustedBy(String adjustedBy) {
		this.adjustedBy = adjustedBy;
	}

	@Override
	public String toString() {
		return "Inventory [inverntoryId=" + inverntoryId + ", productId=" + productId + ", changeType=" + changeType
				+ ", quantity=" + quantity + ", reason=" + reason + ", status=" + status + ", statusChangeDatetime="
				+ statusChangeDatetime + ", adjustedBy=" + adjustedBy + "]";
	}
	
}
