package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.dto.UserDto;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.UserSort;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public interface UserController {

    @GetMapping
    Page<UserDto> retrieveAllUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") UserSort sort,
            @RequestParam(required = false) UserFilter filter
    );

    @GetMapping("/{id}")
    UserDto retrieveUserById(@PathVariable Long id);

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isSameUser(authentication.principal, #id)")
    UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUserById(@PathVariable Long id);

    @GetMapping("/{id}/initiated")
    Page<TaskDto> retrieveInitiatedTasks(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") TaskSort sort,
            @RequestParam(required = false) TaskFilter filter
    );

    @GetMapping("/{id}/executed")
    Page<TaskDto> retrieveExecutedTasks(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") TaskSort sort,
            @RequestParam(required = false) TaskFilter filter
    );

}