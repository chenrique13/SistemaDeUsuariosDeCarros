package com.pitang.common.dtos.common;

import java.io.Serializable;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = -8152207975108400187L;

	private String message;
	
	private int errorCode;

	public ErrorDTO() {
		
	}
	
	public ErrorDTO(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorDTO [message=");
		builder.append(message);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append("]");
		return builder.toString();
	}
	
}
