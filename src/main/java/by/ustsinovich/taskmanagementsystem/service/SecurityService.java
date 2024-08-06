package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.entity.User;

public interface SecurityService {

    boolean isSameUser(User principal, Long id);

    boolean isCommentAuthor(User principal, Long commentId);

    boolean isTaskInitiated(User principal, Long taskId);

    boolean isTaskExecuted(User principal, Long taskId);

}
