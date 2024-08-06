package by.ustsinovich.taskmanagementsystem.controller.impl;

import by.ustsinovich.taskmanagementsystem.controller.AuthController;
import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;
import by.ustsinovich.taskmanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    public AuthResponse refresh(RefreshRequest refreshRequest) {
        return authService.refresh(refreshRequest);
    }

}
