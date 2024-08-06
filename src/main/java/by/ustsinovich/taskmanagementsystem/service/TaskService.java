package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<TaskDto> getAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    TaskDto getTaskById(Long id);

    void deleteTaskById(Long id);

    TaskDto createTask(TaskDto taskDto);

    Page<CommentDto> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    );

    CommentDto createComment(Long id, CommentDto commentDto);

    TaskDto updateTask(Long id, TaskDto taskDto);

    Page<TaskDto> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

}
