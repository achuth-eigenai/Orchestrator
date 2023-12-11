package com.eigenai.orchestrator.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * The type User controller.
 */
@RestController
@OpenAPIDefinition
@RequestMapping("v1/public")
public class GeneralController {
    /**
     * Login response entity.
     *
     * @return the response entity
     */
    @GetMapping("/welcome")
    public ResponseEntity<String> login() {
        return ok("We are currently at v1.0.0! \nThis is a basic version of our EigenAI but by the time you use it, its going to be the best product in the market!");
    }
}