package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public interface CommentController {

    @GetMapping
    Page<CommentDto> retrieveAllComments(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") CommentSort sort,
            @RequestParam(required = false) CommentFilter filter
    );

    @GetMapping("/{id}")
    CommentDto retrieveCommentById(@PathVariable Long id);

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isCommentAuthor(authentication.principal, #id)")
    CommentDto updateCommentById(@PathVariable Long id, @RequestBody CommentDto commentDto);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isCommentAuthor(authentication.principal, #id)")
    void deleteCommentById(@PathVariable Long id);

}
