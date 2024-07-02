package com.ms.user.controllers;

import com.ms.user.domain.User;
import com.ms.user.dto.UserDTO;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userDTO) {
		var user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
	}
}
