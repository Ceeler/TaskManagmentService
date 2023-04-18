package com.example.taskmanagmentservice.rest.User;

import com.example.taskmanagmentservice.model.request.UserData;
import com.example.taskmanagmentservice.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    UserResponseBuilder userResponseBuilder;

    @PostMapping(path = "/registration")
    public ResponseEntity<AuthResponse> registrationUser(@RequestBody UserData userData){
        return userResponseBuilder.processRegistrationUser(userData);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<AuthResponse> authUser(
            @RequestBody UserData userData

    ){
    return userResponseBuilder.processAuthenticationUser(userData);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<String> getUserInfo(@PathVariable Long id){
        return ResponseEntity.ok("ok");
    }
}
