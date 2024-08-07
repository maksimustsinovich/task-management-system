package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.exception.UserNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import by.ustsinovich.taskmanagementsystem.repository.UserRepository;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import by.ustsinovich.taskmanagementsystem.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TaskService taskService;

    @Override
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        logger.info("Creating new user with email: {}", registerRequest.getEmail());
        User user = User
                .builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .patronymic(registerRequest.getPatronymic())
                .lastName(registerRequest.getLastName())
                .role(UserRole.ROLE_USER)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Page<User> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    ) {
        logger.info("Getting all users with filter: {}", filter);
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<User> specification = UserSpecification.filterBy(filter);

        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Getting user by ID: {}", id);
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        logger.info("Deleting user with ID: {}", id);
        User user = getUserById(id);

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        logger.info("Updating user with ID: {}", id);
        User user = getUserById(id);

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPatronymic(userDto.getPatronymic());
        user.setLastName(userDto.getLastName());

        return userRepository.save(user);
    }

    @Override
    public Page<Task> getInitiatedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        logger.info("Getting initiated tasks for user with ID: {}", id);
        return taskService.getInitiatedTasksByUserId(id, page, size, sort);
    }

    @Override
    public Page<Task> getExecutedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        logger.info("Getting executed tasks for user with ID: {}", id);
        return taskService.getExecutedTasksByUserId(id, page, size, sort);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Getting user by email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    @Transactional
    public Task addTaskToExecute(Long executorId, Long taskId) {
        logger.info("Adding task to execute for user with ID: {}", executorId);
        User user = getUserById(executorId);

        Task task = taskService.getTaskById(taskId);

        user.getExecuted().add(task);

        userRepository.save(user);

        return task;
    }

}
