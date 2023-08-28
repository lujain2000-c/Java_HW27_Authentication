package com.example.hw27security.Controller;

import com.example.hw27security.Api.ApiResponse;
import com.example.hw27security.Model.User;
import com.example.hw27security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("OK"));
    }
}
