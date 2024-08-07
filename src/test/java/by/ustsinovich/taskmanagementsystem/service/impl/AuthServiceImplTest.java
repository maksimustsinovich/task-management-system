package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.request.LoginRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RefreshRequest;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.dto.response.AuthResponse;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.exception.InvalidJwtTokenException;
import by.ustsinovich.taskmanagementsystem.service.JwtService;
import by.ustsinovich.taskmanagementsystem.service.TokenService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest(
                "email@example.com",
                "password",
                "1",
                "1",
                "1"
        );

        User user = User
                .builder()
                .email("email@example.com")
                .password("password")
                .patronymic("1")
                .firstName("1")
                .lastName("1")
                .build();

        when(userService.createUser(registerRequest)).thenReturn(user);

        assertEquals(user, authService.register(registerRequest));
    }

    @Test
    void testLogin() {
        LoginRequest loginRequest = new LoginRequest("email@example.com", "password");
        User user = User
                .builder()
                .email("email@example.com")
                .password("password")
                .patronymic("1")
                .firstName("1")
                .lastName("1")
                .build();
        String accessToken = "access-token";
        String refreshToken = "refresh-token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userService.getUserByEmail(loginRequest.getEmail())).thenReturn(user);
        when(jwtService.generateAccessToken(user)).thenReturn(accessToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        AuthResponse authResponse = authService.login(loginRequest);
        assertEquals(accessToken, authResponse.getAccessToken());
        assertEquals(refreshToken, authResponse.getRefreshToken());
    }

    @Test
    void testRefresh() {
        RefreshRequest refreshRequest = new RefreshRequest("refresh-token");
        User user = User
                .builder()
                .email("email@example.com")
                .password("password")
                .patronymic("1")
                .firstName("1")
                .lastName("1")
                .build();
        String accessToken = "access-token";
        String refreshToken = "refresh-token";

        when(jwtService.extractEmail(refreshRequest.getRefreshToken())).thenReturn(user.getEmail());
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(jwtService.isValidRefreshToken(refreshRequest.getRefreshToken(), user)).thenReturn(true);
        when(jwtService.generateAccessToken(user)).thenReturn(accessToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        AuthResponse authResponse = authService.refresh(refreshRequest);
        assertEquals(accessToken, authResponse.getAccessToken());
        assertEquals(refreshToken, authResponse.getRefreshToken());
    }

    @Test
    void testRefresh_InvalidToken() {
        RefreshRequest refreshRequest = new RefreshRequest("refresh-token");
        User user = User
                .builder()
                .email("email@example.com")
                .password("password")
                .patronymic("1")
                .firstName("1")
                .lastName("1")
                .build();

        when(jwtService.extractEmail(refreshRequest.getRefreshToken())).thenReturn(user.getEmail());
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(jwtService.isValidRefreshToken(refreshRequest.getRefreshToken(), user)).thenReturn(false);

        assertThrows(InvalidJwtTokenException.class, () -> authService.refresh(refreshRequest));
    }
}