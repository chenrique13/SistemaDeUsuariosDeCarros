package com.pitang.common.dtos.cars;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe DTO que reoresenta um carro no sistema.
 */
public class CarDTO implements Serializable{

	private static final long serialVersionUID = -27439010688617984L;

	/**
	 * Identificador unico do carro.
	 */
	@Schema(description = "Identificador unico do carro.", example = "1")
	private Long id;
	
	/**
	 * Ano de fabricacao do carro.
	 */
	@Schema(description = "Ano de fabricação do carro.", example = "2024")
	private int year;
	
	/**
	 * Placa do carro.
	 */
	@Schema(description = "Placa do carro.", example = "KIC-2050")
	private String licensePlate;
	
	/**
	 * Modelo do carro.
	 */
	@Schema(description = "Modelo do carro.", example = "ONIX")
	private String model;
	
	/**
	 * Cor predominante do carro.
	 */
	@Schema(description = "Cor do carro.", example = "PRATA")
	private String color;

	/**
	 * Construtor padrão sem argumentos.
	 */
	public CarDTO() {
		
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
	public CarDTO(Long id, int year, String licensePlate, String model, String color) {
		this.id = id;
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
	}

	/**
	 * Obtém o id do carro.
	 * 
	 * @return id do carro.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Obtém o ano de fabricacao do carro.
	 * 
	 * @return int O ano de fabricacao do carro.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Obtém a placa do carro.
	 * 
	 * @return A placa do carro.
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Obtém o modelo do carro.
	 * 
	 * @return O modelo do carro.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Obtém a cor predominante do carro.
	 * 
	 * @return A cor predominante do carro.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Retorna uma representacao em texto do carro.
	 * 
	 * @return String A representacao em texto do carro.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarDTO [id=");
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
