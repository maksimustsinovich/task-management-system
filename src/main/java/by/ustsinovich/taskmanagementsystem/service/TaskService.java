package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<Task> getAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    Task createTask(TaskDto taskDto);

    Page<Comment> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    );

    Comment createComment(Long id, CommentDto commentDto);

    Task updateTask(Long id, TaskDto taskDto);

    Page<Task> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    Page<Task> getInitiatedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    Task setTaskStatus(Long id, TaskStatus taskStatus);

}
