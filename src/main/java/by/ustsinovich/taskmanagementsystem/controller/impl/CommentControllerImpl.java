package by.ustsinovich.taskmanagementsystem.controller.impl;

import by.ustsinovich.taskmanagementsystem.controller.CommentController;
import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.mapper.CommentMapper;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @Override
    public Page<CommentDto> retrieveAllComments(
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter) {
        return commentService.getAllComments(page, size, sort, filter).map(commentMapper::mapToDto);
    }

    @Override
    public CommentDto retrieveCommentById(Long id) {
        return commentMapper.mapToDto(commentService.getCommentById(id));
    }

    @Override
    public CommentDto updateCommentById(Long id, CommentDto commentDto) {
        return commentMapper.mapToDto(commentService.updateCommentById(id, commentDto));
    }

    @Override
    public void deleteCommentById(Long id) {
        commentService.deleteCommentById(id);
    }

}
