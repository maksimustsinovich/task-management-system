package by.ustsinovich.taskmanagementsystem.service;


import by.ustsinovich.taskmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean isValidRefreshToken(String token, User user);

    String extractEmail(String token);

    boolean isValid(String token, UserDetails userDetails);

}
