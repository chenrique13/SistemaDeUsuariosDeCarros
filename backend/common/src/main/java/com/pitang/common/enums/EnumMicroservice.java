package com.pitang.common.enums;

/**
 * Enum responsável por conter os nomes dos micros serviços que são usados nas
 * configurações de rotas;
 */
public enum EnumMicroservice {
	USERS("user-service"),
	CARS("car-service");

	/**
	 * nome do microsserviço.
	 */
	private String name;

	/**
	 * Construtor com todos os argumentos.
	 * 
	 * @param name
	 */
	EnumMicroservice(String name) {
		this.name = name;
	}

	/**
	 * Obtém o nome do microsserviço.
	 *
	 * @return String
	 */
	public String getName() {
		return name;
	}
}
