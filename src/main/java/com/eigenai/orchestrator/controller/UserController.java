package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.User;
import com.eigenai.orchestrator.service.UserService;
import com.eigenai.orchestrator.vo.Response;
import com.eigenai.orchestrator.vo.UserLoginRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

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
     * Create user user.
     *
     * @param user the user input
     * @return the user
     */
    @PostMapping("/create-employee")
    public ResponseEntity<User> createEmployee(@RequestBody User user) {
        return ResponseEntity.ok(userService.createOrUpdate(user));
    }

    /**
     * Gets details.
     *
     * @param principal the principal
     * @return the details
     */
    @GetMapping("/user-info")
    public ResponseEntity<Principal> getDetails(Principal principal) {
        return ResponseEntity.ok(principal);
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

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Value("${spring.security.oauth2.client.registration.cognito.token-uri}")
    private String tokenUri;

    /**
     * Login response entity.
     *
     * @param userLoginRequest the userLoginRequest
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            ClientRegistration registration = clientRegistrationRepository.findByRegistrationId("cognito");
            String tokenEndpoint = tokenUri;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setBasicAuth(registration.getClientId(), registration.getClientSecret());
            String requestBody = String.format("grant_type=client_credentials&username=%s&password=%s", userLoginRequest.getUsername(), userLoginRequest.getPassword());
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, request, String.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("No..");
        }
    }
}