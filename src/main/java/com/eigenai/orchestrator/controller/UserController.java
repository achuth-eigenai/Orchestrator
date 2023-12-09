package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.*;
import com.eigenai.orchestrator.domain.User;
import com.eigenai.orchestrator.service.UserService;
import com.eigenai.orchestrator.vo.Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type User controller.
 */
@RestController
@OpenAPIDefinition
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * Create user user.
	 *
	 * @param userInput the user input
	 * @return the user
	 */
	@PostMapping("/")
	public ResponseEntity<User> createOrUpdateUser(@RequestBody User userInput) {
		return ResponseEntity.ok(userService.createOrUpdate(userInput));
	}

	/**
	 * Gets user.
	 *
	 * @param username the username
	 * @return the user
	 */
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		return ResponseEntity.ok(userService.getByUsername(username));
	}

	/**
	 * Delete user status.
	 *
	 * @param id the id
	 * @return the status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(Response.builder().status(SUCCESS).build());
	}
}