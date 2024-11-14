package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

public class UserDTO  implements Serializable{

	private static final long serialVersionUID = 6263165937218446463L;

	private Long id;
	
	private String firstName;

	private String lastName;

	private String email;

	private Date birthday;

	private String login;

	private String password;

	private String phone;

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
