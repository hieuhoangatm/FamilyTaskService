package com.ptit.mobie.taskfamily_service.controller;


import com.ptit.mobie.taskfamily_service.dto.request.LoginRequest;
import com.ptit.mobie.taskfamily_service.dto.request.RegisterRequest;
import com.ptit.mobie.taskfamily_service.dto.response.LoginResponse;
import com.ptit.mobie.taskfamily_service.model.User;
import com.ptit.mobie.taskfamily_service.service.AuthenticationService;
import com.ptit.mobie.taskfamily_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = authenticationService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }
}
