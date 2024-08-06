package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    @PostMapping("/register")
    UserDto register(@RequestBody RegisterRequest registerRequest);

    @PostMapping("/login")
    AuthResponse login(@RequestBody LoginRequest loginRequest);

    @PostMapping("/refresh")
    AuthResponse refresh(@RequestBody RefreshRequest refreshRequest);

}
