package com.pitang.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Classe responsável por ser o exceção personalizado.
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -6350393475779405851L;

	private int errorCode;

	private HttpStatus httpStatus;

	/**
	 * Construtor padrão.
	 */
	public CustomException() {
		
	}
	
	/**
	 * Construtor com a mensagem, codigo e httpStatus da exceção.
	 */
	public CustomException(String message, int errorCode, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	/**
	 * Obtem o código de erro da exceção.
	 * @return int
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Obtem o httpStatus da exceção.
	 * @return int
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Retorna uma representação em JSON do ErroPadrao.
	 * 
	 * @return String
	 */
	public String toJSON() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append("  \"errorCode\": ").append(errorCode).append(",\n");
		builder.append("  \"message\": \"").append(this.getMessage()).append("\",\n");
		builder.append("}");
		return builder.toString();
	}

}