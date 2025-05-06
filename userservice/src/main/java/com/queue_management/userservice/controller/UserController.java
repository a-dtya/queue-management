package com.queue_management.userservice.controller;


import com.queue_management.userservice.dto.RegisterRequest;
import com.queue_management.userservice.dto.UserResponse;
import com.queue_management.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor // used instead of specifying autowired
public class UserController {
    private UserService userService;
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.existByUserId(userId));
    }

}
