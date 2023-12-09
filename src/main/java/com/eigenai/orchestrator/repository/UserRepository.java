package com.eigenai.orchestrator.repository;


import com.eigenai.orchestrator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findByUsername(String username);
}
