package com.pitang.common.dtos.cars;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class CarDTO implements Serializable{

	private static final long serialVersionUID = -27439010688617984L;

	@Schema(description = "Identificador unico do carro.", example = "1")
	private Long id;
	
	@Schema(description = "Ano de fabricação do carro.", example = "2024")
	private int year;
	
	@Schema(description = "Placa do carro.", example = "KIC-2050")
	private String licensePlate;
	
	@Schema(description = "Modelo do carro.", example = "ONIX")
	private String model;
	
	@Schema(description = "Cor do carro.", example = "PRATA")
	private String color;

	public CarDTO() {
		
	}
	
	public CarDTO(Long id, int year, String licensePlate, String model, String color) {
		this.id = id;
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public int getYear() {
		return year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

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
