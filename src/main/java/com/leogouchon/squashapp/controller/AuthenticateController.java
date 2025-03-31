package com.leogouchon.squashapp.controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.leogouchon.squashapp.dto.AuthenticateRequestDTO;
import com.leogouchon.squashapp.model.Users;
import com.leogouchon.squashapp.service.AuthenticateService;
import com.leogouchon.squashapp.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;
    private final UserService userService;

    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticateRequestDTO authenticateRequestDTO) {
        String token = authenticateService.login(authenticateRequestDTO);
        if (token == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String token) {
        authenticateService.logout(token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users user) {
        try {
            Users newUser = userService.createUser(user);
            String token = authenticateService.generateToken(newUser);
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("Error during signup: " + e.getMessage());
        }
    }
}
