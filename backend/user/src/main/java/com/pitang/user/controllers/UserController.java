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

import jakarta.servlet.http.HttpServletRequest;

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
	 * @autor Carlos Pereira
	 *
	 * @return ResponseEntity<List<{@link UserDTO}>>
	 */
	@Override
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		List<UserDTO> listaUsuarios = userService.findAllUsers();
		
		return ResponseEntity.ok(listaUsuarios);
	}

	/**
	 * Endpoint responsavel por buscar um usuario por id. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity<<{@link UserDTO}>>
	 */
	@Override
	public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
		UserDTO userDTO = userService.findUserById(id);

		if (userDTO != null) {
			return ResponseEntity.ok(userDTO);
		}

		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por cadastrar um novo usuario no sistema. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param novoCar
	 * @return ResponseEntity< {@link UserDTO} >
	 */
	@Override
	public ResponseEntity<UserDTO> insertUser(@RequestBody SaveUserDTO saveUserDTO) {
		UserDTO userDTO = userService.insertUser(saveUserDTO);

		URI uri = UriComponentsBuilder.fromPath("user/").buildAndExpand(userDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}

	/**
	 * EndPoint responsavel por atualizar um usuario do sistema. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @param user
	 * @return ResponseEntity< {@link UserDTO} >
	 */
	@Override
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
		UserDTO userDTO = userService.updateUser(id, updateUserDTO);

		if (userDTO != null) {
			return ResponseEntity.ok(userDTO);
		}
		
		return ResponseEntity.notFound().build();
	}

	/**
	 * EndPoint responsavel por excluir um usuario do sistema. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @param id
	 * @return ResponseEntity< {@link Void} >
	 */
	@Override
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Endpoint responsavel por buscar informações do usuario logado. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @return ResponseEntity<<{@link UserInfoDTO}>>
	 */
	@Override
	public ResponseEntity<UserInfoDTO> findMe() {
		String login = jwtUtils.getLoginFromToken(request);
		
		return ResponseEntity.ok(userService.findMe(login));
	}

	/**
	 * Endpoint responsavel por buscar o usuario por login. {@link User}
	 *
	 * @autor Carlos Pereira
	 *
	 * @return {@link UserDTO}
	 */
	@Override
	public UserDTO findUserByLogin(String login) {
		return userService.findUserByLogin(login);
	}

	/**
	 * Endpoint responsável por realizar uma tentativa de login no sistema.
	 *
	 * @author Carlos Pereira
	 *
	 * @param credenciaisDTO
	 * @return ResponseEntity Void
	 */
	@Override
	public ResponseEntity<Void> signinUser(SigninDTO signinDTO) {
		String token = securityService.signinToken(signinDTO);

		if (token != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			return ResponseEntity.ok().headers(headers).build();
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Endpoint interno responsável por adicionar o carro no usuario.
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @param login
	 */
	@Override
	public void addCarToUser(Long id, String login) {
		userService.addCarToUser(id, login);
	}

	/**
	 * Endpoint interno responsável por deletar o carro no usuario.
	 *
	 * @author Carlos Pereira
	 *
	 * @param id
	 * @param login
	 */
	@Override
	public void deleteCarToUser(Long id, String login) {
		userService.deleteCarToUser(id, login);
	}

}
