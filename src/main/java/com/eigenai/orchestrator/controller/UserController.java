package com.eigenai.orchestrator.controller;

import com.eigenai.orchestrator.domain.User;
import com.eigenai.orchestrator.service.UserService;
import com.eigenai.orchestrator.vo.Response;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

import static com.eigenai.orchestrator.constant.SecurityConstants.IS_SHOPIFY;
import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type User controller.
 */
@Slf4j
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
    @GetMapping("/info")
    @PreAuthorize(IS_SHOPIFY)
    public ResponseEntity<Principal> getDetails(@RequestHeader Map<String, String> headers, Principal principal) {
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
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

//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;

//    @Value("${spring.security.oauth2.client.registration.cognito.token-uri}")
//    private String tokenUri;

    /**
     * Login response entity.
     *
     * @param userLoginRequest the userLoginRequest
     * @return the response entity
     */
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
//        try {
//            ClientRegistration registration = clientRegistrationRepository.findByRegistrationId("cognito");
//            String tokenEndpoint = tokenUri;
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            headers.setBasicAuth(registration.getClientId(), registration.getClientSecret());
//            String requestBody = String.format("grant_type=client_credentials&username=%s&password=%s", userLoginRequest.getUsername(), userLoginRequest.getPassword());
//            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, request, String.class);
//            return response;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.ok("No..");
//        }
//    }
}