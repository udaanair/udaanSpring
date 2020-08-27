package com.lti.dto;

public class ResultOfBooking {

	private String status;
	private int PnrGenerated;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPnrGenerated() {
		return PnrGenerated;
	}
	public void setPnrGenerated(int pnrGenerated) {
		PnrGenerated = pnrGenerated;
	}
	
	
}
