package com.pitang.user.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

/**
 * Classe representando um usuario do sistema de usuarios de carros.
 * 
 * @author Carlos Pereira
 */
@Entity
@Table(name = "users" )
public class User implements Serializable {

	private static final long serialVersionUID = -6154520053495083870L;

	/**
	 * Identificador unico do Usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	/**
	 * Nome do usuario.
	 */
	@Column
	private String firstName;

	/**
	 * Sobrenome do usuario.
	 */
	@Column
	private String lastName;

	/**
	 * E-mail do usuario
	 */
	@Column
	private String email;

	/**
	 * Data de nascimento do usuario
	 */
	@Column
	private Date birthday;

	/**
	 * Login do usuario
	 */
	@Column
	private String login;

	/**
	 * Senha do usuario
	 */
	@Column
	private String password;

	/**
	 * Telefone do usuario
	 */
	@Column
	private String phone;

	/**
	 * Lista de ids dos carros do usuario
	 */
    @ElementCollection
    @CollectionTable(name = "user_car", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "car_id")
	private List<Long> cars;

    /**
     * A data que o usuario foi criado.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    /**
     * Data que o usuario fez login antes do login atual.
     */
    private LocalDateTime lastLogin;
    
    /**
     * Data do login atual do usuario.
     */
    private LocalDateTime currentLogin;
    
	/**
	 * Construtor padrão sem argumentos.
	 */
	public User() {

	}

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param birthday
	 * @param login
	 * @param password
	 * @param phone
	 * @param cars
	 */
	public User(Long id, String firstName, String lastName, String email, Date birthday, String login, String password,
			String phone, List<Long> cars) {
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
	
	/**
	 * Construtor com todos os argumentos sem o id.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param birthday
	 * @param login
	 * @param password
	 * @param phone
	 * @param cars
	 */
	public User(String firstName, String lastName, String email, Date birthday, String login, String password,
			String phone, List<Long> cars) {
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
	 * Obtem o id do usuario.
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id do usuario.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtem o nome do usuario.
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Define o nome do usuario.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Obtem o sobrenome do usuario.
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Define o sobrenome do usuario.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Obtem o email do usuario.
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o email do usuario.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtem a data de nascimento do usuario.
	 * 
	 * @return Date
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Define a data de nascimento do usuario.
	 * 
	 * @param birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Obtem o login do usuario.
	 * 
	 * @return String
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Define o login do usuario.
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Obtem a senha do usuario.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usuario.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtem o telefone do usuario.
	 * 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Define o telefone do usuario.
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Obtem a lista dos ids dos carros do usuario.
	 * 
	 * @return List<Long>
	 */
	public List<Long> getCars() {
		return cars;
	}

	/**
	 * Define  a lista de carros do usuario.
	 * 
	 * @param cars
	 */
	public void setCars(List<Long> cars) {
		this.cars = cars;
	}

	/**
	 * Obtem a data de criacao do usuario.
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Define a data de criacao do usuario.
	 * 
	 * @param createdAt
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Obtem a data do ultimo login do usuario.
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * Define a data do ultimo login do usuario.
	 * 
	 * @param createdAt
	 */
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Obtem a data do login atual do usuario.
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getCurrentLogin() {
		return currentLogin;
	}

	/**
	 * Define a data do login atual do usuario.
	 * 
	 * @param currentLogin
	 */
	public void setCurrentLogin(LocalDateTime currentLogin) {
		this.currentLogin = currentLogin;
	}

	/**
	 * Valida o usuario.
	 * 
	 * @return boolean
	 */
    public boolean isValid() {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               birthday != null &&
               login != null && !login.trim().isEmpty() &&
               password != null && !password.trim().isEmpty() &&
               phone != null && !phone.trim().isEmpty();
    }
	
	/**
	 * Gera um hash de um usuario a partir do seu id, email e login.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se um usuario e igual ao outro pelo id, email e login.
	 * 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Retorna uma representacao em texto do usuario.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
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
