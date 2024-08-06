package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.exception.CommentNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.repository.CommentRepository;
import by.ustsinovich.taskmanagementsystem.service.CommentService;
import by.ustsinovich.taskmanagementsystem.specification.CommentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllComments(
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Comment> specification = CommentSpecification.filterBy(filter);

        return commentRepository.findAll(specification, pageable);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    @Override
    public Comment updateCommentById(Long id, CommentDto commentDto) {
        Comment comment = getCommentById(id);

        comment.setContent(commentDto.getContent());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = getCommentById(id);

        commentRepository.delete(comment);
    }

    @Override
    public Page<Comment> getCommentsByTaskId(
            Long id,
            Integer page,
            Integer size,
            CommentSort sort,
            CommentFilter filter
    ) {
        Pageable pageable = PageRequest.of(page, size, sort.getSort());
        Specification<Comment> specification = CommentSpecification.filterBy(filter);

        return commentRepository.findByTaskId(id, specification, pageable);
    }

    @Override
    public Comment createComment(Task task, CommentDto commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = Comment
                .builder()
                .task(task)
                .content(commentDto.getContent())
                .author(user)
                .build();

        return commentRepository.save(comment);
    }

}
