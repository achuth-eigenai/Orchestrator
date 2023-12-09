package com.eigenai.orchestrator.controller;

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
@RequestMapping("v1/general")
public class GeneralController {
	@GetMapping("/welcome")
	public ResponseEntity<String> login() {
		return ResponseEntity.ok("Hello!");
	}
}