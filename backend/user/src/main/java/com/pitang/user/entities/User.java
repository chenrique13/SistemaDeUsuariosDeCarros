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
 * Classe representando um usuário do sistema de usuários de carros.
 * 
 * @author Carlos Pereira
 */
@Entity
@Table(name = "users" )
public class User implements Serializable {

	private static final long serialVersionUID = -6154520053495083870L;

	/**
	 * Identificador unico do usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	/**
	 * Nome do usuário.
	 */
	@Column
	private String firstName;

	/**
	 * Sobrenome do usuário.
	 */
	@Column
	private String lastName;

	/**
	 * E-mail do usuário.
	 */
	@Column
	private String email;

	/**
	 * Data de nascimento do usuário.
	 */
	@Column
	private Date birthday;

	/**
	 * Login do usuário.
	 */
	@Column
	private String login;

	/**
	 * Senha do usuário.
	 */
	@Column
	private String password;

	/**
	 * Telefone do usuário.
	 */
	@Column
	private String phone;

	/**
	 * Lista de ids dos carros do usuário.
	 */
    @ElementCollection
    @CollectionTable(name = "user_car", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "car_id")
	private List<Long> cars;

    /**
     * A data que o usuário foi criado.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    /**
     * Data que o usuário fez login antes do login atual.
     */
    private LocalDateTime lastLogin;
    
    /**
     * Data do login atual do usuário.
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
	 * @param id Atributo que representa o identificador do usuário.
	 * @param firstName Atributo que representa o Nome do usuário.usuário.
	 * @param lastName Atributo que representa o Sobrenome do usuário.
	 * @param email Atributo que representa o e-mail do usuário.
	 * @param birthday Atributo que representa a data de nascimento do usuário.
	 * @param login Atributo que representa o login do usuário.
	 * @param password Atributo que representa a senha do usuário.
	 * @param phone Atributo que representa o telefone do usuário.
	 * @param cars Atributo que representa a lista de ids de carros do usuário.
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
	 * @param firstName Atributo que representa o nome do usuário.
	 * @param lastName Atributo que representa o sobrenome do usuário.
	 * @param email Atributo que representa o e-mail do usuário.
	 * @param birthday Atributo que representa a data de nascimento do usuário.
	 * @param login Atributo que representa o login do usuário.
	 * @param password Atributo que representa a senha do usuário.
	 * @param phone Atributo que representa o telefone do usuário.
	 * @param cars Atributo que representa a lista de carros do usuário.
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
	 * Obtem o id do usuário.
	 * 
	 * @return O id do usuário.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id do usuário.
	 * 
	 * @param id Atributo que representa o identificador do usuário.
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Define o nome do usuário.
	 * 
	 * @param firstName Atributo que representa o nome do usuário.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * Define o sobrenome do usuário.
	 * 
	 * @param lastName Atributo que representa o sobrenome do usuário.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * Define o email do usuário.
	 * 
	 * @param email Atributo que representa o e-mail do usuário.
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Define a data de nascimento do usuário.
	 * 
	 * @param birthday Atributo que representa a data de nascimento do usuário.
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * Define o login do usuário.
	 * 
	 * @param login Atributo que representa o login do usuário.
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * Define a senha do usuário.
	 * 
	 * @param password Atributo que representa a senha do usuário.
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Define o telefone do usuário.
	 * @param phone Atributo que representa o telefone do usuário.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Obtem a lista dos ids dos carros do usuário.
	 * 
	 * @return Uma lista que contém os IDs dos carros do usuário.
	 */
	public List<Long> getCars() {
		return cars;
	}

	/**
	 * Define  a lista de carros do usuário.
	 * 
	 * @param cars Atributo que representa a lista de carros do usuário.
	 */
	public void setCars(List<Long> cars) {
		this.cars = cars;
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
	 * Define a data de criacao do usuário.
	 * 
	 * @param createdAt Atributo que representa a data de criação do usuário.
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
	 * Define a data do ultimo login do usuário.
	 * 
	 * @param lastLogin Atributo que representa a data do ultimo login do usuário.
	 */
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Obtem a data do login atual do usuário.
	 * 
	 * @return A data do login atual do usuário.
	 */
	public LocalDateTime getCurrentLogin() {
		return currentLogin;
	}

	/**
	 * Define a data do login atual do usuário.
	 * 
	 * @param currentLogin Atributo que representa a data login atual do usuário.
	 */
	public void setCurrentLogin(LocalDateTime currentLogin) {
		this.currentLogin = currentLogin;
	}

	/**
	 * Valida o usuário.
	 * 
	 * @return Validação do usuário.
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
	 * Gera um hash de um usuário a partir do seu id.
	 * 
	 * @return Um hash de um usuário a partir do seu id.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se um usuário e igual ao outro pelo id.
	 * 
	 * @param obj Objeto para comparação.
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
	 * Retorna uma representacao em texto do usuário.
	 * 
	 * @return Uma representacao em texto do usuário.
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
