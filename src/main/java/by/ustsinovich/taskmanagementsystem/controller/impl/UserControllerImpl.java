package by.ustsinovich.taskmanagementsystem.controller.impl;

import by.ustsinovich.taskmanagementsystem.controller.UserController;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import by.ustsinovich.taskmanagementsystem.mapper.TaskMapper;
import by.ustsinovich.taskmanagementsystem.mapper.UserMapper;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final TaskMapper taskMapper;

    @Override
    public Page<UserDto> retrieveAllUsers(
            Integer page,
            Integer size,
            UserSort sort,
            UserFilter filter
    ) {
        return userService
                .getAllUsers(page, size, sort, filter)
                .map(userMapper::mapToDto);
    }

    @Override
    public UserDto retrieveUserById(Long id) {
        return userMapper.mapToDto(userService.getUserById(id));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        return userMapper.mapToDto(userService.updateUser(id, userDto));
    }

    @Override
    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
    }

    @Override
    public Page<TaskDto> retrieveInitiatedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        return userService
                .getInitiatedTasks(id, page, size, sort)
                .map(taskMapper::mapToDto);
    }

    @Override
    public Page<TaskDto> retrieveExecutedTasks(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    ) {
        return userService
                .getExecutedTasks(id, page, size, sort)
                .map(taskMapper::mapToDto);
    }

}
