package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDTO  implements Serializable{

	private static final long serialVersionUID = 6263165937218446463L;

	@Schema(description = "Id do usuário.", example = "1")
	private Long id;
	
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
	
	public UserDTO() {
		
	}
	
	public UserDTO(Long id, String firstName, String lastName, String email, Date birthday, String login, String password,
			String phone, List<CarDTO> cars) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.cars = cars;
	}

	public Long getId() {
		return id;
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
		builder.append("UserDTO [id=");
		builder.append(id);
		builder.append(", firstName=");
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
