package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment API")
@SecurityRequirement(name = "JWT")
public interface CommentController {

    /**
     * Retrieve all comments
     *
     * @param page   The page number
     * @param size   The page size
     * @param sort   The sort order
     * @param filter The filter criteria
     * @return The comments
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
    })
    @Parameter(name = "page", description = "The page number")
    @Parameter(name = "size", description = "The page size")
    @Parameter(name = "sort", description = "The sort order")
    @Parameter(name = "filter", description = "The filter criteria")
    @SecurityRequirement(name = "JWT")
    Page<CommentDto> retrieveAllComments(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "20") @Min(1) Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") CommentSort sort,
            @RequestParam(required = false) CommentFilter filter
    );

    /**
     * Retrieve a comment by ID
     *
     * @param id The comment ID
     * @return The comment
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve a comment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @Parameter(name = "id", description = "The comment ID", required = true)
    @SecurityRequirement(name = "JWT")
    CommentDto retrieveCommentById(@PathVariable @Min(1) Long id);

    /**
     * Update a comment
     *
     * @param id         The comment ID
     * @param commentDto The updated comment
     * @return The updated comment
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @Parameter(name = "id", description = "The comment ID", required = true)
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isCommentAuthor(authentication.principal, #id)")
    CommentDto updateCommentById(@PathVariable @Min(1) Long id, @Valid @RequestBody CommentDto commentDto);

    /**
     * Delete a comment
     *
     * @param id The comment ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @Parameter(name = "id", description = "The comment ID", required = true)
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isCommentAuthor(authentication.principal, #id)")
    void deleteCommentById(@PathVariable @Min(1) Long id);

}