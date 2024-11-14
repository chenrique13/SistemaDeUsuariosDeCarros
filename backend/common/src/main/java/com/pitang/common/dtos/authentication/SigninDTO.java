package com.pitang.common.dtos.authentication;

/**
 * Classe resonsável por ser o modelo de dados no login de um Usuário.
 *
 * @author Carlos Pereira
 */
public class SigninDTO {

	/**
	 * Login do Usuário.
	 */
	private String login;

	/**
	 * Senha do Usuário.
	 */
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
