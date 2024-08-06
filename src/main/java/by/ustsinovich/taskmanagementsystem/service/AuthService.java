package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;

public interface AuthService {

    UserDto register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(RefreshRequest refreshRequest);

}
