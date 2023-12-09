package com.eigenai.orchestrator.service;

import com.eigenai.orchestrator.domain.Status;
import com.eigenai.orchestrator.domain.User;
import com.eigenai.orchestrator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Create or update user.
     *
     * @param userInput the user input
     * @return the user
     */
    public User createOrUpdate(User userInput) {
        return userRepository.save(userInput);
    }

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Delete by id status.
     *
     * @param id the id
     * @return the status
     */
    public Status deleteById(Long id) {
        userRepository.deleteById(id);
        return SUCCESS;
    }
}
