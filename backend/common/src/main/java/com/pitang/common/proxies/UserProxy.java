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

import com.pitang.common.dtos.authentication.SigninDTO;
import com.pitang.common.dtos.users.SaveUserDTO;
import com.pitang.common.dtos.users.UpdateUserDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.dtos.users.UserInfoDTO;

/**
 * É uma interface Feign que se comunica com um serviço de usuário remoto.
 */
@FeignClient(name = "user-service")
public interface UserProxy {

	/**
	 * EndPoint responsável por buscar todos os usuários do sistema.
	 * 
	 * @return O {@link ResponseEntity} uma lista de {@link UserDTO}.
	 */
	@GetMapping(path = "/users")
	public ResponseEntity<List<UserDTO>> findAllUsers();

	/**
	 * EndPoint responsável por buscar um usuário por id do sistema.
	 * 
	 * @param id Atributo que representa o identificador do usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@GetMapping(path = "/users/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Long id);

	/**
	 * EndPoint responsável por inserir um usuário no sistema.
	 * 
	 * @param newUserDTO Objeto que representa o usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@PostMapping(path = "/users")
	public ResponseEntity<UserDTO> insertUser(@RequestBody SaveUserDTO newUserDTO);

	/**
	 * EndPoint responsável por atualizar um usuário no sistema.
	 * 
	 * @param id           Atributo que representa o identificador do usuário.
	 * @param updateUserDTO Objeto que representa o usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO);

	/**
	 * EndPoint responsável por deletar um usuário no sistema.
	 * 
	 * @param id Atributo que representa o identificador do usuário.
	 * @return {@link ResponseEntity} da operação foi concluída sem um retorno de
	 *         dados.
	 */
	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id);

	/**
	 * EndPoint responsável por buscar o usuário com informações específicas.
	 * 
	 * @return O {@link ResponseEntity} de um {@link UserInfoDTO}.
	 */
	@GetMapping(path = "/me")
	public ResponseEntity<UserInfoDTO> findMe();

	/**
	 * 
	 * @param signinDTO Objeto com as informações do signin.
	 * @return {@link ResponseEntity} da operação foi concluída sem um retorno de
	 *         dados.
	 */
	@PostMapping(path = "/signin")
	public ResponseEntity<Void> signinUser(@RequestBody SigninDTO signinDTO);

	/**
	 * EndPoint interno responsável por realizar o signin do usuário no sistema.
	 * 
	 * @param login Atributo que representa o login do usuário.
	 * @return Um {@link UserDTO}
	 */
	@GetMapping(path = "/users/internal/login/{login}")
	public UserDTO findUserByLogin(@PathVariable String login);

	/**
	 * EndPoint interno responsável por adicionar um carro no usuário.
	 * 
	 * @param id    Atributo que representa o id do carro.
	 * @param login Atributo que representa o login do usuário.
	 */
	@PutMapping("/users/internal/addCarUser/{id}/{login}")
	void addCarToUser(@PathVariable Long id, @PathVariable String login);

	/**
	 * EndPoint interno responsável por deletar um carro no usuário.
	 * 
	 * @param id    Atributo que representa o id do carro.
	 * @param login Atributo que representa o login do usuário.
	 */
	@DeleteMapping("/users/internal/deleteCarUser/{id}/{login}")
	void deleteCarToUser(@PathVariable Long id, @PathVariable String login);
}
