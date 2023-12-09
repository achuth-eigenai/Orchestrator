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
@RequestMapping("v1/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * Create user user.
	 *
	 * @param user the user input
	 * @return the user
	 */
	@PostMapping("/")
	public ResponseEntity<User> createOrUpdateUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createOrUpdate(user));
	}

	/**
	 * Gets user.
	 *
	 * @param username the username
	 * @return the user
	 */
	@GetMapping("/{username}")
	public ResponseEntity<User> userInfo(@PathVariable String username) {
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

	/**
	 * Login response entity.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return ResponseEntity.ok(User.builder().build());
	}
}