package by.ustsinovich.taskmanagementsystem.service;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<CommentDto> getAllComments(Integer page, Integer size, CommentSort sort, CommentFilter filter);

    CommentDto getCommentById(Long id);

    CommentDto updateCommentById(Long id, CommentDto commentDto);

    void deleteCommentById(Long id);

}
