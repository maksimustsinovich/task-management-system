package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.entity.User;

public interface TokenService {

    void saveToken(String accessToken, String refreshToken, User user);

    void revokeTokens(User user);

    void logout(String token);

    boolean isValidRefreshToken(String token);

    boolean isValidAccessToken(String token);

}
