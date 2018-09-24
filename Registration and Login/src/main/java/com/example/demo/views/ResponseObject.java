package com.example.demo.views;

public class ResponseObject {
	private int status;
	private String statusMessage;
	
	public ResponseObject(int status, String statusMessage) {
		this.status = status;
		this.statusMessage = statusMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
