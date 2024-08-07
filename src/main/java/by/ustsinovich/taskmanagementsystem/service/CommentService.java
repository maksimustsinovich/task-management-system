package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

/**
 * Interface for comment services.
 */
public interface CommentService {

    /**
     * Gets all comments.
     *
     * @param page     page number
     * @param size    page size
     * @param sort    comment sort
     * @param filter  comment filter
     * @return page of comments
     */
    Page<Comment> getAllComments(
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    );

    /**
     * Gets a comment by ID.
     *
     * @param id comment ID
     * @return comment
     */
    Comment getCommentById(Long id);

    /**
     * Updates a comment.
     *
     * @param id         comment ID
     * @param commentDto comment data
     * @return updated comment
     */
    Comment updateCommentById(Long id, @Valid CommentDto commentDto);

    /**
     * Deletes a comment.
     *
     * @param id comment ID
     */
    void deleteCommentById(Long id);

    /**
     * Gets comments by task ID.
     *
     * @param id       task ID
     * @param page     page number
     * @param size    page size
     * @param sort    comment sort
     * @return page of comments
     */
    Page<Comment> getCommentsByTaskId(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort
    );

    /**
     * Creates a new comment.
     *
     * @param task     task
     * @param commentDto comment data
     * @return created comment
     */
    Comment createComment(Task task, @Valid CommentDto commentDto);

}
