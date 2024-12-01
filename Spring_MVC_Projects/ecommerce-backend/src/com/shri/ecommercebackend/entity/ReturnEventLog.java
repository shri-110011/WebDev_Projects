package com.shri.ecommercebackend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "return_events_log")
public class ReturnEventLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "return_id")
	private int returnId;
	
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "creation_datetime", insertable = false, updatable = false)
	private LocalDateTime creationDatetime;
		
	@Column(name = "status")
	private String status;
	
	@Column(name = "status_change_datetime")
	private LocalDateTime statusChangeDatetime;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "return_id")
	private List<ReturnItem> returnItems;
	
	public ReturnEventLog() {
		
	}

	public int getReturnId() {
		return returnId;
	}

	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreationDatetime() {
		return creationDatetime;
	}

	public void setCreationDatetime(LocalDateTime creationDatetime) {
		this.creationDatetime = creationDatetime;
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
	
	public void addReturnItem(ReturnItem returnItem) {
		if (returnItems == null) {
			returnItems = new ArrayList<>();
		}
		returnItems.add(returnItem);
	}

	@Override
	public String toString() {
		return "ReturnEventLog [returnId=" + returnId + ", orderId=" + orderId + ", creationDatetime="
				+ creationDatetime + ", status=" + status + ", statusChangeDatetime=" + statusChangeDatetime + "]";
	}
	
}
