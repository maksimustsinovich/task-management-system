package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import by.ustsinovich.taskmanagementsystem.service.SecurityService;
import by.ustsinovich.taskmanagementsystem.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final CommentService commentService;

    private final TaskService taskService;

    @Override
    public boolean isSameUser(User principal, Long id) {
        return principal.getId().equals(id);
    }

    @Override
    public boolean isCommentAuthor(User principal, Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        return comment.getAuthor().equals(principal);
    }

    @Override
    public boolean isTaskInitiated(User principal, Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return task.getInitiator().equals(principal);
    }

    @Override
    public boolean isTaskExecuted(User principal, Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return task.getExecutor().equals(principal);
    }

}
