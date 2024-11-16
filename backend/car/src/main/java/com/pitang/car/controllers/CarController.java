package com.pitang.car.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pitang.car.entities.Car;
import com.pitang.car.services.CarService;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.cars.SaveUpdateCarDTO;
import com.pitang.common.proxies.CarProxy;
import com.pitang.common.utils.JwtUtils;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controlador responsável por gerenciar as requisições relacionadas aos carros.
 * Implementa a interface {@link CarProxy} para fornecer operações CRUD de carros.
 * 
 * Este controlador lida com endpoints relacionados a operações de criação, 
 * leitura, atualização e exclusão de carros no sistema.
 * 
 */
@RestController
@RequestMapping(path = "/")
public class CarController implements CarProxy {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CarService servicoCar;

	@Autowired
	private HttpServletRequest request;

	/**
	 * Endpoint responsavel por buscar todos os carros. {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @return ResponseEntity<List<{@link CarDTO}>>
	 */
	@Override
	@Operation(summary = "Find All Cars", description = "Busca todos os carros do usuário logado.")
	public ResponseEntity<List<CarDTO>> findAllCars() {
		return ResponseEntity.ok(servicoCar.findAllCarsByUserLogin(jwtUtils.getLoginFromToken(request)));
	}

	/**
	 * Endpoint responsavel por buscar um carro por id. {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity<<{@link CarDTO}>>
	 */
	@Override
	@Operation(summary = "Find Car By Id", description = "Busca um carro do usuário logado por id.")
	public ResponseEntity<CarDTO> findCarById(
			@Parameter(description = "Id do carro a ser consultado.", required = true, example = "1") 
			@PathVariable Long id) {
		return ResponseEntity.ok(servicoCar.findCarById(id, jwtUtils.getLoginFromToken(request)));
	}

	/**
	 * EndPoint responsavel por cadastrar um novo carro no sistema. {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @param newcar
	 * @return ResponseEntity< {@link Car} >
	 */
	@Override
	@Operation(summary = "Insert Car", description = "Insere um carro no sistema.")
	public ResponseEntity<CarDTO> insertCar(
			@io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Detalhes do carro a ser criado.", required = true)
			@RequestBody SaveUpdateCarDTO newcar) {
		CarDTO novoCarro = servicoCar.insertCar(newcar, jwtUtils.getLoginFromToken(request));

		URI uri = UriComponentsBuilder.fromPath("car/").buildAndExpand(novoCarro.getId()).toUri();
		return ResponseEntity.created(uri).body(novoCarro);
	}

	/**
	 * EndPoint responsavel por atualizar um carro do sistema. {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @param updateCarDTO
	 * @return ResponseEntity< {@link Car} >
	 */
	@Override
	@Operation(summary = "Update Car", description = "Atualiza um carro no sistema.")
	public ResponseEntity<CarDTO> updateCar(
			@Parameter(description = "Id do carro a ser atualizado.", required = true, example = "1") 
			@PathVariable Long id, 
			@io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Detalhes do carro a ser atualizado.", required = true)
			@RequestBody SaveUpdateCarDTO updateCarDTO) {
		CarDTO carroAtualizado = servicoCar.updateCar(id, updateCarDTO, jwtUtils.getLoginFromToken(request));

		if (carroAtualizado != null) {
			return ResponseEntity.ok(carroAtualizado);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por excluir um carro do sistema. {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity< {@link Void} >
	 */
	@Override
	@Operation(summary = "Delete Car", description = "Deleta um carro no sistema.")
	public ResponseEntity<Void> deleteCar(
			@Parameter(description = "Id do carro a ser deletado.", required = true, example = "1") 
			@PathVariable Long id) {
		servicoCar.deleteCar(id, jwtUtils.getLoginFromToken(request));

		return ResponseEntity.noContent().build();
	}

	/**
	 * EndPoint interno responsavel por buscar uma lista de carro do sistema.
	 * {@link Car}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity< {@link Void} >
	 */
	@Override
	@Hidden
	public List<CarDTO> findAllCarsbyIds(List<Long> listIdsCars) {
		return servicoCar.findAllCarsByIds(listIdsCars);
	}

}
