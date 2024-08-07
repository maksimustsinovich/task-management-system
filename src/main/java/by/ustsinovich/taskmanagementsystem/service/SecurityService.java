package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.entity.User;

/**
 * Interface for security services.
 */
public interface SecurityService {

    /**
     * Checks if the current user is the same as the provided user.
     *
     * @param principal current user
     * @param id        user ID
     * @return true if users are the same, false otherwise
     */
    boolean isSameUser(User principal, Long id);

    /**
     * Checks if the current user is the author of a comment.
     *
     * @param principal current user
     * @param commentId comment ID
     * @return true if user is the author, false otherwise
     */
    boolean isCommentAuthor(User principal, Long commentId);

    /**
     * Checks if the current user initiated a task.
     *
     * @param principal current user
     * @param taskId    task ID
     * @return true if user initiated the task, false otherwise
     */
    boolean isTaskInitiated(User principal, Long taskId);

    /**
     * Checks if the current user executed a task.
     *
     * @param principal current user
     * @param taskId    task ID
     * @return true if user executed the task, false otherwise
     */
    boolean isTaskExecuted(User principal, Long taskId);

}