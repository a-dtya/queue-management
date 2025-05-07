package com.queue_management.shopservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId) {
        try {
            // basically we're making a get req using webclient (custom userservicewebclient created in webclient config)
            return userServiceWebClient.get()
                    .uri("api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block(); // Blocking here for simplicity — suitable for sync service calls
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("User not found with id: " + userId);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid request for user ID: " + userId);
            } else {
                throw new RuntimeException("Unexpected error: " + e.getMessage(), e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with USER-SERVICE", e);
        }
    }
}
