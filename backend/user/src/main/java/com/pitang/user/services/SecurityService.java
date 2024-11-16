package com.pitang.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pitang.common.dtos.authentication.SigninDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.exceptions.CustomException;
import com.pitang.common.utils.JwtUtils;

/**
 * Classe responsável por fornecer operações de negócio relacionadas a segurança
 * dos sistemas.
 */
@Service
public class SecurityService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;

	/**
	 * Método responsável fazer verificações na tentativa de login no sistema.
	 *
	 * @author Carlos Pereira
	 *
	 * @param signinDTO Objeto que representa o DTO do signin.
	 * @return String
	 */
	public String signinToken(SigninDTO signinDTO) {
		UserDTO userDTO = userService.findUserByLogin(signinDTO.getLogin());

		if (userDTO != null && passwordEncoder.matches(signinDTO.getPassword(), userDTO.getPassword())) {
			userService.updateLastLoginUser(userDTO.getId());
			return jwtUtils.generateToken(userDTO.getLogin());
		}

		throw new CustomException("Invalid login or password", 1, HttpStatus.UNAUTHORIZED);
	}

}
