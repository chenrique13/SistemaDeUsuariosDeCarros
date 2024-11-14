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
	 * @param id
	 * @param year
	 * @param licensePlate
	 * @param model
	 * @param color
	 * @param user
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
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id do carro.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtem o ano de fabricacao do carro.
	 * 
	 * @return int
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Define o ano de fabricacao do carro.
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Obtem a placa do carro.
	 * 
	 * @return String
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Define a placa do carro.
	 * 
	 * @param licensePlate
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * Obtem o modelo do carro.
	 * 
	 * @return String
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Define o modelo do carro.
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Obtem a cor predominante do carro.
	 * 
	 * @return String
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Define a cor predominante do carro.
	 * 
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	public boolean isValid() {
		return licensePlate != null && !licensePlate.trim().isEmpty() &&
				model != null && !model.trim().isEmpty() && 
				color != null && !color.trim().isEmpty();
	}

	/**
	 * Gera um hash de um carro a partir do seu id e licensePlate.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se um carro e igual ao outro pelo id e licensePlate.
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
		Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Retorna uma representacao em texto do carro.
	 * 
	 * @return String
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
