package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public interface TaskController {

    @PostMapping
    TaskDto createTask(@RequestBody TaskDto taskDto);

    @GetMapping
    Page<TaskDto> retrieveAllTasks(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") TaskSort sort,
            @RequestParam(required = false) TaskFilter filter
    );

    @GetMapping("/{id}")
    TaskDto retrieveTaskById(@PathVariable Long id);

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isTaskAuthor(authentication.principal, #id)")
    TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto);

    @DeleteMapping("/{id}")
    void deleteTaskById(@PathVariable Long id);

    @GetMapping("/{id}/comments")
    Page<CommentDto> retrieveTaskComments(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false) CommentSort sort,
            @RequestParam(required = false) CommentFilter filter
    );

    @PostMapping("/{id}/comments")
    CommentDto createTaskComment(@PathVariable Long id, @RequestBody CommentDto commentDto);

    @PostMapping("/{id}/executor")
    TaskDto setExecutors(@PathVariable Long id, @RequestParam Long executorId);

    @PostMapping("/{id}/status")
    TaskDto setStatus(@PathVariable Long id, @RequestParam TaskStatus taskStatus);

}