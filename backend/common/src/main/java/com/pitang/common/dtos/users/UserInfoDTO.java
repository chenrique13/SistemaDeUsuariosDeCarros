package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe representando um usuário com informações específicas como data de
 * criação e último login.
 * 
 * @author Carlos Pereira
 */
public class UserInfoDTO implements Serializable {

	private static final long serialVersionUID = 6263165937218446463L;

	/**
	 * Nome do usuário.
	 */
	@Schema(description = "Nome do usuário.", example = "Carlos")
	private String firstName;

	/**
	 * Sobrenome do usuário.
	 */
	@Schema(description = "Sobrenome do usuário.", example = "Pereira")
	private String lastName;

	/**
	 * E-mail do usuário.
	 */
	@Schema(description = "E-mail do usuário.", example = "c.henrique@gmail.com")
	private String email;

	/**
	 * Data de nascimento do usuário.
	 */
	@Schema(description = "Data de nascimento do usuário.", example = "2024-01-01")
	private Date birthday;

	/**
	 * Login do usuário.
	 */
	@Schema(description = "Login do usuário.", example = "Chenrique")
	private String login;

	/**
	 * Telefone do usuário.
	 */
	@Schema(description = "Telefone do usuário.", example = "81912345678")
	private String phone;

	/**
	 * Lista de {@link CarDTO} do usuário.
	 */
	@Schema(description = "Lista de carros do usuário", implementation = CarDTO.class)
	private List<CarDTO> cars;

	/**
	 * A data que o usuário foi criado.
	 */
	@Schema(description = "Data de criação do usuário.", example = "2024-01-01")
	private LocalDateTime createdAt;

	/**
	 * Data que o usuário fez login antes do login atual.
	 */
	@Schema(description = "Data do último login do usuário.", example = "2024-01-01")
	private LocalDateTime lastLogin;

	/**
	 * Construtor padrão sem argumentos.
	 */
	public UserInfoDTO() {

	}

	/**
	 * Construtor com todos argumentos.
	 * 
	 * @param firstName Atributo que representa o Nome do usuário.
	 * @param lastName Atributo que representa o sobrenome do usuário.
	 * @param email Atributo que representa o e-mail do usuário.
	 * @param birthday Atributo que representa a data de nascimento do usuário.
	 * @param login Atributo que representa o login do usuário.
	 * @param phone Atributo que representa o telefone do usuário.
	 * @param cars Atributo que representa a lista de carros do usuário.
	 * @param createdAt Atributo que representa a data de criação do usuário.
	 * @param lastLogin Atributo que representa a data do último login do usuário.
	 */
	public UserInfoDTO(String firstName, String lastName, String email, Date birthday, String login, String phone,
			List<CarDTO> cars, LocalDateTime createdAt, LocalDateTime lastLogin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.phone = phone;
		this.cars = cars;
		this.createdAt = createdAt;
		this.lastLogin = lastLogin;
	}

	/**
	 * Obtem o nome do usuário.
	 * 
	 * @return O nome do usuário
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Obtem o sobrenome do usuário.
	 * 
	 * @return O sobrenome do usuário.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Obtem o e-mail do usuário.
	 * 
	 * @return O e-mail do usuário.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Obtem a data de nascimento do usuário.
	 * 
	 * @return A data de nascimento do usuário.
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Obtem o login do usuário.
	 * 
	 * @return o login do usuário.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Obtem o telefone do usuário.
	 * 
	 * @return O telefone do usuário.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Obtem a lista dos {@link CarDTO} do usuário.
	 * 
	 * @return Uma lista que contém {@link CarDTO} do usuário.
	 */
	public List<CarDTO> getCars() {
		return cars;
	}

	/**
	 * Obtem a data de criacao do usuário.
	 * 
	 * @return A data de criacao do usuário.
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Obtem a data do ultimo login do usuário.
	 * 
	 * @return A data do ultimo login do usuário.
	 */
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * Retorna uma representacao em texto do usuário.
	 * 
	 * @return Uma representacao em texto do usuário.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfoDTO [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", login=");
		builder.append(login);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", cars=");
		builder.append(cars);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append("]");
		return builder.toString();
	}

}
