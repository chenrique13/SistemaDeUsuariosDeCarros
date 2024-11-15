package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class SaveUserDTO  implements Serializable{

	private static final long serialVersionUID = 97096322392488317L;

	@Schema(description = "Nome do usuário.", example = "Carlos")
	private String firstName;

	@Schema(description = "Sobrenome do usuário.", example = "Pereira")
	private String lastName;

	@Schema(description = "E-mail do usuário.", example = "c.henrique@gmail.com")
	private String email;
	
	@Schema(description = "Data de nascimento do usuário.", example = "2024-01-01")
	private Date birthday;
	
	@Schema(description = "Login do usuário.", example = "Chenrique")
	private String login;
	
	@Schema(description = "Senha do usuário.", example = "senha123")
	private String password;
	
	@Schema(description = "Telefone do usuário.", example = "81912345678")
	private String phone;

	@Schema(description = "Lista de carros do usuário", implementation = CarDTO.class)
	private List<CarDTO> cars;

	public SaveUserDTO() {
		
	}
	
	public SaveUserDTO(String firstName, String lastName, String email, Date birthday, String login, String password,
			String phone, List<CarDTO> cars) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public List<CarDTO> getCars() {
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
