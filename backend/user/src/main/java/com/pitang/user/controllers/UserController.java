package com.pitang.user.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pitang.common.dtos.authentication.SigninDTO;
import com.pitang.common.dtos.users.SaveUserDTO;
import com.pitang.common.dtos.users.UpdateUserDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.dtos.users.UserInfoDTO;
import com.pitang.common.proxies.UserProxy;
import com.pitang.common.utils.JwtUtils;
import com.pitang.user.entities.User;
import com.pitang.user.services.SecurityService;
import com.pitang.user.services.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controlador responsável por gerenciar as requisições relacionadas aos usuários.
 * Implementa a interface {@link UserProxy} para fornecer operações CRUD de usuários.
 * 
 * Este controlador lida com endpoints relacionados a operações de criação, 
 * leitura, atualização e exclusão de usuários no sistema.
 * 
 */
@RestController
@RequestMapping(path = "/")
public class UserController implements UserProxy{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * Endpoint responsavel por buscar todos os usuarios. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @return O {@link ResponseEntity} da lista de {@link UserDTO}.
	 */
	@Override
	@Operation(summary = "Find All Users", description = "Buscar todos os usuários e seus carros.")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		List<UserDTO> listaUsuarios = userService.findAllUsers();
		
		return ResponseEntity.ok(listaUsuarios);
	}

	/**
	 * Endpoint responsavel por buscar um usuario por id. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id Atributo que representa o identificador do usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@Override
	@Operation(summary = "Find User By Id", description = "Busca por id o usuário e seus carros.")
	public ResponseEntity<UserDTO> findUserById(
			@Parameter(description = "Id do usuário a ser consultado.", required = true, example = "1") 
			@PathVariable Long id) {
		UserDTO userDTO = userService.findUserById(id);

		if (userDTO != null) {
			return ResponseEntity.ok(userDTO);
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por cadastrar um novo usuario no sistema. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @param saveUserDTO Objeto que representa o usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@Override
	@Operation(summary = "Insert User", description = "Insere um usuário com seus carros no sistema.")
	public ResponseEntity<UserDTO> insertUser(
			@io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Detalhes do usuário a ser criado.", required = true)
			@RequestBody SaveUserDTO saveUserDTO) {
		UserDTO userDTO = userService.insertUser(saveUserDTO);

		URI uri = UriComponentsBuilder.fromPath("user/").buildAndExpand(userDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}

	/**
	 * EndPoint responsavel por atualizar um usuario do sistema. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id Atributo que representa o identificador do usuário.
	 * @param updateUserDTO Objeto que representa o usuário.
	 * @return O {@link ResponseEntity} de um {@link UserDTO}.
	 */
	@Override
	@Operation(summary = "Update User", description = "Atualiza um usuário no sistema.")
	public ResponseEntity<UserDTO> updateUser(
			@Parameter(description = "Id do usuário a ser atualizado.", required = true, example = "1") 
			@PathVariable Long id, 
			@io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Detalhes do usuário a ser atualizado.", required = true)
			@RequestBody UpdateUserDTO updateUserDTO) {
		UserDTO userDTO = userService.updateUser(id, updateUserDTO);

		if (userDTO != null) {
			return ResponseEntity.ok(userDTO);
		}
		
		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por excluir um usuario do sistema. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @param id Atributo que representa o identificador do usuário.
	 */
	@Override
	@Operation(summary = "Delete User", description = "Deleta um usuário no sistema.")
	public ResponseEntity<Void> deleteUser(
			@Parameter(description = "Id do usuário a ser deletado.", required = true, example = "1") 
			@PathVariable Long id) {
		userService.deleteUser(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Endpoint responsavel por buscar informações do usuario logado. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @return O {@link ResponseEntity} de um {@link UserInfoDTO}.
	 */
	@Override
	@Operation(summary = "Finde Me", description = "Busca informações do o usuário logado, seus carros, data de criação e de último login.")
	public ResponseEntity<UserInfoDTO> findMe() {
		String login = jwtUtils.getLoginFromToken(request);
		
		return ResponseEntity.ok(userService.findMe(login));
	}

	/**
	 * Endpoint responsável por realizar uma tentativa de login no sistema.
	 *
	 * @author Carlos Pereira
	 *
	 * @param signinDTO Objeto que representa o DTO do signin.
	 */
	@Override
	@Operation(summary = "Signin User", description = "Faz a autenticação do usuário no sistema com a geração do token JWT.")
	public ResponseEntity<Void> signinUser(
			@io.swagger.v3.oas.annotations.parameters.RequestBody (description = "Detalhes do usuário a ser autenticado.", required = true)
			@RequestBody SigninDTO signinDTO) {
		String token = securityService.signinToken(signinDTO);

		if (token != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			return ResponseEntity.ok().headers(headers).build();
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	/**
	 * Endpoint responsavel por buscar o usuario por login. {@link User}
	 *
	 * @author Carlos Pereira
	 *
	 * @return {@link UserDTO}
	 */
	@Override
	@Hidden
	public UserDTO findUserByLogin(String login) {
		return userService.findUserByLogin(login);
	}

	/**
	 * Endpoint interno responsável por adicionar o carro no usuario.
	 *
	 * @author Carlos Pereira
	 *
	 * @param id Atributo que representa o identificador do carro.
	 * @param login Atributo que representa o login do usuário.
	 */
	@Override
	@Hidden
	public void addCarToUser(Long id, String login) {
		userService.addCarToUser(id, login);
	}

	/**
	 * Endpoint interno responsável por deletar o carro no usuario.
	 *
	 * @author Carlos Pereira
	 *
	 * @param id Atributo que representa o identificador do carro.
	 * @param login Atributo que representa o login do usuário.
	 */
	@Override
	@Hidden
	public void deleteCarToUser(Long id, String login) {
		userService.deleteCarToUser(id, login);
	}

}
