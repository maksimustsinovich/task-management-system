package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import org.springframework.data.domain.Page;

public interface UserService {

    User createUser(RegisterRequest registerRequest);

    Page<User> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    );

    User getUserById(Long id);

    void deleteUserById(Long id);

    User updateUser(Long id, UserDto userDto);

    Page<Task> getInitiatedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    Page<Task> getExecutedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    User getUserByEmail(String email);

    Task addTaskToExecute(Long executorId, Long id);

}
