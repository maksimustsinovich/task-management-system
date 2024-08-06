package by.ustsinovich.taskmanagementsystem.config;

import by.ustsinovich.taskmanagementsystem.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final LogoutHandler logoutHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(
                                        "/api/auth/login",
                                        "/api/auth/register"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        exception -> exception
                                .accessDeniedHandler(
                                        (request, response, accessDeniedException) -> response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()
                        )
                );

        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl
                .withDefaultRolePrefix()
                .role("ADMIN").implies("USER")
                .build();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }

}
