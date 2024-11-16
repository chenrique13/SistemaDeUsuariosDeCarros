package com.pitang.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.user.entities.User;

/**
 * Classe responsável por conter métodos para realização de operações no banco
 * de dados da classe {@link User}.
 * 
 * @author Carlos Pereira
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Realiza uma busca por e-mail de usuário no banco de dados.
	 * 
	 * @param email Atributo que representa o e-mail do usuário.
	 * @return Um {@link User}.
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Realiza uma busca por login de usuário no banco de dados .
	 * 
	 * @param login Atributo que representa o login do usuário.
	 * @return Um {@link User}.
	 */
	Optional<User> findByLogin(String login);
	
}
