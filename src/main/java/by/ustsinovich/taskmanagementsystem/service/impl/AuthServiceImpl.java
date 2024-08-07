package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.exception.InvalidJwtTokenException;
import by.ustsinovich.taskmanagementsystem.service.AuthService;
import by.ustsinovich.taskmanagementsystem.service.JwtService;
import by.ustsinovich.taskmanagementsystem.service.TokenService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public User register(RegisterRequest registerRequest) {
        logger.info("Registering user: {}", registerRequest.getEmail());
        return userService.createUser(registerRequest);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        logger.info("Logging in user: {}", loginRequest.getEmail());
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

        logger.info("Login successful for user: {}", user.getEmail());
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refresh(RefreshRequest refreshRequest) {
        String token = refreshRequest.getRefreshToken();

        String email = jwtService.extractEmail(token);

        User user = userService.getUserByEmail(email);

        if (!jwtService.isValidRefreshToken(token, user)) {
            throw new InvalidJwtTokenException();
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.revokeTokens(user);
        tokenService.saveToken(accessToken, refreshToken, user);

        logger.info("Token refresh successful for user: {}", user.getEmail());
        return new AuthResponse(accessToken, refreshToken);
    }

}
