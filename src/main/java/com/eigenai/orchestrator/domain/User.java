package com.eigenai.orchestrator.domain;

import com.eigenai.orchestrator.domain.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type User.
 */
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String address;
    private Role role;
}
