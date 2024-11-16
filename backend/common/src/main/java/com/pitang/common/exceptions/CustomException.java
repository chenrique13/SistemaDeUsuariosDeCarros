package com.pitang.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Classe responsável por ser o exceção personalizado.
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -6350393475779405851L;

	/**
	 * Código do erro.
	 */
	private int errorCode;

	/**
	 * Status http do erro.
	 */
	private HttpStatus httpStatus;

	/**
	 * Construtor padrão.
	 */
	public CustomException() {
		
	}
	
	/**
	 * Construtor com a mensagem, codigo e httpStatus da exceção.
	 *
	 * @param message Mensagem do erro.
	 * @param errorCode Código do erro.
	 * @param httpStatus Status http do erro.
	 */
	public CustomException(String message, int errorCode, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	/**
	 * Obtem o código de erro da exceção.
	 * @return int código de erro da exceção
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Obtem o httpStatus da exceção.
	 * @return {@link HttpStatus} httpStatus da exceção
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