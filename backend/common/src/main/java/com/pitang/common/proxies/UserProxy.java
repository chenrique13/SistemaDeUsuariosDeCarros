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

@FeignClient(name = "user-service")
public interface UserProxy {

	@GetMapping(path = "/users")
	public ResponseEntity<List<UserDTO>> findAllUsers();

	@GetMapping(path = "/users/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable Long id);

	@PostMapping(path = "/users")
	public ResponseEntity<UserDTO> insertUser(@RequestBody SaveUserDTO newUserDTO);

	@PutMapping("/users/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO);

	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id);

	@GetMapping(path = "/me")
	public ResponseEntity<UserInfoDTO> findMe();

	@PostMapping(path = "/signin")
	public ResponseEntity<Void> signinUser(@RequestBody SigninDTO signinDTO);

	@GetMapping(path = "/users/internal/login/{login}")
	public UserDTO findUserByLogin(@PathVariable String login);

	@PutMapping("/users/internal/addCarUser/{id}/{login}")
	void addCarToUser(@PathVariable Long id, @PathVariable String login);

	@DeleteMapping("/users/internal/deleteCarUser/{id}/{login}")
	void deleteCarToUser(@PathVariable Long id, @PathVariable String login);
}
