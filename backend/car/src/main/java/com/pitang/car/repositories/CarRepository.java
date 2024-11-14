package com.pitang.car.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.car.entities.Car;

/**
 * Classe responsável por conter métodos para realização de operações no banco
 * de dados da classe {@link Car}.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findByLicensePlate(String licensePlate);

}
