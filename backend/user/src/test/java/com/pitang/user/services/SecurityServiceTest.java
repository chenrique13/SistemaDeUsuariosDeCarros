package com.pitang.user.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pitang.common.dtos.authentication.SigninDTO;
import com.pitang.common.dtos.cars.CarDTO;
import com.pitang.common.dtos.users.UserDTO;
import com.pitang.common.exceptions.CustomException;
import com.pitang.common.utils.JwtUtils;

public class SecurityServiceTest {

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtUtils jwtUtils;

	@Mock
	private UserService userService;

	@InjectMocks
	private SecurityService securityService;

	private SigninDTO signinDTO;
	private UserDTO userDTO;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		signinDTO = new SigninDTO("carlosPereira", "password123");

		userDTO = new UserDTO(1L, "Carlos", "Pereira", "teste@gmail.com",
				Date.from(LocalDate.of(1994, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), "carlosPereira",
				"password123", "81988888888", new ArrayList<CarDTO>());
	}

	/**
	 * Testa o método SigninToken quando o login e senha do usuario são válidos.
	 */
	@Test
	public void testSigninTokenValidLogin_whenSigninToken_thenReturnToken() {
		// Dado que o login e senha são válidos
		when(userService.findUserByLogin(signinDTO.getLogin())).thenReturn(userDTO);
		when(passwordEncoder.matches(signinDTO.getPassword(), userDTO.getPassword())).thenReturn(true);
		when(jwtUtils.generateToken(userDTO.getLogin())).thenReturn("valid-jwt-token");

		// Quando o método signinToken é chamado
		String token = securityService.signinToken(signinDTO);

		assertThat(token).isEqualTo("valid-jwt-token");
		verify(userService).updateLastLoginUser(userDTO.getId());
	}

	/**
	 * Testa o método SigninToken quando a senha do usuario estiver incorreta e
	 * lança {@link CustomException}.
	 */
	@Test
	public void testSigninTokenInvalidPassword_whenSigninToken_thenThrowCustomException() {
		when(userService.findUserByLogin(signinDTO.getLogin())).thenReturn(userDTO);
		when(passwordEncoder.matches(signinDTO.getPassword(), userDTO.getPassword())).thenReturn(false);

		CustomException exception = assertThrows(CustomException.class, () -> {
			securityService.signinToken(signinDTO);
		});

		assertThat(exception.getMessage()).isEqualTo("Invalid login or password");
		assertThat(exception.getErrorCode()).isEqualTo(1);
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Testa o método SigninToken quando o usuario não é encontrado e lança
	 * {@link CustomException}.
	 */
	@Test
	public void testSigninTokenUserNotFound_whenSigninToken_thenThrowCustomException() {
		when(userService.findUserByLogin(signinDTO.getLogin())).thenReturn(null);

		CustomException exception = assertThrows(CustomException.class, () -> {
			securityService.signinToken(signinDTO);
		});

		assertThat(exception.getMessage()).isEqualTo("Invalid login or password");
		assertThat(exception.getErrorCode()).isEqualTo(1);
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}
}
