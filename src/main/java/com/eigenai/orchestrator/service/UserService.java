package com.eigenai.orchestrator.service;

import com.eigenai.orchestrator.domain.Status;
import com.eigenai.orchestrator.model.User;
import com.eigenai.orchestrator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.eigenai.orchestrator.domain.Status.SUCCESS;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createOrUpdate(User userInput) {
        return userRepository.save(userInput);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Status deleteById(Long id) {
        userRepository.deleteById(id);
        return SUCCESS;
    }
}
