package com.pitang.common.dtos.authentication;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe resonsável por ser o modelo de dados no login de um Usuário.
 *
 * @author Carlos Pereira
 */
@Schema(description = "DTO que representa o modelo de dados do signin para autenticação no sistema.")
public class SigninDTO {

	/**
	 * Login do Usuário.
	 */
	@Schema(description = "Login do usuário.", example = "Carlos")
	private String login;

	/**
	 * Senha do Usuário.
	 */
	@Schema(description = "Senha do usuário.", example = "Senha123")
	private String password;

	/**
	 * Construtor padrão.
	 */
	public SigninDTO() {

	}

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param login
	 * @param password
	 */
	public SigninDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * Obtém o login do signinDTO.
	 *
	 * @return String
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Obtém a senha do signinDTO.
	 *
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SigninDTO [login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}
}
