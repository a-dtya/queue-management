package com.queue_management.userservice.service;

import com.queue_management.userservice.dto.RegisterRequest;
import com.queue_management.userservice.dto.UserResponse;
import com.queue_management.userservice.model.User;
import com.queue_management.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse register(RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists in the users db");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = repository.save(user);

        // Convert to response DTO
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }



    public UserResponse getUserProfile(String userId) {
        Optional<User> optionalUser = repository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = optionalUser.get();

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    public Boolean existByUserId(String userId) {
        log.info("User Validation API called for user : {} ", userId);
        return repository.existsById(userId);
    }
}
