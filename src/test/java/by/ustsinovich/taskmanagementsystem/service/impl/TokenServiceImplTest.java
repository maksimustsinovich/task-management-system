package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.Token;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import by.ustsinovich.taskmanagementsystem.exception.InvalidJwtTokenException;
import by.ustsinovich.taskmanagementsystem.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenServiceImpl tokenService;

    private User user;

    private Token token;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1L)
                .email("john.doe@example.com")
                .password("password")
                .firstName("John")
                .patronymic("Doe")
                .lastName("Doe")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(UserRole.ROLE_USER)
                .build();

        token = Token.builder()
                .accessToken("access-token")
                .refreshToken("refresh-token")
                .isLoggedOut(false)
                .user(user)
                .build();
    }

    @Test
    void testSaveToken() {
        tokenService.saveToken("access-token", "refresh-token", user);

        verify(tokenRepository, times(1)).save(token);
    }

    @Test
    void testRevokeTokens() {
        List<Token> validTokens = List.of(token);
        when(tokenRepository.findAllAccessTokensByUser(user)).thenReturn(validTokens);

        tokenService.revokeTokens(user);

        verify(tokenRepository, times(1)).saveAll(validTokens);
    }

    @Test
    void testLogout() {
        when(tokenRepository.findByAccessToken("access-token")).thenReturn(Optional.of(token));

        tokenService.logout("access-token");

        assertTrue(token.isLoggedOut());
        verify(tokenRepository, times(1)).save(token);
    }

    @Test
    void testLogout_InvalidToken() {
        when(tokenRepository.findByAccessToken("access-token")).thenReturn(Optional.empty());

        assertThrows(InvalidJwtTokenException.class, () -> tokenService.logout("access-token"));
    }

    @Test
    void testIsValidRefreshToken() {
        when(tokenRepository.findByRefreshToken("refresh-token")).thenReturn(Optional.of(token));

        boolean result = tokenService.isValidRefreshToken("refresh-token");

        assertTrue(result);
    }

    @Test
    void testIsValidRefreshToken_InvalidToken() {
        when(tokenRepository.findByRefreshToken("refresh-token")).thenReturn(Optional.empty());

        boolean result = tokenService.isValidRefreshToken("refresh-token");

        assertFalse(result);
    }

    @Test
    void testIsValidAccessToken() {
        when(tokenRepository.findByAccessToken("access-token")).thenReturn(Optional.of(token));

        boolean result = tokenService.isValidAccessToken("access-token");

        assertTrue(result);
    }

    @Test
    void testIsValidAccessToken_InvalidToken() {
        when(tokenRepository.findByAccessToken("access-token")).thenReturn(Optional.empty());

        boolean result = tokenService.isValidAccessToken("access-token");

        assertFalse(result);
    }

}