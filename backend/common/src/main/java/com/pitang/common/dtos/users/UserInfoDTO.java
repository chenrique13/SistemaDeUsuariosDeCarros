package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserInfoDTO  implements Serializable{

	private static final long serialVersionUID = 6263165937218446463L;
	
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
	
	@Schema(description = "Telefone do usuário.", example = "81912345678")
	private String phone;

	@Schema(description = "Lista de carros do usuário", implementation = CarDTO.class)
	private List<CarDTO> cars;

	@Schema(description = "Data de criação do usuário.", example = "2024-01-01")
    private LocalDateTime createdAt;

	@Schema(description = "Data do último login do usuário.", example = "2024-01-01")
    private LocalDateTime lastLogin;
	
    public UserInfoDTO() {
    	
    }
    
	public UserInfoDTO(String firstName, String lastName, String email, Date birthday, String login,
			String phone, List<CarDTO> cars, LocalDateTime createdAt, LocalDateTime lastLogin) {
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

	public String getPhone() {
		return phone;
	}

	public List<CarDTO> getCars() {
		return cars;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

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
