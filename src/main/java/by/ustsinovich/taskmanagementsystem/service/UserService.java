package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.dto.request.RegisterRequest;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto createUser(RegisterRequest registerRequest);

    Page<UserDto> getAllUsers(Integer page, Integer size, UserSort sort, UserFilter filter);

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    Page<TaskDto> getInitiatedTasks(Long id, Integer page, Integer size, TaskSort sort, TaskFilter filter);

    Page<TaskDto> getExecutedTasks(Long id, Integer page, Integer size, TaskSort sort, TaskFilter filter);

    User getUserByEmail(String email);

}
