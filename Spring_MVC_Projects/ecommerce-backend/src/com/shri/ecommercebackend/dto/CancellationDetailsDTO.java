package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CancellationDetailsDTO {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime cancelledAt;
	
	private BigDecimal refundAmount;
	
	public CancellationDetailsDTO(LocalDateTime cancelledAt, BigDecimal refundAmount) {
		this.cancelledAt = cancelledAt;
		this.refundAmount = refundAmount;
	}

	public LocalDateTime getCancelledAt() {
		return cancelledAt;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	@Override
	public String toString() {
		return "CancellationDetailsDTO [cancelledAt=" + cancelledAt + ", refundAmount=" + refundAmount + "]";
	}
		
}
