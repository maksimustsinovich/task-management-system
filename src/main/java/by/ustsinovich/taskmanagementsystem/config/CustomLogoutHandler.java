package by.ustsinovich.taskmanagementsystem.config;

import by.ustsinovich.taskmanagementsystem.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenService tokenService;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw null;
        }

        String token = header.substring(7);

        tokenService.logout(token);
    }

}
