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

@FeignClient(name = "car-service")
public interface CarProxy {

	@GetMapping(path = "/cars")
	public ResponseEntity<List<CarDTO>> findAllCars();

	@GetMapping(path = "/cars/{id}")
	public ResponseEntity<CarDTO> findCarById(@PathVariable Long id);

	@PostMapping(path = "/cars")
	public ResponseEntity<CarDTO> insertCar(@RequestBody SaveUpdateCarDTO newCarDTO);

	@PutMapping("/cars/{id}")
	public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody SaveUpdateCarDTO updateCarDTO);

	@DeleteMapping(path = "/cars/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long id);

	@GetMapping(path = "/cars/internal/getCarsIds/{listIdsCars}")
	public List<CarDTO> findAllCarsbyIds(@RequestParam List<Long> listIdsCars);

}
