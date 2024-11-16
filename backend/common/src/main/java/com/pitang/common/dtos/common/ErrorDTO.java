package com.pitang.common.dtos.common;

import java.io.Serializable;

/**
 * Classe DTO que representa a mensagem de erro que será exibida na tela.
 */
public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = -8152207975108400187L;

	/**
	 * Mensagem de erro.
	 */
	private String message;
	
	/**
	 * Código do erro.
	 */
	private int errorCode;

	/**
	 * Construtor padrão sem argumentos.
	 */
	public ErrorDTO() {
		
	}
	
	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param message mensasgem do erro.
	 * @param errorCode código do erro.
	 */
	public ErrorDTO(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	/**
	 * Obtém a mensagem do erro.
	 * 
	 * @return a mensagem do erro.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Obtém a código do erro.
	 * 
	 * @return a código do erro.
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Retorna uma representacao em texto do erro.
	 * 
	 * @return String A representacao em texto do erro.
	 */
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
