package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.mapper.CommentMapper;
import by.ustsinovich.taskmanagementsystem.repository.CommentRepository;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Override
    public Page<CommentDto> getAllComments(Integer page, Integer size, CommentSort sort, CommentFilter filter) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return null;
    }

    @Override
    public CommentDto updateCommentById(Long id, CommentDto commentDto) {
        return null;
    }

    @Override
    public void deleteCommentById(Long id) {

    }

}
