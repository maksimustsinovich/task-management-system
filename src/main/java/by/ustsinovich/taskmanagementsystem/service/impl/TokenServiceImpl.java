package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.Token;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.repository.TokenRepository;
import by.ustsinovich.taskmanagementsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public void saveToken(String accessToken, String refreshToken, User user) {
        Token token = Token
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isLoggedOut(false)
                .user(user)
                .build();

        tokenRepository.save(token);
    }

    @Override
    public void revokeTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user);

        if (!validTokens.isEmpty()) {
            validTokens.forEach(token -> token.setLoggedOut(true));

            tokenRepository.saveAll(validTokens);
        }
    }

    @Override
    public void logout(String token) {
        tokenRepository
                .findByAccessToken(token)
                .map(stored -> {
                    stored.setLoggedOut(true);

                    return tokenRepository.save(stored);
                })
                .orElseThrow(() -> null);
    }

    @Override
    public boolean isValidRefreshToken(String token) {
        return tokenRepository
                .findByRefreshToken(token)
                .map(stored -> !stored.isLoggedOut())
                .orElse(false);
    }

    @Override
    public boolean isValidAccessToken(String token) {
        return tokenRepository
                .findByAccessToken(token)
                .map(stored -> !stored.isLoggedOut())
                .orElse(false);
    }

}
