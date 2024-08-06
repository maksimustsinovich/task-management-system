package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.service.AuthService;
import by.ustsinovich.taskmanagementsystem.service.JwtService;
import by.ustsinovich.taskmanagementsystem.service.TokenService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userService.getUserByEmail(loginRequest.getEmail());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.revokeTokens(user);
        tokenService.saveToken(accessToken, refreshToken, user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refresh(RefreshRequest refreshRequest) {
        String token = refreshRequest.getRefreshToken();

        String email = jwtService.extractEmail(token);

        User user = userService.getUserByEmail(email);

        if (!jwtService.isValidRefreshToken(token, user)) {
            throw null;
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.revokeTokens(user);
        tokenService.saveToken(accessToken, refreshToken, user);

        return new AuthResponse(accessToken, refreshToken);
    }

}
