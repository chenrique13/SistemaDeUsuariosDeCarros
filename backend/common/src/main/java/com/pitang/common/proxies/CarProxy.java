package com.pitang.common.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;

/**
 * É uma interface Feign que se comunica com um serviço de carros remoto.
 */
@FeignClient(name = "car-service")
public interface CarProxy {

	/**
	 * EndPoint responsável por buscar todos os carros do sistema.
	 * 
	 * @return O {@link ResponseEntity} uma lista de {@link CarDTO}.
	 */
	@GetMapping(path = "/cars")
	public ResponseEntity<List<CarDTO>> findAllCars();

	/**
	 * EndPoint responsável por buscar um carro por id do sistema.
	 * 
	 * @param id Atributo que representa o identificador do carro.
	 * @return O {@link ResponseEntity} de um {@link CarDTO}.
	 */
	@GetMapping(path = "/cars/{id}")
	public ResponseEntity<CarDTO> findCarById(@PathVariable Long id);

	/**
	 * EndPoint responsável por inserir um carro no sistema.
	 * 
	 * @param newCarDTO Objeto que representa o carro.
	 * @return O {@link ResponseEntity} de um {@link CarDTO}.
	 */
	@PostMapping(path = "/cars")
	public ResponseEntity<CarDTO> insertCar(@RequestBody SaveUpdateCarDTO newCarDTO);

	/**
	 * EndPoint responsável por atualizar um carro no sistema.
	 * 
	 * @param id           Atributo que representa o identificador do carro.
	 * @param updateCarDTO Objeto que representa o carro.
	 * @return O {@link ResponseEntity} de um {@link CarDTO}.
	 */
	@PutMapping("/cars/{id}")
	public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody SaveUpdateCarDTO updateCarDTO);

	/**
	 * EndPoint responsável por deletar um carro no sistema.
	 * 
	 * @param id Atributo que representa o identificador do carro.
	 * @return {@link ResponseEntity} da operação foi concluída sem um retorno de
	 *         dados.
	 */
	@DeleteMapping(path = "/cars/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id);

	/**
	 * EndPoint interno responsável por buscar uma lista de carros por ids.
	 * 
	 * @param listIdsCars Lista de identificadores de carros.
	 * @return Uma lista de {@link CarDTO}.
	 */
	@GetMapping(path = "/cars/internal/getCarsIds/{listIdsCars}")
	public List<CarDTO> findAllCarsbyIds(@RequestParam List<Long> listIdsCars);

}
