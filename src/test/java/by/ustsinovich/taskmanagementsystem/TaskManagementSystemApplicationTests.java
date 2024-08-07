package by.ustsinovich.taskmanagementsystem;

import by.ustsinovich.taskmanagementsystem.controller.AuthController;
import by.ustsinovich.taskmanagementsystem.controller.CommentController;
import by.ustsinovich.taskmanagementsystem.controller.TaskController;
import by.ustsinovich.taskmanagementsystem.controller.UserController;
import by.ustsinovich.taskmanagementsystem.exception.ControllerExceptionHandler;
import by.ustsinovich.taskmanagementsystem.jwt.JwtAuthenticationFilter;
import by.ustsinovich.taskmanagementsystem.repository.CommentRepository;
import by.ustsinovich.taskmanagementsystem.repository.TaskRepository;
import by.ustsinovich.taskmanagementsystem.repository.TokenRepository;
import by.ustsinovich.taskmanagementsystem.repository.UserRepository;
import by.ustsinovich.taskmanagementsystem.service.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class TaskManagementSystemApplicationTests {

    private final AuthController authController;
    private final CommentController commentController;
    private final UserController userController;
    private final TaskController taskController;
    private final ControllerExceptionHandler controllerExceptionHandler;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TokenRepository tokenRepository;
    private final AuthService authService;
    private final CommentService commentService;
    private final JwtService jwtService;
    private final UserService userService;
    private final TokenService tokenService;
    private final SecurityService securityService;
    private final TaskService taskService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
        assertThat(commentController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(taskController).isNotNull();
        assertThat(controllerExceptionHandler).isNotNull();
        assertThat(commentRepository).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(taskRepository).isNotNull();
        assertThat(tokenRepository).isNotNull();
        assertThat(authService).isNotNull();
        assertThat(commentService).isNotNull();
        assertThat(jwtService).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(tokenService).isNotNull();
        assertThat(securityService).isNotNull();
        assertThat(taskService).isNotNull();
        assertThat(jwtAuthenticationFilter).isNotNull();
    }

}