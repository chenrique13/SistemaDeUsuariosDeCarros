package com.pitang.common.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pitang.common.dtos.authentication.SigninDTO;

@FeignClient(name = "authenticationManeger-service")
public interface AuthenticationProxy {

	@PostMapping(path = "/signin")
	public ResponseEntity<Void> signinUser(@RequestBody SigninDTO signinDTO);

}
