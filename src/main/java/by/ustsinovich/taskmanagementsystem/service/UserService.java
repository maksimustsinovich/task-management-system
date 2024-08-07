package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * Interface for user services.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param registerRequest registration request
     * @return created user
     */
    User createUser(@Valid RegisterRequest registerRequest);

    /**
     * Gets all users.
     *
     * @param page     page number
     * @param size    page size
     * @param sort    user sort
     * @param filter  user filter
     * @return page of users
     */
    Page<User> getAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    );

    /**
     * Gets a user by ID.
     *
     * @param id user ID
     * @return user
     */
    User getUserById(Long id);

    /**
     * Deletes a user.
     *
     * @param id user ID
     */
    void deleteUserById(Long id);

    /**
     * Updates a user.
     *
     * @param id      user ID
     * @param userDto user data
     * @return updated user
     */
    User updateUser(Long id, @Valid UserDto userDto);

    /**
     * Gets initiated tasks for a user.
     *
     * @param id       user ID
     * @param page     page number
     * @param size    page size
     * @param sort    task sort
     * @param filter  task filter
     * @return page of tasks
     */
    Page<Task> getInitiatedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    /**
     * Gets executed tasks for a user.
     *
     * @param id       user ID
     * @param page     page number
     * @param size    page size
     * @param sort    task sort
     * @param filter  task filter
     * @return page of tasks
     */
    Page<Task> getExecutedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    /**
     * Gets a user by email.
     *
     * @param email user email
     * @return user
     */
    User getUserByEmail(String email);

    /**
     * Adds task to execute.
     *
     * @param executorId executor ID
     * @param id task ID
     * @return task
     */
    Task addTaskToExecute(Long executorId, Long id);

}
