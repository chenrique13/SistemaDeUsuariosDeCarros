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

	Optional<User> findByEmail(String email);
	
	Optional<User> findByLogin(String login);
	
}
