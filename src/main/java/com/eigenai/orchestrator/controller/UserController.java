package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.*;
import com.eigenai.orchestrator.model.User;
import com.eigenai.orchestrator.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@OpenAPIDefinition
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/")
	public User createUser(@RequestBody User userInput) {
		return userService.createOrUpdate(userInput);
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getByUsername(username);
	}
	@DeleteMapping("/{id}")
	public Status deleteUser(@PathVariable Long id) {
		return userService.deleteById(id);
	}
	@PatchMapping("/")
	public User updateUser(@RequestBody User user) {
		return userService.createOrUpdate(user);
	}
}