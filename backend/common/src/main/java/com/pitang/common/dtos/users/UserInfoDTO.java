package com.pitang.common.dtos.users;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.pitang.common.dtos.cars.CarDTO;

public class UserInfoDTO  implements Serializable{

	private static final long serialVersionUID = 6263165937218446463L;
	
	private String firstName;

	private String lastName;

	private String email;

	private Date birthday;

	private String login;

	private String phone;

	private List<CarDTO> cars;

    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;
	
    public UserInfoDTO() {
    	
    }
    
	public UserInfoDTO(String firstName, String lastName, String email, Date birthday, String login, String password,
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
