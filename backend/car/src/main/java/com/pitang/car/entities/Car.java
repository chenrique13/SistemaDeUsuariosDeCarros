package com.pitang.car.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe representando um carro do sistema de usuarios de carros.
 * 
 * @author Carlos Pereira
 */
@Entity
@Table(name = "cars" )
public class Car  implements Serializable{

	private static final long serialVersionUID = 4235620175512203208L;
	
	/**
	 * Identificador unico do carro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	/**
	 * Ano de fabricacao do carro.
	 */
	@Column(name = "year_car")
	private int year;
	
	/**
	 * Placa do carro.
	 */
	@Column
	private String licensePlate;
	
	/**
	 * Modelo do carro.
	 */
	@Column
	private String model;
	
	/**
	 * Cor predominante do carro.
	 */
	@Column
	private String color;
	
	/**
	 * Construtor padrão sem argumentos.
	 */
	public Car() {
		
	}
	
	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param id Atributo que representa o identificador do carro.
	 * @param year Atributo que representa o ano de fabricação do carro.
	 * @param licensePlate Atributo que representa a placa do carro.
	 * @param model Atributo que representa o modelo do carro.
	 * @param color Atributo que representa a cor do carro.
	 */
	public Car(Long id, int year, String licensePlate, String model, String color) {
		this.id = id;
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
	}

	/**
	 * Obtem o id do carro.
	 * 
	 * @return id do carro.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id do carro.
	 * 
	 * @param id O id do carro.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtem o ano de fabricacao do carro.
	 * 
	 * @return int O ano de fabricacao do carro.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Define o ano de fabricacao do carro.
	 * @param year O ano de fabricacao do carro.
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Obtem a placa do carro.
	 * 
	 * @return A placa do carro.
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Define a placa do carro.
	 * 
	 * @param licensePlate A placa do carro.
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * Obtem o modelo do carro.
	 * 
	 * @return O modelo do carro.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Define o modelo do carro.
	 * 
	 * @param model O modelo do carro.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Obtem a cor predominante do carro.
	 * 
	 * @return A cor predominante do carro.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Define a cor predominante do carro.
	 * 
	 * @param color A cor predominante do carro.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Checa se o carro é válido.
	 * 
	 * @return Se o carro é válido.
	 */
	public boolean isValid() {
		return licensePlate != null && !licensePlate.trim().isEmpty() &&
				model != null && !model.trim().isEmpty() && 
				color != null && !color.trim().isEmpty();
	}

	/**
	 * Gera um hash de um carro a partir do seu id.
	 * 
	 * @return um hash de um carro a partir do seu id.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se um carro e igual ao outro pelo id.
	 * 
	 * @param obj Objeto a ser comparado.
	 * @return boolean Se um carro e igual ao outro pelo id.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Retorna uma representacao em texto do carro.
	 * 
	 * @return String A representacao em texto do carro.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", year=");
		builder.append(year);
		builder.append(", licensePlate=");
		builder.append(licensePlate);
		builder.append(", model=");
		builder.append(model);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}
	
}
