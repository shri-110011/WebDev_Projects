package com.shri.ecommercebackend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "return_events_log")
public class ReturnEventLog {
	
	@Id
	@Column(name = "inventory_event_id")
	private int inventoryEventId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "status_change_datetime")
	private LocalDateTime statusChangeDatetime;
	
	public ReturnEventLog() {
		
	}

	public ReturnEventLog(int inventoryEventId, int quantity, String reason, String status,
			LocalDateTime statusChangeDatetime) {
		this.inventoryEventId = inventoryEventId;
		this.quantity = quantity;
		this.reason = reason;
		this.status = status;
		this.statusChangeDatetime = statusChangeDatetime;
	}

	public int getInventoryEventId() {
		return inventoryEventId;
	}

	public void setInventoryEventId(int inventoryEventId) {
		this.inventoryEventId = inventoryEventId;
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

	public ReturnStatus getStatus() {
		return ReturnStatus.valueOf(status);
	}

	public void setStatus(ReturnStatus status) {
		this.status = status.toString();
	}

	public LocalDateTime getStatusChangeDatetime() {
		return statusChangeDatetime;
	}

	public void setStatusChangeDatetime(LocalDateTime statusChangeDatetime) {
		this.statusChangeDatetime = statusChangeDatetime;
	}

	@Override
	public String toString() {
		return "ReturnEventLog [inventoryEventId=" + inventoryEventId + ", quantity=" + quantity + ", reason=" + reason
				+ ", status=" + status + ", statusChangeDatetime=" + statusChangeDatetime + "]";
	}
	
}
