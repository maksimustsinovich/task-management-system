package by.ustsinovich.taskmanagementsystem.controller.impl;

import by.ustsinovich.taskmanagementsystem.controller.TaskController;
import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import by.ustsinovich.taskmanagementsystem.mapper.CommentMapper;
import by.ustsinovich.taskmanagementsystem.mapper.TaskMapper;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import by.ustsinovich.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;

    private final UserService userService;

    private final TaskMapper taskMapper;

    private final CommentMapper commentMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return taskMapper.mapToDto(taskService.createTask(taskDto));
    }

    @Override
    public Page<TaskDto> retrieveAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    ) {
        return taskService
                .getAllTasks(page, size, sort, filter)
                .map(taskMapper::mapToDto);
    }

    @Override
    public TaskDto retrieveTaskById(Long id) {
        return taskMapper.mapToDto(taskService.getTaskById(id));
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        return taskMapper.mapToDto(taskService.updateTask(id, taskDto));
    }

    @Override
    public void deleteTaskById(Long id) {
        taskService.deleteTaskById(id);
    }

    @Override
    public Page<CommentDto> retrieveTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    ) {
        return taskService
                .getTaskComments(id, page, size, sort, filter)
                .map(commentMapper::mapToDto);
    }

    @Override
    public CommentDto createTaskComment(Long id, CommentDto commentDto) {
        return commentMapper.mapToDto(taskService.createComment(id, commentDto));
    }

    @Override
    public TaskDto setExecutors(Long id, Long executor) {
        return taskMapper.mapToDto(userService.addTaskToExecute(executor, id));
    }

    @Override
    public TaskDto setStatus(Long id, TaskStatus taskStatus) {
        return taskMapper.mapToDto(taskService.setTaskStatus(id, taskStatus));
    }

}
