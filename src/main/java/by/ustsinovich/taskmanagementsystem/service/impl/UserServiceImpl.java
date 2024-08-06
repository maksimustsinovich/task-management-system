package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.exception.UserNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import by.ustsinovich.taskmanagementsystem.mapper.UserMapper;
import by.ustsinovich.taskmanagementsystem.repository.UserRepository;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import by.ustsinovich.taskmanagementsystem.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final TaskService taskService;

    @Override
    public UserDto createUser(RegisterRequest registerRequest) {
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

        return userMapper.mapToDto(userRepository.save(user));
    }

    @Override
    public Page<UserDto> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<User> specification = UserSpecification.filterBy(filter);

        return userRepository
                .findAll(specification, pageable)
                .map(userMapper::mapToDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public Page<TaskDto> getInitiatedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        return null;
    }

    @Override
    public Page<TaskDto> getExecutedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        return taskService.getExecutedTasksByUserId(id, page, size, sort, filter);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> null);
    }

}
