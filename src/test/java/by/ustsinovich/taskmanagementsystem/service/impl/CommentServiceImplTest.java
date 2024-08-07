package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.exception.CommentNotFoundException;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void getAllComments() {
        // given
        Page<Comment> comments = Page.empty();
        when(commentRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(comments);

        // when
        Page<Comment> result = commentService.getAllComments(0, 10, CommentSort.CREATED_ASC, new CommentFilter());

        // then
        assertEquals(comments, result);
    }

    @Test
    void getCommentById_NotFound() {
        // given
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        CommentNotFoundException exception = assertThrows(CommentNotFoundException.class, () -> commentService.getCommentById(1L));

        // then
        assertEquals("Comment with ID `1` not found", exception.getMessage());
    }

    @Test
    void deleteCommentById() {
        // given
        Comment comment = Comment.builder().id(1L).build();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        // when
        commentService.deleteCommentById(1L);

        // then
        verify(commentRepository).delete(comment);
    }

}