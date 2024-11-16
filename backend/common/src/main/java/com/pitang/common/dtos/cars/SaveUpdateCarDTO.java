package com.pitang.common.dtos.cars;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe representando um carro na inserção e atialização.
 * 
 * @author Carlos Pereira
 */
public class SaveUpdateCarDTO implements Serializable {

	private static final long serialVersionUID = -27439010688617984L;

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
	public SaveUpdateCarDTO() {

	}

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param year         Atributo que representa o ano de fabricação do carro.
	 * @param licensePlate Atributo que representa a placa do carro.
	 * @param model        Atributo que representa o modelo do carro.
	 * @param color        Atributo que representa a cor do carro.
	 */
	public SaveUpdateCarDTO(int year, String licensePlate, String model, String color) {
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
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
		builder.append("SaveUpdateCarDTO [year=");
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
