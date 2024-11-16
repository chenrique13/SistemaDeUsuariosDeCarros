package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe representando um usuário na inserção do sistema de usuários de carros.
 * 
 * @author Carlos Pereira
 */
public class SaveUserDTO implements Serializable {

	private static final long serialVersionUID = 97096322392488317L;

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
	 * Senha do usuário.
	 */
	@Schema(description = "Senha do usuário.", example = "senha123")
	private String password;

	/**
	 * Telefone do usuário.
	 */
	@Schema(description = "Telefone do usuário.", example = "81912345678")
	private String phone;

	/**
	 * Lista de {@link CarDTO} do usuário.
	 */

	@Schema(description = "Lista de carros do usuário", implementation = SaveUpdateCarDTO.class)
	private List<SaveUpdateCarDTO> cars;

	/**
	 * Construtor padrão sem argumentos.
	 */
	public SaveUserDTO() {

	}

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param firstName Atributo que representa o Nome do usuário.usuário.
	 * @param lastName Atributo que representa o Sobrenome do usuário.
	 * @param email Atributo que representa o e-mail do usuário.
	 * @param birthday Atributo que representa a data de nascimento do usuário.
	 * @param login Atributo que representa o login do usuário.
	 * @param password Atributo que representa a senha do usuário.
	 * @param phone Atributo que representa o telefone do usuário.
	 * @param cars Atributo que representa a lista de carros do usuário.
	 */
	public SaveUserDTO(String firstName, String lastName, String email, Date birthday, String login, String password,
			String phone, List<SaveUpdateCarDTO> cars) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
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
	 * Obtem a senha do usuário.
	 * 
	 * @return A senha do usuário.
	 */
	public String getPassword() {
		return password;
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
	 * Obtem a lista dos {@link SaveUpdateCarDTO} do usuário.
	 * 
	 * @return Uma lista que contém {@link SaveUpdateCarDTO} do usuário.
	 */
	public List<SaveUpdateCarDTO> getCars() {
		return cars;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveUserDTO [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", cars=");
		builder.append(cars);
		builder.append("]");
		return builder.toString();
	}

}
