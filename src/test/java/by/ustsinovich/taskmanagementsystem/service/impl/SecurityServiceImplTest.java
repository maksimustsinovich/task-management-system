package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

    @Mock
    private CommentService commentService;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private SecurityServiceImpl securityService;

    @Test
    void testIsSameUser() {
        User principal = User.builder()
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
        Long id = 1L;

        boolean result = securityService.isSameUser(principal, id);

        assertTrue(result);
    }

    @Test
    void testIsNotSameUser() {
        User principal = User.builder()
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
        Long id = 2L;

        boolean result = securityService.isSameUser(principal, id);

        assertFalse(result);
    }

    @Test
    void testIsCommentAuthor() {
        User principal = User.builder()
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
        Long commentId = 1L;
        Comment comment = Comment.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .content("Comment text")
                .task(Task.builder().build())
                .author(principal)
                .build();
        when(commentService.getCommentById(commentId)).thenReturn(comment);

        boolean result = securityService.isCommentAuthor(principal, commentId);

        assertTrue(result);
    }

    @Test
    void testIsNotCommentAuthor() {
        User principal = User.builder()
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
        Long commentId = 1L;
        User anotherUser = User.builder()
                .id(2L)
                .email("jane.doe@example.com")
                .password("password")
                .firstName("Jane")
                .patronymic("Doe")
                .lastName("Doe")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(UserRole.ROLE_USER)
                .build();
        Comment comment = Comment.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .content("Comment text")
                .task(Task.builder().build())
                .author(anotherUser)
                .build();
        when(commentService.getCommentById(commentId)).thenReturn(comment);

        boolean result = securityService.isCommentAuthor(principal, commentId);

        assertFalse(result);
    }

    @Test
    void testIsTaskInitiated() {
        User principal = User.builder()
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
        Long taskId = 1L;
        Task task = Task.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title("Task title")
                .description("Task description")
                .status(TaskStatus.OPEN)
                .priority(TaskPriority.BLOCKER)
                .initiator(principal)
                .executor(new User())
                .build();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        boolean result = securityService.isTaskInitiated(principal, taskId);

        assertTrue(result);
    }

    @Test
    void testIsNotTaskInitiated() {
        User principal = User.builder()
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
        Long taskId = 1L;
        User anotherUser = User.builder()
                .id(2L)
                .email("jane.doe@example.com")
                .password("password")
                .firstName("Jane")
                .patronymic("Doe")
                .lastName("Doe")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(UserRole.ROLE_USER)
                .build();
        Task task = Task.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title("Task title")
                .description("Task description")
                .status(TaskStatus.OPEN)
                .priority(TaskPriority.BLOCKER)
                .initiator(anotherUser)
                .executor(new User())
                .build();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        boolean result = securityService.isTaskInitiated(principal, taskId);

        assertFalse(result);
    }

    @Test
    void testIsTaskExecuted() {
        User principal = User.builder()
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
        Long taskId = 1L;
        Task task = Task.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title("Task title")
                .description("Task description")
                .status(TaskStatus.OPEN)
                .priority(TaskPriority.BLOCKER)
                .initiator(new User())
                .executor(principal)
                .build();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        boolean result = securityService.isTaskExecuted(principal, taskId);

        assertTrue(result);
    }

    @Test
    void testIsNotTaskExecuted() {
        User principal = User.builder()
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
        Long taskId = 1L;
        User anotherUser = User.builder()
                .id(2L)
                .email("jane.doe@example.com")
                .password("password")
                .firstName("Jane")
                .patronymic("Doe")
                .lastName("Doe")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .role(UserRole.ROLE_USER)
                .build();
        Task task = Task.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .title("Task title")
                .description("Task description")
                .status(TaskStatus.OPEN)
                .priority(TaskPriority.BLOCKER)
                .initiator(new User())
                .executor(anotherUser)
                .build();
        when(taskService.getTaskById(taskId)).thenReturn(task);

        boolean result = securityService.isTaskExecuted(principal, taskId);

        assertFalse(result);
    }
}