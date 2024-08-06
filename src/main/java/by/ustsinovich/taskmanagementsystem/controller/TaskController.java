package by.ustsinovich.taskmanagementsystem.controller;

import by.ustsinovich.taskmanagementsystem.dto.CommentDto;
import by.ustsinovich.taskmanagementsystem.dto.TaskDto;
import by.ustsinovich.taskmanagementsystem.enums.CommentSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskSort;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Task API")
@SecurityRequirement(name = "JWT")
public interface TaskController {

    /**
     * Create a new task
     *
     * @param taskDto The task to create
     * @return The created task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully")
    })
    @SecurityRequirement(name = "JWT")
    TaskDto createTask(@Valid @RequestBody TaskDto taskDto);

    /**
     * Retrieve all tasks
     *
     * @param page  The page number
     * @param size  The page size
     * @param sort  The sort order
     * @param filter The filter criteria
     * @return The tasks
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    })
    @Parameter(name = "page", description = "The page number")
    @Parameter(name = "size", description = "The page size")
    @Parameter(name = "sort", description = "The sort order")
    @Parameter(name = "filter", description = "The filter criteria")
    @SecurityRequirement(name = "JWT")
    Page<TaskDto> retrieveAllTasks(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "20") @Min(1) Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") TaskSort sort,
            @RequestParam(required = false) TaskFilter filter
    );

    /**
     * Retrieve a task by ID
     *
     * @param id The task ID
     * @return The task
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve a task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Parameter(name = "id", description = "The task ID")
    @SecurityRequirement(name = "JWT")
    TaskDto retrieveTaskById(@PathVariable @Min(1) Long id);

    /**
     * Update a task
     *
     * @param id       The task ID
     * @param taskDto The updated task
     * @return The updated task
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Parameter(name = "id", description = "The task ID")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isTaskInitiated(authentication.principal, #id)")
    TaskDto updateTask(@PathVariable @Min(1) Long id, @Valid @RequestBody TaskDto taskDto);

    /**
     * Delete a task
     *
     * @param id The task ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Parameter(name = "id", description = "The task ID")
    @SecurityRequirement(name = "JWT")
    void deleteTaskById(@PathVariable @Min(1) Long id);

    /**
     * Retrieve comments for a task
     *
     * @param id      The task ID
     * @param page    The page number
     * @param size    The page size
     * @param sort    The sort order
     * @param filter The filter criteria
     * @return The comments
     */
    @GetMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve comments for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
    })
    @Parameter(name = "id", description = "The task ID")
    @Parameter(name = "page", description = "The page number")
    @Parameter(name = "size", description = "The page size")
    @Parameter(name = "sort", description = "The sort order")
    @Parameter(name = "filter", description = "The filter criteria")
    @SecurityRequirement(name = "JWT")
    Page<CommentDto> retrieveTaskComments(
            @PathVariable @Min(1) Long id,
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "20") @Min(1) Integer size,
            @RequestParam(required = false, defaultValue = "ID_ASC") CommentSort sort,
            @RequestParam(required = false) CommentFilter filter
    );

    /**
     * Create a new comment for a task
     *
     * @param id        The task ID
     * @param commentDto The comment to create
     * @return The created comment
     */
    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new comment for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created successfully")
    })
    @Parameter(name = "id", description = "The task ID")
    @SecurityRequirement(name = "JWT")
    CommentDto createTaskComment(@PathVariable @Min(1) Long id, @Valid @RequestBody CommentDto commentDto);

    /**
     * Set the executor for a task
     *
     * @param id        The task ID
     * @param executorId The executor ID
     * @return The updated task
     */
    @PostMapping("/{id}/executor")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Set the executor for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Executor set successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Parameter(name = "id", description = "The task ID")
    @Parameter(name = "executorId", description = "The executor ID")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasRole('ADMIN') || @securityService.isTaskInitiated(authentication.principal, #id)")
    TaskDto setExecutors(@PathVariable @Min(1) Long id, @RequestParam @Min(1) Long executorId);

    /**
     * Set the status for a task
     *
     * @param id        The task ID
     * @param taskStatus The task status
     * @return The updated task
     */
    @PostMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Set the status for a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status set successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @Parameter(name = "id", description = "The task ID")
    @Parameter(name = "taskStatus", description = "The task status")
    @PreAuthorize(
            "hasRole('ADMIN') ||" +
            " @securityService.isTaskInitiated(authentication.principal, #id) ||" +
            " @securityService.isTaskExecuted(authentication.principal, #id)"
    )
    @SecurityRequirement(name = "JWT")
    TaskDto setStatus(@PathVariable @Min(1) Long id, @RequestParam @NotBlank TaskStatus taskStatus);

}