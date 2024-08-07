package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * Interface for task services.
 */
public interface TaskService {

    /**
     * Gets all tasks.
     *
     * @param page     page number
     * @param size    page size
     * @param sort    task sort
     * @param filter  task filter
     * @return page of tasks
     */
    Page<Task> getAllTasks(
            Integer page,
            Integer size,
            TaskSort sort,
            TaskFilter filter
    );

    /**
     * Gets a task by ID.
     *
     * @param id task ID
     * @return task
     */
    Task getTaskById(Long id);

    /**
     * Deletes a task.
     *
     * @param id task ID
     */
    void deleteTaskById(Long id);

    /**
     * Creates a new task.
     *
     * @param taskDto task data
     * @return created task
     */
    Task createTask(@Valid TaskDto taskDto);

    /**
     * Gets comments for a task.
     *
     * @param id       task ID
     * @param page     page number
     * @param size    page size
     * @param sort    comment sort
     * @return page of comments
     */
    Page<Comment> getTaskComments(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort
    );

    /**
     * Creates a new comment for a task.
     *
     * @param id       task ID
     * @param commentDto comment data
     * @return created comment
     */
    Comment createComment(Long id, @Valid CommentDto commentDto);

    /**
     * Updates a task.
     *
     * @param id       task ID
     * @param taskDto task data
     * @return updated task
     */
    Task updateTask(Long id, @Valid TaskDto taskDto);

    /**
     * Gets executed tasks for a user.
     *
     * @param id       user ID
     * @param page     page number
     * @param size    page size
     * @param sort    task sort
     * @return page of tasks
     */
    Page<Task> getExecutedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    );

    /**
     * Gets initiated tasks for a user.
     *
     * @param id       user ID
     * @param page     page number
     * @param size    page size
     * @param sort    task sort
     * @return page of tasks
     */
    Page<Task> getInitiatedTasksByUserId(
            Long id,
            Integer page,
            Integer size,
            TaskSort sort
    );

    /**
     * Sets the status of a task.
     *
     * @param id         task ID
     * @param taskStatus task status
     * @return updated task
     */
    Task setTaskStatus(Long id, TaskStatus taskStatus);

}