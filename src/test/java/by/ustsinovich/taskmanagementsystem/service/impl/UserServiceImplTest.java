package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import org.mockito.junit.jupiter.MockitoExtension;

import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.exception.UserNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import by.ustsinovich.taskmanagementsystem.repository.UserRepository;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser() {
        RegisterRequest registerRequest = new RegisterRequest(
                "example@mail.com",
                "password",
                "firstName",
                "patronymic",
                "lastName"
        );

        User user = new User();
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(registerRequest);

        assertEquals(user, createdUser);
        verify(passwordEncoder).encode(registerRequest.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        Page<User> page = Page.empty();
        when(userRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);

        Page<User> result = userService.getAllUsers(0, 10, UserSort.ID_ASC, new UserFilter());

        assertEquals(page, result);
        verify(userRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    void getUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals(user, result);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserById_ThrowsUserNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository).findById(1L);
    }

    @Test
    void deleteUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUserById(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void updateUser() {
        RegisterRequest registerRequest = new RegisterRequest(
                "example@mail.com",
                "password",
                "firstName",
                "patronymic",
                "lastName"
        );

        User user = new User();
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        user = userService.createUser(registerRequest);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto userDto = UserDto
                .builder()
                .email("testexapmle@mail.com")
                .firstName("firstName")
                .lastName("lastName")
                .patronymic("patronymic")
                .build();

        User updatedUser = userService.updateUser(1L, userDto);

        assertEquals(user, updatedUser);
        verify(userRepository).findById(1L);
        verify(userRepository).save(user);
    }

    @Test
    void getInitiatedTasks() {
        Page<Task> page = Page.empty();
        when(taskService.getInitiatedTasksByUserId(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter()))
                .thenReturn(page);

        Page<Task> result = userService.getInitiatedTasks(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter());

        assertEquals(page, result);
        verify(taskService).getInitiatedTasksByUserId(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter());
    }

    @Test
    void getExecutedTasks() {
        Page<Task> page = Page.empty();
        when(taskService.getExecutedTasksByUserId(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter()))
                .thenReturn(page);

        Page<Task> result = userService.getExecutedTasks(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter());

        assertEquals(page, result);
        verify(taskService).getExecutedTasksByUserId(1L, 0, 10, TaskSort.ID_ASC, new TaskFilter());
    }

    @Test
    void getUserByEmail() {
        User user = new User();
        when(userRepository.findByEmail("testexample@mail.ru")).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail("testexample@mail.ru");

        assertEquals(user, result);
        verify(userRepository).findByEmail("testexample@mail.ru");
    }

    @Test
    void getUserByEmail_ThrowsUsernameNotFoundException() {
        when(userRepository.findByEmail("testexample@mail.ru")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByEmail("testexample@mail.ru"));
        verify(userRepository).findByEmail("testexample@mail.ru");
    }

    @Test
    void addTaskToExecute() {
        User user = new User();
        Task task = new Task();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(taskService.getTaskById(1L)).thenReturn(task);

        Task result = userService.addTaskToExecute(1L, 1L);

        assertEquals(task, result);
        verify(userRepository).findById(1L);
        verify(taskService).getTaskById(1L);
        verify(userRepository).save(user);
    }

}