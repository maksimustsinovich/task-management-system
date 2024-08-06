package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<Comment> getAllComments(
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    );

    Comment getCommentById(Long id);

    Comment updateCommentById(Long id, CommentDto commentDto);

    void deleteCommentById(Long id);

    Page<Comment> getCommentsByTaskId(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    );

    Comment createComment(Task task, CommentDto commentDto);

}
