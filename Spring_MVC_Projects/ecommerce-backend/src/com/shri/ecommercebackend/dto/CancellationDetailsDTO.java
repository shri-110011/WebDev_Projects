package com.shri.ecommercebackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CancellationDetailsDTO {

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
